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
package com.sun.enterprise.management;

import java.io.File;
import java.io.PrintStream;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;


import com.sun.appserv.management.base.Util;
import com.sun.appserv.management.base.AMX;

import com.sun.appserv.management.helper.RefHelper;

import com.sun.appserv.management.util.misc.StringUtil;

import com.sun.enterprise.management.TestUtil;
import com.sun.enterprise.management.AMXTestBase;
import com.sun.enterprise.management.Capabilities;
import com.sun.enterprise.management.config.DanglingRefsTest;
import com.sun.enterprise.management.support.CoverageInfo;


/**
    This test should normally be run before the generic tests
    so that it can set up default items for many of the config elements
    so that the generic tests will actually test them. Otherwise,
    when the generic tests are run, they won't see any instances
    of many of the AMXConfig MBeans.
    <p>
    If there are errors doing this, disable this test in amxtest.classes,
    fix the error in the specific place it's occurring, then re-enabled
    this test.
 */
public final class RunMeLastTest extends AMXTestBase
{
		public
	RunMeLastTest( )
	{
	}
	
	    private void
	emitCoverage()
	    throws java.io.IOException
	{
	    final CoverageInfoAnalyzer  analyzer = 
	        new CoverageInfoAnalyzer( getDomainRoot() );
	    
	    final String summary    = analyzer.getCoverageSummary();
	    
	    final File  dataFile    = new File ("amx-tests.coverage");
	    final PrintStream  out = new PrintStream( dataFile );
	    out.println( summary );
	    out.close();
    
    	if ( getVerbose() )
        {
            trace( "NOTE: code coverage data save in file " +
                StringUtil.quote( "" + dataFile ) );
        }
	}
	
	    public void
	testLast()
	    throws Exception
	{
	    emitDanglingRefs();
	    
	    if ( getTestUtil().asAMXDebugStuff( getDomainRoot() ) != null )
	    {
	        emitCoverage();
	    }
	}
	
	    public static Capabilities
	getCapabilities()
	{
	    return getOfflineCapableCapabilities( true );
	}
	
	
	    public void
	emitDanglingRefs()
	    throws ClassNotFoundException
	{
	    new DanglingRefsTest().testAllDangling();
	}
}

















