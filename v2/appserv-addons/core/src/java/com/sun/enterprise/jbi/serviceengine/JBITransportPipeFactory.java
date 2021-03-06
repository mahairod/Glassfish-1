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
package com.sun.enterprise.jbi.serviceengine;

import com.sun.enterprise.deployment.ServiceRefPortInfo;
import com.sun.enterprise.deployment.NameValuePairDescriptor;
import com.sun.enterprise.webservice.ServiceEngineRtObjectFactory;
import com.sun.enterprise.webservice.ServiceEngineUtil;
import com.sun.enterprise.webservice.ServiceEngineFacade;
import com.sun.enterprise.webservice.WSClientContainer;
import com.sun.enterprise.Switch;
import com.sun.enterprise.InvocationManager;
import com.sun.enterprise.ComponentInvocation;
import com.sun.xml.ws.api.pipe.ClientPipeAssemblerContext;
import com.sun.xml.ws.api.pipe.Pipe;
import com.sun.xml.ws.api.pipe.TransportPipeFactory;

import javax.xml.namespace.QName;
import java.net.URL;

/**
 * This Factory class is used by the JAX-WS client runtime to create TransportPipe.
 * This factory is configured using the standard JAR services framework. The file
 * "META-INF/services/com.sun.xml.ws.api.pipe.TransportPipeFactory" is used for
 * this purpose. It is put in appserv-rt.jar so that it is available in the 
 * appserver classpath.
 * 
 * @author Vikas Awasthi
 */
public class JBITransportPipeFactory extends TransportPipeFactory {

    /**
     * If jbi-enabled property in the context is true or if a composite 
     * application with this consumer endpoint is deployed then a 
     * JBITransportPipe is created otherwise null is returned. 
     * Returning null from this method allows JAX-WS to use the normal 
     * invocation path by creating other relevant Tranport pipe.
     */
    public Pipe doCreate(ClientPipeAssemblerContext context) {
        
        //if service engine is not installed then return null
        ServiceEngineFacade facade =
                ServiceEngineRtObjectFactory.getInstance().getFacade();
        if(facade == null)
            return null;

        InvocationManager invmgr =
           Switch.getSwitch().getInvocationManager();
        ComponentInvocation inv = invmgr.getCurrentInvocation();
        if (inv == null || inv.getInstance() == null) {
           return null;
        }

        // ServiceEngineUtil is used here because a package level variable of 
        // WSClientContainer is used to get the portInfo
        //For all jsr109 invocations container will be WSClientContainer. The
        //normal invocations need to check if there is a composite application.
        ServiceRefPortInfo portInfo =
                (context.getContainer() instanceof WSClientContainer)?
                        ServiceEngineUtil.getPortInfo((WSClientContainer)context.getContainer(),
                                                    context.getWsdlModel().getName()):
                        null;

        if(portInfo == null) {
            if(hasRegisteredEndpointInJBI(facade, context))
                return createJBITransportPipe(facade, context);
            return null;
        }

        NameValuePairDescriptor stubProp =
                                portInfo.getStubPropertyByName("jbi-enabled");
        // In a composite application jbi-enabled flag can be used to override 
        // the client routing logic 
        if(stubProp == null) {
            if(hasRegisteredEndpointInJBI(facade, context))
                return createJBITransportPipe(facade, context);
            return null;
        }

        String jbi_enabled = stubProp.getValue();

        if(jbi_enabled.equalsIgnoreCase("true")) {
                return createJBITransportPipe(facade, context);
        }
        return null;
    }

    /**
     * Check whether the composite application has registered an endpoint for 
     * this client
     */
    private boolean hasRegisteredEndpointInJBI(ServiceEngineFacade facade,
                                               ClientPipeAssemblerContext context) {
        QName serviceName = context.getService().getServiceName();
        String endpointName = context.getWsdlModel().getName().getLocalPart();

        return facade.hasConsumerEP(serviceName, endpointName);
    }
    
    private Pipe createJBITransportPipe(ServiceEngineFacade facade, 
                                        ClientPipeAssemblerContext context) {
        URL wsdlURL = context.getService().getWSDLDocumentLocation();
        QName service = context.getService().getServiceName();
        return facade.createJBITransportPipe(context.getBinding(),
                                            wsdlURL,
                                            service,
                                            context.getWsdlModel());
    }
}
