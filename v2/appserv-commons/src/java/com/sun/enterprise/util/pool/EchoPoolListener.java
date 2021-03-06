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

//NOTE: Tabs are used instead of spaces for indentation. 
//  Make sure that your editor does not replace tabs with spaces. 
//  Set the tab length using your favourite editor to your 
//  visual preference.

/*
 * Filename: EchoPoolListener.java	
 *
 * Copyright 2000-2001 by iPlanet/Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of iPlanet/Sun Microsystems, Inc. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license 
 * agreement you entered into with iPlanet/Sun Microsystems.
 */
 
/**
 * <BR> <I>$Source: /cvs/glassfish/appserv-commons/src/java/com/sun/enterprise/util/pool/EchoPoolListener.java,v $</I>
 * @author     $Author: tcfujii $
 * @version    $Revision: 1.3 $ $Date: 2005/12/25 04:12:26 $
 */
 
package com.sun.enterprise.util.pool;

import java.rmi.RemoteException;
//Bug 4677074 begin
import java.util.logging.Logger;
import java.util.logging.Level;
import com.sun.logging.LogDomains;
//Bug 4677074 end

/**
 * PoolListeners receive notification from the PoolHandler.
 */
public class EchoPoolListener
	implements PoolListener
{
//Bug 4677074 begin
    static Logger _logger=LogDomains.getLogger(LogDomains.UTIL_LOGGER);
//Bug 4677074 end
    private boolean debug = false;

    /**
     * called after an object has been created.
     */
    public void afterCreate(Object object) {
        if ( com.sun.enterprise.util.logging.Debug.enabled )
//Bug 4677074     	System.out.println(this + ": afterCreate(" + object + ");");
//Bug 4677074 begin
		 _logger.log(Level.FINE,this + ": afterCreate(" + object + ");");
//Bug 4677074 end
    }
       
    /**
     * called before an object is to be destroyed.
     */
    public void beforeDestroy(Object object) {
        if ( com.sun.enterprise.util.logging.Debug.enabled )
//Bug 4677074    	System.out.println(this + ": beforeDestroy(" + object + ");");
//Bug 4677074 begin
		 _logger.log(Level.FINE,this + ": beforeDestroy(" + object + ");");
//Bug 4677074 end
    }
       
    /**
     * Called by the thread that is about to wait.
     */
    public void beforeWait(Object object) {
        if ( com.sun.enterprise.util.logging.Debug.enabled )
//Bug 4677074    	System.out.println(this + ": beforeWait(" + object + ");");
//Bug 4677074 begin
                 _logger.log(Level.FINE,this + ": beforeWait(" + object + ");");
//Bug 4677074 end
    }
       
    /**
     * Called by the thread that has been notified.
     */
    public void afterNotify(Object object) {
        if ( com.sun.enterprise.util.logging.Debug.enabled )
//Bug 4677074    	System.out.println(this + ": afterNotify(" + object + ");");
//Bug 4677074 begin
                 _logger.log(Level.FINE,this + ": afterNotify(" + object + ");");
//Bug 4677074 end
    }
   
   
   
    /**
     * Called when the pool is recycled.
     */
    public void onRecycle() {
        if ( com.sun.enterprise.util.logging.Debug.enabled )
//Bug 4677074    	System.out.println(this + ": pool recycled......");
//Bug 4677074 begin
		_logger.log(Level.FINE,this + ": pool recycled......");
//Bug 4677074 end
    }
    
    /**
     * Called when the pool is on hold.
     */
    public void beforeHold() {
        if ( com.sun.enterprise.util.logging.Debug.enabled )
//Bug 4677074    	System.out.println(this + ": pool on hold......");
//Bug 4677074 begin
                _logger.log(Level.FINE,this + ": pool on hold......");
//Bug 4677074 end
    }
    
    /**
     * Called when the pool is out of hold.
     */
    public void afterHold() {
        if ( com.sun.enterprise.util.logging.Debug.enabled )
//Bug 4677074    	System.out.println(this + ": pool out of hold......");
//Bug 4677074 begin
                _logger.log(Level.FINE,this + ": pool out of hold......");
//Bug 4677074 end
    }
    
    /**
     * Called when the pool is closed.
     */
    public void onClose() {
        if ( com.sun.enterprise.util.logging.Debug.enabled )
//Bug 4677074    	System.out.println(this + ": pool closed......");
//Bug 4677074 begin
                _logger.log(Level.FINE,this + ": pool closed......");
//Bug 4677074 end
    }
   
}
