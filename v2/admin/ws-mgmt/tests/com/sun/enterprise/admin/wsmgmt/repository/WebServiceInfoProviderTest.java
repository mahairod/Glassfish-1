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
package com.sun.enterprise.admin.wsmgmt.repository;

import com.sun.enterprise.admin.wsmgmt.repository.spi.RepositoryException;
import com.sun.enterprise.admin.wsmgmt.repository.spi.WebServiceInfoProvider;
import com.sun.enterprise.admin.wsmgmt.repository.spi.WebServiceInfo;
import com.sun.enterprise.tools.common.AppServWebServiceInfoProvider;
import com.sun.enterprise.config.serverbeans.Domain;

import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class WebServiceInfoProviderTest extends TestCase {
   
    public WebServiceInfoProviderTest(String name) {
        super(name);        
    }       

    public void testWSInfos() throws RepositoryException {                        
        try {
           Map map = new HashMap();
           map.put( WebServiceInfoProvider.MOD_TYPE_PROP_NAME, "WEB");
           map.put( WebServiceInfoProvider.WS_XML_LOCATION_PROP_NAME, 
                    WS_XML_FILE);
           map.put( WebServiceInfoProvider.APP_ID_PROP_NAME, "jaxrpc-simple");
           map.put( WebServiceInfoProvider.BUNDLE_NAME_PROP_NAME, 
                    "jaxrpc-simple.war");
           List list  = impl.getWebServiceInfo(WEB_XML_FILE, map); 
           Iterator hItr = list.iterator();
           while ( hItr.hasNext() ) {
                Object m = hItr.next();
                System.out.println(" Web Service Info " + m.toString());
           }
        } catch (RepositoryException re) {
            re.printStackTrace();
            throw re;
        }
    }

  
    protected void setUp() {
        impl = new AppServWebServiceInfoProvider();
    }

    private final static String EJB_XML_FILE = 
                    "/tmp/com/sun/enterprise/admin/wsmgmt/sun-ejb-jar.xml";
    private final static String WEB_XML_FILE = 
                    "/tmp/com/sun/enterprise/admin/wsmgmt/sun-web.xml";
    private final static String WS_XML_FILE = 
                    "/tmp/com/sun/enterprise/admin/wsmgmt/webservices.xml";

    private AppServWebServiceInfoProvider impl = null;

    public static void main(String args[]) {
        junit.textui.TestRunner.run(WebServiceInfoProviderTest.class);
    }
}
