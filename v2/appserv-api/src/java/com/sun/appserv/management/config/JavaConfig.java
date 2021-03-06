/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
 * 
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License. You can obtain
 * a copy of the License at https://glassfish.dev.java.net/public/CDDL+GPL.html
 * or glassfish/bootstrap/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at glassfish/bootstrap/legal/LICENSE.txt.
 * Sun designates this particular file as subject to the "Classpath" exception
 * as provided by Sun in the GPL Version 2 section of the License file that
 * accompanied this code.  If applicable, add the following below the License
 * Header, with the fields enclosed by brackets [] replaced by your own
 * identifying information: "Portions Copyrighted [year]
 * [name of copyright owner]"
 * 
 * Contributor(s):
 * 
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
 
/*
 * $Header: /cvs/glassfish/appserv-api/src/java/com/sun/appserv/management/config/JavaConfig.java,v 1.1 2006/12/02 06:03:12 llc Exp $
 * $Revision: 1.1 $
 * $Date: 2006/12/02 06:03:12 $
 */

package com.sun.appserv.management.config;

import java.util.Map;

import com.sun.appserv.management.base.XTypes;
import com.sun.appserv.management.base.Container;

/**
	 Configuration for the &lt;java-config&gt; element.
*/

public interface JavaConfig
	extends ConfigElement, PropertiesAccess, Container
{
/** The j2eeType as returned by {@link com.sun.appserv.management.base.AMX#getJ2EEType}. */
	public static final String	J2EE_TYPE	= XTypes.JAVA_CONFIG;
	
	public String	getBytecodePreprocessors();
	public void	setBytecodePreprocessors( String value );

	public String	getClasspathPrefix();
	public void	setClasspathPrefix( String value );

	public String	getClasspathSuffix();
	public void	setClasspathSuffix( String value );

	public boolean	getDebugEnabled();
	public void	setDebugEnabled( boolean value );

	public String	getDebugOptions();
	public void	setDebugOptions( String value );

	public boolean	getEnvClasspathIgnored();
	public void	setEnvClasspathIgnored( boolean value );

	public String	getJavaHome();
	public void	setJavaHome( String value );

	public String	getJavacOptions();
	public void	setJavacOptions( String value );


    /**
        @since AppServer 9.0
     */
    public String   getSystemClasspath();
    
    /**
        @since AppServer 9.0
     */
    public void     setSystemClasspath( String classpath );
    
	public String[]	getJVMOptions();
	
	/**
        Overwrites existing jvm options with the new options.
        If the intent is to append the new options the caller needs to first get 
        the existing jvm options using <a>#getJVMOptions</a>, append new 
        options and set the resulting whole using this method.
        <p>
        If a JVM option contains a space or tab, you must enclose
        it in quotes eg </code>"C:Program Files\dir"</code>
	 */
	public void	setJVMOptions( String[] value );

	public String	getNativeLibraryPathPrefix();
	public void	setNativeLibraryPathPrefix( String value );

	public String	getNativeLibraryPathSuffix();
	public void	setNativeLibraryPathSuffix( String value );

	public String	getRMICOptions();
	public void	setRMICOptions( String value );

	public String	getServerClasspath();
	public void	setServerClasspath( String value );


// -------------------- Operations --------------------
	/**
		Get the ProfilerConfig MBean.
	 */
	public ProfilerConfig	getProfilerConfig();

	/**
		Creates a profiler element.
		Although a name is specified, only one profiler may exist.

		@param name		identifier
		@param optional	Map of optional attributes.  See {@link ProfilerConfigKeys}
		@return A proxy to the ProfilerConfig MBean.
	 */
	public ProfilerConfig	createProfilerConfig( String name, Map<String,String> optional );

	/**
		Removes profiler element.
	 */
	public void			removeProfilerConfig();
}
