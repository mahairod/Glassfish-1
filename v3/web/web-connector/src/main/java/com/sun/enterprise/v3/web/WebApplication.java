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

package com.sun.enterprise.v3.web;

import org.jvnet.glassfish.api.deployment.ApplicationContainer;
import org.apache.catalina.core.StandardContext;

public class WebApplication implements ApplicationContainer {

    WebContainer container;
    StandardContext ctx;

    public WebApplication(WebContainer container, StandardContext ctx) {
        this.container = container;
        this.ctx = ctx;
    }

    public boolean start() {
        return true;
    }

    public boolean stop() {
        return false;
    }

    /**
     * Returns the class loader associated with this application
     *
     * @return ClassLoader for this app
     */
    public ClassLoader getClassLoader() {
        return ctx.getLoader().getClassLoader();
    }

    WebContainer getContainer() {
        return container;
    }

}
