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
package com.sun.enterprise;

import com.sun.enterprise.deployment.JndiNameEnvironment;

/**
 * InjectionManager provides runtime resource injection(@Resource, @EJB, etc.) 
 * and generic callback(PostConstruct/PreDestroy) services  It performs 
 * the actual injection into the fields and methods of designated
 * J2EE 5 component instances and managed class instances.  The decision 
 * as to when injection takes place is determined by the caller.
 *
 * @author Kenneth Saks
 */
public interface InjectionManager {

    /**
     * Inject the given object instance with the resources from its
     * component environment.  The applicable component naming environment
     * information will be retrieved from the current invocation context.
     *
     * Any @PostConstruct methods on the instance's class(and super-classes)
     * will be invoked after injection.  
     *
     * @exception InjectionException Thrown if an error occurs during injection
     * 
     */
    public void injectInstance(Object instance)
        throws InjectionException;

    /**
     * Inject the injectable resources from the given component environment
     * into an object instance. The specified componentEnv must match the
     * environment that is associated with the component on top of the
     * invocation stack at the time this method is invoked.
     *
     * Any @PostConstruct methods on the instance's class(and super-classes)
     * will be invoked after injection.  
     *
     *
     * @exception InjectionException Thrown if an error occurs during injection
     * 
     */
    public void injectInstance(Object instance, 
                               JndiNameEnvironment componentEnv)
        throws InjectionException;

    /**
     * Inject the injectable resources from the given component environment
     * into an object instance. The specified componentEnv must match the
     * environment that is associated with the component on top of the
     * invocation stack at the time this method is invoked.
     *
     * @param invokePostConstruct if true, invoke any @PostConstruct methods
     * on the instance's class(and super-classes) after injection.
     *
     * @exception InjectionException Thrown if an error occurs during injection
     * 
     */
    public void injectInstance(Object instance, 
                               JndiNameEnvironment componentEnv,
                               boolean invokePostConstruct)
        throws InjectionException;

    /**
     * Inject the injectable resources from the given component environment
     * into a Class instance.  Only class-level(static) fields/methods are 
     * supported.  E.g., this injection operation would be used for the 
     * Application Client Container main class. 
     *
     * Any @PostConstruct methods on the class(and super-classes)
     * will be invoked after injection.  
     *
     * @exception InjectionException Thrown if an error occurs during injection
     */
    public void injectClass(Class clazz, 
                            JndiNameEnvironment componentEnv)
        throws InjectionException;

    /**
     * Inject the injectable resources from the given component environment
     * into a Class instance.  Only class-level(static) fields/methods are 
     * supported.  E.g., this injection operation would be used for the 
     * Application Client Container main class. 
     *
     * @param invokePostConstruct if true, invoke any @PostConstruct methods
     * on the class(and super-classes) after injection.
     *
     * @exception InjectionException Thrown if an error occurs during injection
     */
    public void injectClass(Class clazz, 
                            JndiNameEnvironment componentEnv,
                            boolean invokePostConstruct)
        throws InjectionException;

    /**
     *
     * Invoke any @PreDestroy methods defined on the instance's class
     * (and super-classes).  Invocation information will be retrieved from
     * the current component invocation context.
     *
     * @exception InjectionException Thrown if an error occurs
     * 
     */
    public void invokeInstancePreDestroy(Object instance)
        throws InjectionException;


    /**
     *
     * Invoke any @PreDestroy methods defined on the instance's class
     * (and super-classes). The specified componentEnv must match the
     * environment that is associated with the component on top of the
     * invocation stack at the time this method is invoked.
     *
     * @exception InjectionException Thrown if an error occurs
     * 
     */
    public void invokeInstancePreDestroy(Object instance,
                                         JndiNameEnvironment componentEnv)
        throws InjectionException;

    /**
     *
     * Invoke any static @PreDestroy methods defined on the class
     * (and super-classes). The specified componentEnv must match the
     * environment that is associated with the component on top of the
     * invocation stack at the time this method is invoked.
     *
     * @exception InjectionException Thrown if an error occurs
     * 
     */
    public void invokeClassPreDestroy(Class clazz,
                                      JndiNameEnvironment componentEnv)
        throws InjectionException;


}
