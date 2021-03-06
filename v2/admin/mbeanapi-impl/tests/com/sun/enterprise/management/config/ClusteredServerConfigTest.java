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
package com.sun.enterprise.management.config;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;

import com.sun.appserv.management.base.XTypes;
import com.sun.appserv.management.base.Util;

import com.sun.appserv.management.config.DomainConfig;
import com.sun.appserv.management.config.Enabled;
import com.sun.appserv.management.config.ClusteredServerConfig;
import com.sun.appserv.management.config.DeployedItemRefConfig;
import com.sun.appserv.management.config.ResourceRefConfig;
import com.sun.appserv.management.config.NodeAgentConfig;
import com.sun.appserv.management.config.ClusterConfig;
import com.sun.appserv.management.config.RefConfig;

import com.sun.appserv.management.util.misc.GSetUtil;

import com.sun.enterprise.management.AMXTestBase;
import com.sun.enterprise.management.ClusterSupportRequired;

import com.sun.enterprise.management.PropertyKeys;

/**
 */
public final class ClusteredServerConfigTest extends AMXTestBase
    implements ClusterSupportRequired
{
    public ClusteredServerConfigTest()
    {
    }

        private void
    sanityCheck( final ClusteredServerConfig csc )
    {
        assert XTypes.CLUSTERED_SERVER_CONFIG.equals( csc.getJ2EEType() );
        
        final String configName    = csc.getReferencedConfigName();
        final String nodeAgentName = csc.getReferencedNodeAgentName();
        
        final Map<String,DeployedItemRefConfig>	deployedItems   =
            csc.getDeployedItemRefConfigMap();
            
            
        final Map<String,ResourceRefConfig>	resources   =
            csc.getResourceRefConfigMap();
        
        final String lbWeight   = csc.getLBWeight();
        csc.setLBWeight( lbWeight );
    }
    
    
		public ClusteredServerConfig
	createClusteredServer(
	    final String serverName,
	    final String nodeAgentName,
	    final int    basePort )
	{
	    final DomainConfig      domainConfig    = getDomainConfig();
	    
	    if ( domainConfig.getClusteredServerConfigMap().get( serverName ) != null )
	    {
	        domainConfig.removeClusteredServerConfig( serverName );
	    }
	    else if ( domainConfig.getStandaloneServerConfigMap().get( serverName ) != null )
	    {
	        domainConfig.removeStandaloneServerConfig( serverName );
	    }
	    
	    final ClusterConfig clusterConfig   =
	        ClusterConfigTest.ensureDefaultInstance( domainConfig );
	    
	    if ( domainConfig.getClusteredServerConfigMap().get( serverName ) != null )
	    {
	        domainConfig.removeClusteredServerConfig( serverName );
	        assert domainConfig.getClusteredServerConfigMap().get( serverName ) == null;
	    }
	    
	    final ConfigSetup   setup   = new ConfigSetup( getDomainRoot() );
	    final Map<String,String>    options = new HashMap<String,String>();
	    setup.setupServerPorts( options, basePort );
	    
	    final ClusteredServerConfig csc  =
	        domainConfig.createClusteredServerConfig( serverName,
	                    clusterConfig.getName(),
	                    nodeAgentName,
	                    options );
	    sanityCheck( csc );
	    
	    return csc;
	}
	
	    private void
	verifyRefContainers()
	{
	    final Set<String>   j2eeTypes   =
	        GSetUtil.newUnmodifiableStringSet(
	            XTypes.DEPLOYED_ITEM_REF_CONFIG, XTypes.RESOURCE_REF_CONFIG );
	    
	    final Set<RefConfig>    refs    = getQueryMgr().queryJ2EETypesSet( j2eeTypes );
	    
	    for( final RefConfig ref : refs )
	    {
	        assert ref.getContainer() != null :
	            "MBean " + Util.getObjectName( ref ) + " return null from getContainer()";
	    }
	}
	
		public void
	testCreateRemove()
	{
	    final DomainConfig  domainConfig    = getDomainConfig();
	    final NodeAgentConfig   nodeAgentConfig = getDASNodeAgentConfig();
	    
	    if ( nodeAgentConfig == null )
	    {
	        warning( "SKIPPING ClusteredServerConfigTest.testCreateRemove: " +
	            "no DAS Node Agent has been specified; use " +
	                PropertyKeys.DAS_NODE_AGENT_NAME );
	    }
	    else
	    {
	        final int NUM   = 5;
	        final String    baseName    = "ClusteredServerConfigTest";
	        
	        verifyRefContainers();
	        
	        final ClusteredServerConfig[]   servers = new ClusteredServerConfig[ NUM ];
	        for( int i = 0; i < NUM; ++i )
	        {
	            final int   basePort    = 11000 + i * 10;
	            servers[ i ]    = createClusteredServer( baseName + "-" + i,
	                                nodeAgentConfig.getName(),
	                                basePort );
	            printVerbose( "Created ClusteredServerConfig: " + servers[ i ].getName() );
	            assert XTypes.CLUSTERED_SERVER_CONFIG.equals( servers[ i ].getJ2EEType() );
	            
	            verifyRefContainers();
	        }
	        
	        for( int i = 0; i < NUM; ++i )
	        {
	            final String name   = servers[ i ].getName();
    	        domainConfig.removeClusteredServerConfig( name );
	            printVerbose( "Removed ClusteredServerConfig: " + name );
	        }
    	    
	    }
	}
 
}


