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

package gov.redhawk.ide.codegen.jet.cplusplus.gnuradio;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mil.jpeojtrs.sca.spd.SoftPkg;

import org.eclipse.core.runtime.CoreException;

import gov.redhawk.ide.codegen.IScaPortCodegenTemplate;
import gov.redhawk.ide.codegen.ImplementationSettings;
import gov.redhawk.ide.codegen.jet.TemplateParameter;
import gov.redhawk.ide.codegen.jet.cplusplus.gnuradio.template.component.PortImplCppProvidesTemplate;
import gov.redhawk.ide.codegen.jet.cplusplus.gnuradio.template.component.PortImplCppTemplate;
import gov.redhawk.ide.codegen.jet.cplusplus.gnuradio.template.component.PortImplCppUsesTemplate;
import gov.redhawk.ide.codegen.jet.cplusplus.gnuradio.template.component.PortImplHProvidesTemplate;
import gov.redhawk.ide.codegen.jet.cplusplus.gnuradio.template.component.PortImplHTemplate;
import gov.redhawk.ide.codegen.jet.cplusplus.gnuradio.template.component.PortImplHUsesTemplate;

public class PortTemplate implements IScaPortCodegenTemplate {
	
	private List<String> interfaces = new ArrayList<String>();
	
	public PortTemplate() {};
	

	@Override
	public List<String> getExecutableFileNames(
			ImplementationSettings implSettings, SoftPkg softPkg,
			String language) {
		return new ArrayList<String>();
	}

	@Override
	public List<String> getAllGeneratedFileNames(
			ImplementationSettings implSettings, SoftPkg softPkg,
			String language) throws CoreException {
		return new ArrayList<String>();
	}

	@Override
	public String generateFile(String fileName, boolean providesPort,
			SoftPkg softPkg, ImplementationSettings implSettings,
			Object helperObject, String language) throws CoreException {
		
		String file = "";
		
		final TemplateParameter templ = (TemplateParameter) helperObject;
		
		templ.setGenSupport(true);
		
		if ( fileName.equals("port_impl.cpp")) {
			templ.setGenClassDef(false);
			templ.setGenClassImpl(true);
			file = new PortImplCppTemplate().generate(templ);
		} else if ( fileName.equals("port_impl.h")) {
			templ.setGenClassDef(true);
			templ.setGenClassImpl(false);
			file = new PortImplHTemplate().generate(templ);
		}
		
		return file;
	}

	@Override
	public String generateClassDefinition(String repId, boolean providesPort,
			SoftPkg softPkg, ImplementationSettings implSettings,
			Object helperObject, String language) throws CoreException {
		
		String file = "";
		
		final TemplateParameter templ = (TemplateParameter) helperObject;
		
		templ.setGenClassDef(true);
		templ.setGenClassImpl(false);
		templ.setGenSupport(false);
		templ.setProvidesPort(providesPort);
		templ.setPortRepId(repId);
		
		if ( providesPort ) {
			file = new PortImplHProvidesTemplate().generate(templ);
		} else {
			file = new PortImplHUsesTemplate().generate(templ);
		}
		
		return file;
	}

	@Override
	public String generateClassImplementation(String repId,
			boolean providesPort, SoftPkg softPkg,
			ImplementationSettings implSettings, Object helperObject,
			String language) throws CoreException {
		String file = "";
		
		final TemplateParameter templ = (TemplateParameter) helperObject;
		
		templ.setGenClassDef(false);
		templ.setGenClassImpl(true);
		templ.setGenSupport(false);
		templ.setProvidesPort(providesPort);
		templ.setPortRepId(repId);
		
		if ( providesPort ) {
			file = new PortImplCppProvidesTemplate().generate(templ);
		} else {
			file = new PortImplCppUsesTemplate().generate(templ);
		}
		
		return file;
	}

	@Override
	public String generateClassSupport(String repId, boolean providesPort,
			SoftPkg softPkg, ImplementationSettings implSettings,
			Object helperObject, String language) throws CoreException {
		String file = "";
		
		final TemplateParameter templ = (TemplateParameter) helperObject;
		
		templ.setGenClassDef(false);
		templ.setGenClassImpl(false);
		templ.setGenSupport(true);
		templ.setProvidesPort(providesPort);
		templ.setPortRepId(repId);
		
		if ( providesPort ) {
			file = new PortImplHProvidesTemplate().generate(templ);
		} else {
			file = new PortImplHUsesTemplate().generate(templ);
		}
		
		return file;
	}

	@Override
	public String generateClassInstantiator(String repId, boolean providesPort,
			SoftPkg softPkg, ImplementationSettings implSettings,
			Object helperObject, String language) throws CoreException {
		String file = "";
		
		final TemplateParameter templ = (TemplateParameter) helperObject;
		
		templ.setGenClassDef(false);
		templ.setGenClassImpl(false);
		templ.setGenSupport(false);
		templ.setProvidesPort(providesPort);
		templ.setPortRepId(repId);
		
		file = new PortImplHTemplate().generate(templ);

		return file;
	}

	@Override
	public boolean shouldGenerate(String language) {
		return true;
	}

	@Override
	public void setInterfaces(String[] interfaces) {
		this.interfaces = Arrays.asList(interfaces);

	}

}
