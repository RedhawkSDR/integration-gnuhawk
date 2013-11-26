/@@@AC_CONFIG_SUBDIRS@@@/ {
		   s/@@@AC_CONFIG_SUBDIRS@@@/AC_CONFIG_SUBDIRS\( \[ \\/
		   r cfg.cdirs.out
		   a\
		   \]\)
		   }
/^$/		   p
