/*
 * The contents of this file are subject to the terms 
 * of the Common Development and Distribution License 
 * (the License).  You may not use this file except in
 * compliance with the License.
 * 
 * You can obtain a copy of the license at 
 * https://glassfish.dev.java.net/public/CDDLv1.0.html or
 * glassfish/bootstrap/legal/CDDLv1.0.txt.
 * See the License for the specific language governing 
 * permissions and limitations under the License.
 * 
 * When distributing Covered Code, include this CDDL 
 * Header Notice in each file and include the License file 
 * at glassfish/bootstrap/legal/CDDLv1.0.txt.  
 * If applicable, add the following below the CDDL Header, 
 * with the fields enclosed by brackets [] replaced by
 * you own identifying information: 
 * "Portions Copyrighted [year] [name of copyright owner]"
 * 
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 */
package com.sun.enterprise.v3.admin;

import com.sun.enterprise.util.LocalStringManagerImpl;
import com.sun.enterprise.v3.server.Init;
import org.jvnet.glassfish.api.Async;
import org.jvnet.glassfish.api.I18n;
import org.jvnet.glassfish.api.Startup;
import org.jvnet.glassfish.api.admin.AdminCommand;
import org.jvnet.glassfish.api.admin.AdminCommandContext;
import org.jvnet.hk2.annotations.Inject;
import org.jvnet.hk2.annotations.Service;
import org.jvnet.hk2.component.Habitat;
import org.jvnet.hk2.component.Inhabitant;
import org.jvnet.hk2.component.ComponentException;
/**
 * AdminCommand to stop the domain execution which mean shuting down the application
 * server.
 *
 * @author Jerome Dochez
 */
@Service(name="stop-domain")
@Async
@I18n("stop.domain.command")
public class StopDomainCommand implements AdminCommand {

    final private static LocalStringManagerImpl localStrings = new LocalStringManagerImpl(StopDomainCommand.class);
    
    @Inject
    Habitat habitat;

    /**
     * Shutdown of the application server : 
     *
     * All running services are stopped. 
     * LookupManager is flushed.
     */
    public void execute(AdminCommandContext context) {
         
        context.getLogger().info(localStrings.getLocalString("stop.domain.init","Server shutdown initiated"));
        try {
            for (Inhabitant<? extends Startup> svc : habitat.getInhabitants(Startup.class)) {
                svc.release();
            }

            for (Inhabitant<? extends Init> svc : habitat.getInhabitants(Init.class)) {
                svc.release();
            }

        } catch(ComponentException e) {
            // do nothing.
        }
    }
}
