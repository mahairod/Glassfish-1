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

package com.sun.enterprise.tools.verifier.tests.ejb.ias.cmpmapping;


import java.util.*;
import com.sun.enterprise.deployment.EjbDescriptor;
import com.sun.enterprise.deployment.EjbSessionDescriptor;
import com.sun.enterprise.tools.verifier.*;
import com.sun.enterprise.tools.verifier.tests.*;

import com.sun.enterprise.tools.verifier.tests.ejb.EjbCheck;

import com.sun.enterprise.tools.common.dd.ejb.SunEjbJar;
import com.sun.enterprise.tools.common.dd.ejb.Ejb;
import com.sun.enterprise.tools.common.dd.ResourceRef;
import com.sun.enterprise.tools.common.dd.DefaultResourcePrincipal;
import com.sun.enterprise.deployment.ResourceReferenceDescriptor;
import com.sun.enterprise.tools.verifier.tests.ejb.*;
import com.sun.enterprise.tools.common.dd.cmpmapping.*;

public class ASCmpMappingTest extends EjbTest implements EjbCheck {

    public Result check(EjbDescriptor descriptor)
    {
	Result result = getInitializedResult();
	ComponentNameConstructor compName = new ComponentNameConstructor(descriptor);
        boolean oneFailed = false;
        boolean notApp = false;

        //<addition author="irfan@sun.com" [bug/rfe]-id="4715917" >
        /*Set keys = descriptor.getEjbBundleDescriptor().getIasCmpMappingsKeys();

        SunCmpMappings iasCmpMappings=null;
        String forPmConfig=null;*/
        SunCmpMappings iasCmpMappings = descriptor.getEjbBundleDescriptor().getIasCmpMappings();
        String forPmConfig = com.sun.enterprise.deployment.EjbBundleXmlReader.IAS_CMP_MAPPING_JAR_ENTRY;
        //</addition>

        SunCmpMapping[] allIasCmpMapping=null;
        SunCmpMapping iasCmpMapping=null;

        /////test vars
        String schema=null;


        //<addition author="irfan@sun.com" [bug/rfe]-id="4715917" >
        if(iasCmpMappings != null)
        {
        /*if(keys!=null && keys.size()>0)
        {
            Iterator it = keys.iterator();
            while(it.hasNext()) {

                forPmConfig=(String) it.next();
                iasCmpMappings= descriptor.getEjbBundleDescriptor().getIasCmpMappings(forPmConfig);*/
        //</addition>
                allIasCmpMapping=iasCmpMappings.getSunCmpMapping();

                for(int rep=0;rep<allIasCmpMapping.length;rep++){

                      iasCmpMapping=allIasCmpMapping[rep];

                      //test logic
                      schema=iasCmpMapping.getSchema();
                      if(validateSchema(schema)){

                      result.passed(smh.getLocalString(getClass().getName()+".passed",
                            "PASSED [AS-CMP-MAPPING] : schema [{0} ] is a valid entry, in the mapping file [ {1}], "+
                            "of the ejb archive [ {2} ]. ",
                            new Object[]{schema,forPmConfig,descriptor.getName()}));


                      }else{
                      oneFailed = true;

                      result.failed(smh.getLocalString(getClass().getName()+".failed",
                            "FAILED [AS-CMP-MAPPING] : schema [{0} ] is  NOT a valid entry, in the mapping file [ {1}], "+
                            "of the ejb archive [ {2} ]. "+
                            "Either  null or empty.",
                            new Object[]{schema,forPmConfig,descriptor.getName()}));


                      }



                }


            //} 4715917 irfan

        }
        else
        {
            notApp = true;
            result.notApplicable(smh.getLocalString
                                 (getClass().getName() + ".notApplicable",
                                  "NOT APPLICABLE [AS-CMP-MAPPING] : There is no ias-cmp-mappings file present, within the ejb archive [ {0} ].",
                                  new Object[] {descriptor.getName()}));

        }

        if (oneFailed) {
            result.setStatus(Result.FAILED);
        } else if(notApp) {
            result.setStatus(Result.NOT_APPLICABLE);
        }else {
            result.setStatus(Result.PASSED);
        }


        return result;
    }

    boolean validateSchema(String schema){
        boolean valid=false;
        if(schema !=null && !schema.trim().equals("")){
            valid =true;
        }
        return valid;
    }
}

