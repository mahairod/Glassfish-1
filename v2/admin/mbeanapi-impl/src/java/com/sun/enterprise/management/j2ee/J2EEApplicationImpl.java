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
 * $Header: /cvs/glassfish/admin/mbeanapi-impl/src/java/com/sun/enterprise/management/j2ee/J2EEApplicationImpl.java,v 1.4 2006/03/09 20:30:44 llc Exp $
 * $Revision: 1.4 $
 * $Date: 2006/03/09 20:30:44 $
 */
 
package com.sun.enterprise.management.j2ee;
 
import java.util.Set;
import java.util.Collections;

import javax.management.AttributeNotFoundException;

import com.sun.appserv.management.j2ee.J2EEApplication;
import com.sun.appserv.management.j2ee.J2EETypes;

import com.sun.appserv.management.base.XTypes;

import com.sun.appserv.management.util.misc.GSetUtil;

import com.sun.enterprise.management.support.Delegate;

/**
 */
public final class J2EEApplicationImpl
	extends J2EEDeployedObjectImplBase
{
		public
	J2EEApplicationImpl( final Delegate delegate )
	{
		super( delegate );
	}
	
	
	private static final Set<String> MODULE_TYPES	= GSetUtil.newUnmodifiableStringSet(
		J2EETypes.WEB_MODULE,
		J2EETypes.EJB_MODULE,
		J2EETypes.APP_CLIENT_MODULE,
		J2EETypes.RESOURCE_ADAPTER_MODULE );
	
		public String[]
	getmodules()
	{
		return( GSetUtil.toStringArray( getModuleObjectNameSet() ) );
	}
	
		public Set
	getModuleObjectNameSet()
	{
		return( getContaineeObjectNameSet( MODULE_TYPES ) );
	}

	/** jsr77 StateManageable impl. */

		public boolean
	isstateManageable()
	{
		return true;
	}
	
	
		protected String
	getMonitoringPeerJ2EEType()
	{
		return( XTypes.APPLICATION_MONITOR );
	}
}
