/^.*@@@GH_COMPS@@@/ {
	           s/@@@GH_COMPS@@@/\\/
		   r mk.comp.out
		   }

/^.*@@@QA_COMPS@@@/ {
	           s/@@@QA_COMPS@@@/\\/
		   r mk.qa.out
		   }
/^$/		   p
