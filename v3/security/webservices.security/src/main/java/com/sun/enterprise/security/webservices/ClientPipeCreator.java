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

package com.sun.enterprise.security.webservices;

import java.util.HashMap;

import com.sun.xml.ws.api.pipe.Pipe;
import com.sun.xml.ws.assembler.ClientPipelineHook;
import com.sun.xml.ws.api.pipe.ClientPipeAssemblerContext;
import com.sun.xml.ws.policy.PolicyMap;

import com.sun.enterprise.deployment.ServiceReferenceDescriptor;
import com.sun.enterprise.security.jmac.config.ConfigHelper.AuthConfigRegistrationWrapper;

/**
 * This is used by WSClientContainer to return proper 196 security pipe
 * to the StandAlonePipeAssembler and TangoPipeAssembler
 */
public class ClientPipeCreator extends ClientPipelineHook {
    
    private ServiceReferenceDescriptor svcRef = null;
    
    public ClientPipeCreator(){
    }

    public ClientPipeCreator(ServiceReferenceDescriptor ref){
        svcRef = ref;
    }

    @Override
    public Pipe createSecurityPipe(PolicyMap map, 
            ClientPipeAssemblerContext ctxt, Pipe tail) {
        HashMap propBag = new HashMap();
        propBag.put(PipeConstants.POLICY, map);
        propBag.put(PipeConstants.WSDL_MODEL, ctxt.getWsdlModel());
        propBag.put(PipeConstants.SERVICE, ctxt.getService());
        propBag.put(PipeConstants.BINDING, ctxt.getBinding());
        propBag.put(PipeConstants.ENDPOINT_ADDRESS, ctxt.getAddress());
        propBag.put(PipeConstants.SERVICE_REF, svcRef);
	propBag.put(PipeConstants.NEXT_PIPE,tail);
        propBag.put(PipeConstants.CONTAINER,ctxt.getContainer());
        ClientSecurityPipe ret = new ClientSecurityPipe(propBag, tail);
        AuthConfigRegistrationWrapper listenerWrapper = ClientPipeCloser.getInstance().lookupListenerWrapper(svcRef);
        //there is a 1-1 mapping between Service_Ref and a ListenerWrapper
        if (listenerWrapper != null) {
            //override the listener that was created by the ConfigHelper CTOR :if one was already registered
            listenerWrapper.incrementReference();
            ret.getPipeHelper().setRegistrationWrapper(listenerWrapper);
        } else {
            //register a new listener
            ClientPipeCloser.getInstance().registerListenerWrapper(
                    svcRef, ret.getPipeHelper().getRegistrationWrapper());
        }
  
        return ret;
    }
}
