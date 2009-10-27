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

package com.sun.enterprise.iiop.security;

import com.sun.logging.LogDomains;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.api.admin.ProcessEnvironment;
import org.glassfish.api.admin.ProcessEnvironment.ProcessType;
import org.glassfish.enterprise.iiop.api.IIOPInterceptorFactory;
import org.jvnet.hk2.annotations.Inject;
import org.jvnet.hk2.annotations.Scoped;
import org.jvnet.hk2.annotations.Service;
import org.jvnet.hk2.component.Habitat;
import org.jvnet.hk2.component.Singleton;
import org.omg.CORBA.ORB;
import org.omg.IOP.Codec;
import org.omg.PortableInterceptor.ClientRequestInterceptor;
import org.omg.PortableInterceptor.IORInterceptor;
import org.omg.PortableInterceptor.ORBInitInfo;
import org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName;
import org.omg.PortableInterceptor.ServerRequestInterceptor;

/**
 *
 * @author Kumar
 */
@Service(name="ServerSecurityInterceptorFactory")
@Scoped(Singleton.class)
public class SecurityIIOPInterceptorFactory implements IIOPInterceptorFactory{

    private static Logger _logger = null;
    static {
        _logger = LogDomains.getLogger(SecurityIIOPInterceptorFactory.class, LogDomains.SECURITY_LOGGER);
    }
    private ClientRequestInterceptor creq;
    private ServerRequestInterceptor sreq;
    private SecIORInterceptor sior;
    
    @Inject
    private ProcessEnvironment penv;
    @Inject
    private Habitat habitat;
    @Inject(optional=true)
    private AlternateSecurityInterceptorFactory altSecFactory;
    
    // are we supposed to add the interceptor and then return or just return an instance ?.
    public ClientRequestInterceptor createClientRequestInterceptor(ORBInitInfo info, Codec codec) {
        if (!penv.getProcessType().isServer()) {
            return null;
        }
        if(altSecFactory != null){
            return altSecFactory.getClientRequestInterceptor(codec);
        }
        ClientRequestInterceptor ret = getClientInterceptorInstance(codec);
        return ret;
    }

    public ServerRequestInterceptor createServerRequestInterceptor(ORBInitInfo info, Codec codec) {
        ServerRequestInterceptor ret = null;
        try {
            if (!penv.getProcessType().isServer()) {
                return null;
            }
            if(altSecFactory != null){
                ret = altSecFactory.getServerRequestInterceptor(codec);
            }else{
                ret = getServerInterceptorInstance(codec);
            }
            //also register the IOR Interceptor here
            com.sun.corba.ee.spi.legacy.interceptor.ORBInitInfoExt infoExt = (com.sun.corba.ee.spi.legacy.interceptor.ORBInitInfoExt)info;
            IORInterceptor secIOR = getSecIORInterceptorInstance(codec, infoExt.getORB());
            info.add_ior_interceptor(secIOR);
            
        } catch (DuplicateName ex) {
            _logger.log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
        return ret;
    }
    
    private synchronized ClientRequestInterceptor getClientInterceptorInstance(Codec codec) {
        if (creq == null) {
            creq = new SecClientRequestInterceptor(
                "SecClientRequestInterceptor", codec, habitat);
        }
        return creq;
    }
    
     private synchronized ServerRequestInterceptor getServerInterceptorInstance(Codec codec) {
        if (sreq == null) {
            sreq = new SecServerRequestInterceptor(
                    "SecServerRequestInterceptor", codec, habitat);
        }
        return sreq;
    }

    private synchronized IORInterceptor getSecIORInterceptorInstance(Codec codec, ORB orb) {
        if (sior == null) {
            sior = new SecIORInterceptor(codec, habitat, orb);
        }
        return sior;
    }
}
