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

package org.jvnet.glassfish.api;

import org.jvnet.hk2.annotations.Contract;

/**
 * A Startup service is an HK2 component invoked at application server startup.
 * Implementation of this interface will be run during the application server 
 * startup sequence. To do so, an implementation of this interface must also 
 * declare itself using META-INF/services file as described in the JDK 
 * jar file specification.
 *
 * @author Jerome Dochez
 */
@Contract
public interface Startup {
  
    /** 
     * A startup service may be useful during the lifetime of the application 
     * server, while others need to process a task and stop running at the 
     * end of the server startup. 
     * A startup service should indicate if it needs to be running during the 
     * START sequence only or during the SERVER lifetime.
     */
    public enum Lifecycle { START, SERVER }    
    
    /**
     * Returns the life expectency of the service
     * @return the life expectency.
     */
    public Lifecycle getLifecycle();
}
