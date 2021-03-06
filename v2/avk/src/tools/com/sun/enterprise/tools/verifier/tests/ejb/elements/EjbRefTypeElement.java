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
package com.sun.enterprise.tools.verifier.tests.ejb.elements;

import com.sun.enterprise.tools.verifier.tests.ejb.EjbTest;
import com.sun.enterprise.tools.verifier.tests.ejb.EjbCheck;
import java.util.*;
import com.sun.enterprise.deployment.*;
import com.sun.enterprise.tools.verifier.*;
import com.sun.enterprise.tools.verifier.tests.*;

import com.sun.enterprise.tools.verifier.XpathPrefixResolver;

/** 
 * The ejb-ref-type element must be one of the following:
 *   Entity
 *   Session
 */
public class EjbRefTypeElement extends EjbTest implements EjbCheck { 

    // Logger to log messages
    /**
     * The ejb-ref-type element must be one of the following:
     *   Entity
     *   Session
     *
     * @param descriptor the Enterprise Java Bean deployment descriptor
     *   
     * @return <code>Result</code> the results for this assertion
     */
    public Result check(EjbDescriptor descriptor) {

	Result result = getInitializedResult();
	ComponentNameConstructor compName = getVerifierContext().getComponentNameConstructor();
	boolean failed = false;

	//The ejb-ref-type element must be one of the following:
	// Entity
	// Session
	if (!descriptor.getEjbReferenceDescriptors().isEmpty()) {
	    for (Iterator itr = descriptor.getEjbReferenceDescriptors()
                .iterator(); itr.hasNext();) {
		EjbReferenceDescriptor nextEjbReference = 
                (EjbReferenceDescriptor) itr.next();

                // Need to use XPath, because if DOL sees an inconsistent
                // ref-type, it gives a warning and changes the type
		//String ejbRefTypeStr = nextEjbReference.getType();
                String refStr = (nextEjbReference.isLocal()) ? 
                                "ejb-local-ref" : "ejb-ref";
                String beanType = (descriptor.getType()).toLowerCase();
                String xpathQuery = null;
                if (getVerifierContext().getDocument().getDoctype() != null) {
                   xpathQuery = "/ejb-jar/enterprise-beans/" +
                   beanType + "[ejb-name=\"" + descriptor.getName() 
                   +"\"]/" + refStr + "[ejb-ref-name=\"" 
                   + nextEjbReference.getName() + "\"]/ejb-ref-type";
                }
                else {
                   String prefix = XpathPrefixResolver.fakeXPrefix;
                   xpathQuery = prefix + ":" + "ejb-jar/" + 
                    prefix + ":" + "enterprise-beans/" +
                    prefix + ":" + beanType + 
                    "[" + prefix + ":ejb-name=\"" + descriptor.getName() 
                    +"\"]/" + 
                    prefix + ":" + refStr + "[" + prefix + ":ejb-ref-name=\"" 
                    + nextEjbReference.getName() + "\"]/" +
                    prefix + ":" + "ejb-ref-type";
                }
           
		String ejbRefTypeStr = getXPathValueForNonRuntime(xpathQuery);
                EjbDescriptor rdesc = nextEjbReference.getEjbDescriptor();

                /*if (rdesc == null) {
                   logger.log(Level.SEVERE, getClass().getName() + ".Warn",
                   new Object[] {nextEjbReference.getBeanClassName()});
                }*/

                // XPath queries seem to fail for XSD Descriptors
                if (ejbRefTypeStr == null) {
                   ejbRefTypeStr = nextEjbReference.getType();
                } 

	        if (!((ejbRefTypeStr.equals(EjbSessionDescriptor.TYPE)) ||
		        (ejbRefTypeStr.equals(EjbEntityDescriptor.TYPE)))) {
		      result.addErrorDetails(smh.getLocalString
					   ("tests.componentNameConstructor",
					    "For [ {0} ]",
					    new Object[] {compName.toString()}));
		      result.failed(smh.getLocalString
				  (getClass().getName() + ".failed",
				   "Error: ejb-ref-type [ {0} ] within \n bean [ {1} ] is not valid.  \n Must be [ {2} ] or [ {3} ]",
				   new Object[] {ejbRefTypeStr,descriptor.getName(),EjbEntityDescriptor.TYPE,EjbSessionDescriptor.TYPE}));
		      failed = true;
		  }
                  else if (rdesc != null ) {
                    String actualRefType = rdesc.getType();
                    if (!ejbRefTypeStr.equals(actualRefType)) {
                       result.addErrorDetails(smh.getLocalString
                                           ("tests.componentNameConstructor",
                                            "For [ {0} ]",
                                            new Object[] {compName.toString()}));
                        result.failed(smh.getLocalString
                           (getClass().getName() + ".failed2",
                            "Error: ejb-ref-type [ {0} ] was specifed for ejb-ref [ {1} ], within bean [ {2} ], when it should have been [ {3} ].",
                            new Object[] {ejbRefTypeStr, 
                            nextEjbReference.getName(), 
                            descriptor.getName(), actualRefType}));
                    failed = true;
                  }
	      }
            }
	} else {
	    result.addNaDetails(smh.getLocalString
				("tests.componentNameConstructor",
				 "For [ {0} ]",
				 new Object[] {compName.toString()}));
	    result.notApplicable(smh.getLocalString
				 (getClass().getName() + ".notApplicable",
				  "There are no ejb references to other beans within this bean [ {0} ]",
				  new Object[] {descriptor.getName()}));
	    return result;
	}

	if (failed)
	    {
		result.setStatus(Result.FAILED);
	    } else {
		result.addGoodDetails(smh.getLocalString
				      ("tests.componentNameConstructor",
				       "For [ {0} ]",
				       new Object[] {compName.toString()}));
		result.passed
		    (smh.getLocalString
		     (getClass().getName() + ".passed",
		      "All ejb-ref-type elements are valid.  They are all [ {0} ] or [ {1} ] within this bean [ {2} ]",
		      new Object[] {EjbEntityDescriptor.TYPE,EjbSessionDescriptor.TYPE,descriptor.getName()}));
	    } 
	return result;

    }
}
