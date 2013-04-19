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

import java.util.ArrayList;
import java.util.List;

import mil.jpeojtrs.sca.prf.Properties;
import mil.jpeojtrs.sca.spd.SoftPkg;

import org.eclipse.core.runtime.CoreException;

import gov.redhawk.ide.codegen.IScaComponentCodegenTemplate;
import gov.redhawk.ide.codegen.ImplementationSettings;
import gov.redhawk.ide.codegen.jet.TemplateParameter;
import gov.redhawk.ide.codegen.jet.cplusplus.gnuradio.template.BuildShTemplate;
import gov.redhawk.ide.codegen.jet.cplusplus.gnuradio.template.ConfigureAcTemplate;
import gov.redhawk.ide.codegen.jet.cplusplus.gnuradio.template.MakefileAmTemplate;
import gov.redhawk.ide.codegen.jet.cplusplus.gnuradio.template.ReconfTemplate;
import gov.redhawk.ide.codegen.jet.cplusplus.gnuradio.template.component.MainCppTemplate;
import gov.redhawk.ide.codegen.jet.cplusplus.gnuradio.template.component.RHProcessThreadTemplate;
import gov.redhawk.ide.codegen.jet.cplusplus.gnuradio.template.component.GnuHawkBlockTemplate;
import gov.redhawk.ide.codegen.jet.cplusplus.gnuradio.template.component.PortImplCppTemplate;
import gov.redhawk.ide.codegen.jet.cplusplus.gnuradio.template.component.PortImplHTemplate;
import gov.redhawk.ide.codegen.jet.cplusplus.gnuradio.template.component.ResourceBaseCppTemplate;
import gov.redhawk.ide.codegen.jet.cplusplus.gnuradio.template.component.ResourceBaseHTemplate;
import gov.redhawk.ide.codegen.jet.cplusplus.gnuradio.template.component.ResourceCppTemplate;
import gov.redhawk.ide.codegen.jet.cplusplus.gnuradio.template.component.ResourceHTemplate;
import gov.redhawk.ide.codegen.jet.cplusplus.gnuradio.template.component.StructPropsGRHTemplate;
import gov.redhawk.ide.codegen.jet.template.ResourceSpecTemplate;
import gov.redhawk.ide.codegen.util.CodegenFileHelper;

public class GNURadioTemplate implements IScaComponentCodegenTemplate {

	@Override
	public List<String> getExecutableFileNames(
			ImplementationSettings implSettings, SoftPkg softPkg) {
		// TODO Auto-generated method stub
		final List<String> fileNames = new ArrayList<String>();
		fileNames.add("build.sh");
		fileNames.add("reconf");
		return fileNames; 
	}

	@Override
	public List<String> getAllGeneratedFileNames(
			ImplementationSettings implSettings, SoftPkg softPkg) {
		final List<String> fileNames = new ArrayList<String>();
		final String prefix = CodegenFileHelper.getPreferredFilePrefix(softPkg,implSettings);
		fileNames.add("build.sh");
		fileNames.add("reconf");
		fileNames.add("configure.ac");
		fileNames.add("Makefile.am");  //TODO need to add GNUHawk Library to LDADD
		fileNames.add("main.cpp");
		fileNames.add("RH_ProcessThread.h");
		fileNames.add("port_impl.cpp");
		fileNames.add("port_impl.h");
		// TODO need spec file with GNUHawk Library dependency fileNames.add("../"+prefix+".spec");
		fileNames.add(prefix + "_GnuHawkBlock.h");
		fileNames.add(prefix + "_base.cpp");
		fileNames.add(prefix + "_base.h");
		fileNames.add(prefix + ".cpp");
		fileNames.add(prefix + ".h");
		final Properties properties = softPkg.getPropertyFile().getProperties();
		if ( (properties != null) && ( properties.getStruct() != null ) ) {
			fileNames.add("struct_props.h");
		}
		return fileNames; 
	}

	@Override
	public String generateFile(String fileName, SoftPkg softPkg,
			ImplementationSettings implSettings, Object helperObject)
			throws CoreException {

			final TemplateParameter templ = (TemplateParameter)helperObject;
			final String prefix = CodegenFileHelper.getPreferredFilePrefix(softPkg, implSettings);
			String file = "";
			
			if ( fileName.equals("main.cpp")) {
				file = new MainCppTemplate().generate(templ);
			} else if ( fileName.equals( prefix + "_base.h")) {
				file = new ResourceBaseHTemplate().generate(templ);
			} else if ( fileName.equals( prefix + "_base.cpp")) {
				file = new ResourceBaseCppTemplate().generate(templ);
			} else if ( fileName.equals( prefix + ".cpp")) {
				file = new ResourceCppTemplate().generate(templ);
			} else if ( fileName.equals( prefix + ".h")) {
				file = new ResourceHTemplate().generate(templ);
			} else if ( fileName.equals( "port_impl.cpp")) {
				file = new PortImplCppTemplate().generate(templ);
			} else if ( fileName.equals( "port_impl.h")) {
				file = new PortImplHTemplate().generate(templ);
			} else if ( fileName.equals( "configure.ac")) {
				file = new ConfigureAcTemplate().generate(templ);
			} else if ( fileName.equals( "Makefile.am")) {
				file = new MakefileAmTemplate().generate(templ);
			} else if ( fileName.equals( "build.sh")) {
				file = new BuildShTemplate().generate(templ);
			} else if ( fileName.equals( "reconf")) {
				file = new ReconfTemplate().generate(templ);
			} else if ( fileName.equals( "struct_props.h")) {
				file = new StructPropsGRHTemplate().generate(templ);
			} else if ( fileName.equals( "../" + prefix + ".spec")) {
				file = new ResourceSpecTemplate().generate(templ);
			} else if ( fileName.equals(  prefix + "_GnuHawkBlock.h")) {
				file = new GnuHawkBlockTemplate().generate(templ);
			} else if ( fileName.equals( "RH_ProcessThread.h")) {
				file = new RHProcessThreadTemplate().generate(templ);
			}
						
			return file;
	}

	@Override
	public boolean shouldGenerate() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getDefaultFilename(SoftPkg softPkg,
			ImplementationSettings implSettings, String srcDir) {
		
		final String prefix = CodegenFileHelper.getPreferredFilePrefix(softPkg, implSettings);
		return srcDir+ prefix + ".cpp";

	}

}
