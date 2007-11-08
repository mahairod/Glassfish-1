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

package org.jvnet.glassfish.api.container;

import org.jvnet.hk2.annotations.Contract;

/**
 * Contract interface for registering adapters to a port. Each
 * adapter listens to a particular context root. Context root
 * can be / which makes this adapter the default web application
 *
 * @author Jerome Dochez
 *
 */
@Contract
public interface Adapter extends com.sun.grizzly.tcp.Adapter {

    /**
     * Returns the context root for this adapter
     * @return context root
     */
    public String getContextRoot();
}
