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

/*
 * ValidationManagerFactory.java        August 18, 2003, 3:50 PM
 *
 */

package com.sun.enterprise.tools.common.validation;

import com.sun.enterprise.tools.common.validation.util.ObjectFactory;
import com.sun.enterprise.tools.common.validation.ValidationManager;


/**
 * ValidationManagerFactory is a factory to create {@link ValidationManager} 
 * objects. Creates <code>ValidationManager</code> based on the given Validation
 * File. 
 *
 * @see ValidationManager
 * 
 * @author  Rajeshwar Patil
 * @version %I%, %G%
 */

public class ValidationManagerFactory {
    /* A class implementation comment can go here. */

    /** Creates a new instance of <code>ValidationManagerFactory</code> */
    public ValidationManagerFactory(){
    }


    /**
     * Creates default Validation Manager.
     * Default Validation Manager is created using default Validation File
     *
     * @return <code>ValidationManager</code> the default Validation Manager.
     * 
     */
    public ValidationManager getValidationManager(){
        return (ValidationManager) ObjectFactory.newInstance(
            "com.sun.enterprise.tools.common.validation." +             //NOI18N
                "ValidationManager");                                   //NOI18N
    }


    /**
     * Creates Validation Manager based on the given Validation File.
     *
     * @param validationFile the Validation File. Validation File specifies
     * Validation rules(which Constraints to apply to which elements).
     * 
     * @return <code>ValidationManager</code> the Validation Manager based on
     * the given Validation File.
     */
    public ValidationManager getValidationManager(String validationFile){
        return (ValidationManager) ObjectFactory.newInstance(
            "com.sun.enterprise.tools.common.validation." +             //NOI18N
                "ValidationManager", validationFile);                   //NOI18N
    }
}
