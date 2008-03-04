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
 * Copyright 2008 Sun Microsystems, Inc. All rights reserved.
 */
package com.sun.enterprise.glassfish.bootstrap.launcher;

import com.sun.enterprise.glassfish.bootstrap.launcher.util.LocalStringsImpl;
import java.util.logging.*;

/**
 *
 * @author bnevins
 */
public class GFLauncherLogger {

    static void info(String msg, Object... objs)
    {
        logger.info(strings.get(msg, objs));
    }
    static void severe(String msg, Object... objs)
    {
        logger.severe(strings.get(msg, objs));
    }

    
    private GFLauncherLogger() {
    }
    private final static Logger logger = Logger.getAnonymousLogger();
    private final static LocalStringsImpl strings = new LocalStringsImpl(GFLauncherLogger.class);
}
