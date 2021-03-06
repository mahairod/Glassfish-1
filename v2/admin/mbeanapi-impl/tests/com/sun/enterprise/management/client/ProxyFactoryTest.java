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
 * $Header: /cvs/glassfish/admin/mbeanapi-impl/tests/com/sun/enterprise/management/client/ProxyFactoryTest.java,v 1.4 2006/03/09 20:30:52 llc Exp $
 * $Revision: 1.4 $
 * $Date: 2006/03/09 20:30:52 $
 */
package com.sun.enterprise.management.client;

import java.util.Set;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;

import javax.management.ObjectName;
import javax.management.MBeanServerConnection;
import javax.management.AttributeList;
import javax.management.MBeanInfo;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanOperationInfo;
import javax.management.InstanceNotFoundException;
import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.AttributeChangeNotification;

import com.sun.appserv.management.base.AMX;
import com.sun.appserv.management.base.Util;
import com.sun.appserv.management.base.NotificationServiceMgr;
import com.sun.appserv.management.base.NotificationService;
import com.sun.appserv.management.helper.NotificationServiceHelper;
import com.sun.appserv.management.base.QueryMgr;
import com.sun.appserv.management.client.ProxyFactory;
import com.sun.appserv.management.util.misc.ExceptionUtil;


import com.sun.enterprise.management.AMXTestBase;
import com.sun.enterprise.management.Capabilities;


/**
 */
public final class ProxyFactoryTest extends AMXTestBase
{
		public
	ProxyFactoryTest( )
	{
	}
	    public static Capabilities
	getCapabilities()
	{
	    return getOfflineCapableCapabilities( false );
	}
	

	/**
		Verify that when an MBean is removed, the ProxyFactory
		detects this, and removes any proxy from its cache.
	 */
		public void
	testProxyFactoryDetectsMBeanRemoved()
		throws InstanceNotFoundException
	{
		// use the NotificationServiceMgr as a convenient way of making
		// an MBean (a NotificationService) come and go.
		final NotificationServiceMgr	mgr	= getDomainRoot().getNotificationServiceMgr();
		final NotificationService	ns	= mgr.createNotificationService( "UserData", 10 );
		final ObjectName	nsObjectName	= Util.getObjectName( ns );
		assert( ns.getUserData().equals( "UserData" ) );
		
		final ProxyFactory	factory	= getProxyFactory();
		final NotificationService	proxy	=
			factory.getProxy( nsObjectName, NotificationService.class, false );
		assert( proxy == ns ) : "proxies differ: " + ns + "\n" + proxy;
		
		mgr.removeNotificationService( ns.getName() );

		int	iterations	= 0;
		long	sleepMillis	= 10;
		while( factory.getProxy( nsObjectName, NotificationService.class, false ) != null )
		{
			mySleep( sleepMillis );
			if ( sleepMillis >= 400 )
			{
				trace( "testProxyFactoryDetectsMBeanRemoved: waiting for proxy to be removed" );
			}
			sleepMillis	*= 2;
		}
	}
}









