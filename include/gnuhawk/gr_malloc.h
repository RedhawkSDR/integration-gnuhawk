/*
 * This file is protected by Copyright. Please refer to the COPYRIGHT file
 * distributed with this source distribution.
 *
 * This file is part of GNUHAWK.
 *
 * GNUHAWK is free software: you can redistribute it and/or modify is under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * GNUHAWK is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program.  If not, see http://www.gnu.org/licenses/.
 */

#ifndef  __GR_ALIGN_MALLOC_H__
#define  __GR_ALIGN_MALLOC_H__

// The following headers are required for all allocators.
#include <stddef.h>  // Required for size_t and ptrdiff_t and NULL
#include <new>       // Required for placement new and std::bad_alloc
#include <stdexcept> // Required for std::length_error

/**
 * GR_PosixAlign
 *
 * An Allocator that can produce memory blocks on a specified alignment
 * factor using posix memory allocation routines. The alignment factor is 
 * provided to the template at definition time to reduce complexity of having 
 * to provide an allocator object at declaration of an stl container: 
 *   i.e. 
 *       std::vector< float, GR_PosixAlign< float, 16 > > foo;
 *   instead of 
 *        GR_PosixAlign< float > a = GR_PosixAlign<float>(16);
 *        std::vector< float, GR_PosixAlign< floa t> > foo( 2, 0.0, a );
 * 
 * This class implements all the interfaces required for an stl allocate but is not
 * derived from an stl allocator
 */
template <typename T, int ALIGN=1 > 
class GR_PosixAlign 
{

 public:

 // The following will be the same for virtually all allocators.
 typedef T * pointer;
 typedef const T * const_pointer;
 typedef T& reference;
 typedef const T& const_reference;
 typedef T value_type;
 typedef size_t size_type;
 typedef ptrdiff_t difference_type;


 pointer address( reference r) const {
   return &r;
 }

 const_pointer  address(const_reference s) const {
   return &s;
 }

 inline size_type max_size() const {
   return std::numeric_limits<size_type>::max() / sizeof(T);
 }

 template <typename U> struct rebind {
   typedef GR_PosixAlign<U, ALIGN> other;
 };

 inline void construct( pointer p, const T& t) const {
   // allocate the object t in memory space p using new with placement
   new ((void*)p) T(t);
 }

 // The definition of destroy() must be the same for all allocators.
 inline void destroy( pointer p) const {
   p->~T();
 }

 inline bool operator!=(const GR_PosixAlign& other) const {
   return !(*this == other);
 }

 // Always returns true for stateless allocators.
 inline bool operator==(const GR_PosixAlign& other) const {
   return true;
 }

 // Default constructor, copy constructor, rebinding constructor, and destructor.
 // Empty for stateless allocators.
 GR_PosixAlign( )  
 { 
   alignment = ALIGN;
 }

 GR_PosixAlign(const GR_PosixAlign& src) { 
   alignment = src.alignment;
 }

 template <typename U> GR_PosixAlign(const GR_PosixAlign<U> &s) 
 { 
   alignment = s.alignment;
 }

 virtual ~GR_PosixAlign() { }

 T * allocate(const size_t n) const {

   // The return value of allocate(0) is unspecified.
   // GR_PosixAlign returns NULL in order to avoid depending
   // on malloc(0)'s implementation-defined behavior
   // (the implementation can define malloc(0) to return NULL,
   // in which case the bad_alloc check below would fire).
   // All allocators can return NULL in this case.
   if (n == 0) {
     return NULL;
   }

   // check for max allocation case
   if (n > max_size()) {
     throw std::length_error("GR_PosixAlign<T>::allocate() - Integer overflow.");
   }

   void * pv = NULL;
   int v_align = alignment;
    
   if ( alignment == 1 ) {
     return reinterpret_cast< pointer >( ::operator new( n * sizeof(T) ) );
   }
    
   if ( (v_align & (v_align-1) ) != 0 ) 
     v_align = (int)pow(2, round( log2(v_align) ) );

   posix_memalign( &pv, v_align, n * sizeof(T) );
  
   // Allocators should throw std::bad_alloc in the case of memory allocation failure.
   if (pv == NULL) {
     throw std::bad_alloc();
   }

   return reinterpret_cast< pointer >(pv);

 }

 inline void deallocate(T * const p, const size_t n) const {
   ::operator delete(p);
 }

 // The following will be the same for all allocators that ignore hints.
 template <typename U> T * allocate(const size_t n, const U * ) const {
   return allocate(n);
 }

 private:
 GR_PosixAlign& operator=(const GR_PosixAlign&);

 int  alignment;

};


/**
 * GR_MemAlign
 *
 * This class overrides the allocate method to perform the GNU Radio buffer alignment/allocation
 * process. Which allocates a buffer for N elements and pads the buffer with an additional space
 * to hold the address of the originally allcoated buffer that will be used during deallocation
 *
 */
template <typename T  > 
class GR_MemAlign : public GR_PosixAlign< T > 
{

 public:

  // Standard typedefs for allocators
  typedef T * pointer;
  typedef const T * const_pointer;
  typedef T& reference;
  typedef const T& const_reference;
  typedef T value_type;
  typedef size_t size_type;
  typedef ptrdiff_t difference_type;

 GR_MemAlign( ) :
  GR_PosixAlign<T>()
    { 
    }

 GR_MemAlign(const GR_MemAlign& src) :
  GR_PosixAlign<T>( src )
    { 
    }

  virtual ~GR_MemAlign() { }

  template <typename U> GR_MemAlign(const GR_MemAlign<U> &s): 
    GR_PosixAlign<T>(s)
    { 
    }

  template <typename U> struct rebind {
    typedef GR_MemAlign< U > other;
  };

 inline size_type max_size() const {
   return std::numeric_limits<size_type>::max() / sizeof(T);
 }

  T * allocate(const size_t n) const {

    if (n == 0) {
      return NULL;
    }

    // check for max allocation case
    if (n > max_size()) {
      throw std::length_error("GR_MemAlign<T>::allocate() - Integer overflow.");
    }

    void *p;
    void **p1;
    int pad_size = 31;
    size_type tsize = n * sizeof(T) + pad_size;

    if ( (p = ::operator new(tsize))== NULL )  {
	throw std::bad_alloc();
    }
    
    // round up to next 16 byte boundary
    p1 = ( void **)(((long)p+pad_size) & (~15));
  
    
    // stash actual starting address of allocation before our return address
    p1[-1] = p;

    // return aligned address
    return reinterpret_cast< pointer >(p1);

  }

  inline void deallocate(T * const p, const size_t n) const {
    ::operator delete( ((void **)p)[-1] );
  }

  // The following will be the same for all allocators that ignore hints.
  template <typename U> T * allocate(const size_t n, const U * ) const {
    return allocate(n);
  }

 private:
  GR_MemAlign& operator=(const GR_MemAlign&);

};



/**
 * GR_Mem16Align
 *
 * This class overrides the stl std::allocate class to perform the GNU Radio buffer alignment/allocation
 * process. Which allocates a buffer for N elements and pads the buffer with an additional space
 * to hold the address of the originally allcoated buffer that will be used during deallocation
 *
 */
template <typename T  > 
class GR_Mem16Align : public std::allocator< T > 
{

 public:

  // The following will be the same for virtually all allocators.
  typedef T * pointer;
  typedef const T * const_pointer;
  typedef T& reference;
  typedef const T& const_reference;
  typedef T value_type;
  typedef size_t size_type;
  typedef ptrdiff_t difference_type;

 GR_Mem16Align( ) :
  std::allocator<T>()
    { 
    }

 GR_Mem16Align(const GR_Mem16Align& src) :
  std::allocator<T>( src )
    { 
    }

  virtual ~GR_Mem16Align() { }

 template <typename U> struct rebind {
   typedef GR_Mem16Align< U > other;
 };

 inline size_type max_size() const {
   return std::numeric_limits<size_type>::max() / sizeof(T);
 }

  T * allocate(const size_t n) const {

    if (n == 0) {
      return NULL;
    }

    // check for max allocation case
    if (n > max_size()) {
      throw std::length_error("std::allocator<T>::allocate() - Integer overflow.");
    }

    void *p;
    void **p1;
    int pad_size = 31; 
    size_type tsize = n * sizeof(T) + pad_size;

    if ( (p = ::operator new(tsize))== NULL )  {
	throw std::bad_alloc();
    }
    
    // round up to next 16 byte boundary
    p1 = ( void **)( ((long)p+pad_size) & (~15));
  
    
    // stash actual starting address of allocation before our return address
    p1[-1] = p;

    // return aligned address
    return reinterpret_cast< pointer >(p1);

  }

  inline void deallocate(T * const p, const size_t n) const {
    ::operator delete( ((void **)p)[-1] );
  }

  // The following will be the same for all allocators that ignore hints.
  template <typename U> T * allocate(const size_t n, const U * ) const {
    return allocate(n);
  }

 private:
  GR_Mem16Align& operator=(const GR_Mem16Align&);

};




#endif
