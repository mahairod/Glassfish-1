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
package com.sun.ejb;

import java.lang.reflect.Method;
import com.sun.enterprise.resource.ResourceHandle;

/**
 * Lifecycle contract for a single MessageBeanListener. Implemented
 * by the MessageBeanContainer and called by the MessageBeanClient for
 * each message delivery.  Each message delivery MUST call each of the
 * three methods exactly once, in the same thread, and in the following 
 * order :
 *
 * 1. beforeMessageDelivery
 * 2. deliverMessage
 * 3. afterMessageDelivery
 *
 * 
 * @author Kenneth Saks
 */
public interface MessageBeanListener {

    /**
     * Pre-delivery notification to the container.  Any transaction 
     * initialization is peformed here.  In addition, when this method 
     * returns, the current thread's context class loader will be set 
     * the message-bean's application class loader.  
     *
     * @param method is the method that will be invoked during deliverMessage.
     * It is used the container during transaction setup to lookup the 
     * appropriate transaction attribute.
     * @param txImported whether a transaction is being imported
     * 
     */
    void beforeMessageDelivery(Method method, boolean txImported);
    
    /**
     * Deliver a message to a message bean instance.
     *
     * @param params to use of the method invocation.  Can be null or
     * an 0-length array if there are 0 arguments.
     *
     * @throws Throwable  This is either an application exception as thrown
     * from the message bean instance or a javax.ejb.EJBException in the case
     * that the bean throws a system exception.  Note that exceptions are 
     * *always* propagated, regardless of transaction type.  
     */
    Object deliverMessage(Object[] params) throws Throwable;

    /**
     * Post-delivery notification to the container.  Container will perform
     * any work required to commit/rollback a container-managed transaction.
     * When this method returns, the thread's context class loader will be
     * restored to the value it had when beforeMessageDelivery was called.
     */
    void afterMessageDelivery();

    ResourceHandle getResourceHandle();

    void setResourceHandle(ResourceHandle handle);

}
