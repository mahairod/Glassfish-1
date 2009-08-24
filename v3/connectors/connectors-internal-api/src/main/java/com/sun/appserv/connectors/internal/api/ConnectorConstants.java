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

package com.sun.appserv.connectors.internal.api;

import java.util.Collections;
import java.util.List;
import java.util.Arrays;


/** 
 * This interface contains all the constants referenced and used in the 
 * connector module.
 * As a design principal all the constants needs to be placed here. 
 * This will enable tracking all the constants easily.
 */

public interface ConnectorConstants {

    /**
     * Represents the connector container module name / type
     */
    public static final String CONNECTOR_MODULE = "connector";

     /**
     *  JAXR  system resource adapter name.
     */
    public static final String JAXR_RA_NAME = "jaxr-ra";

    /** 
     *  JDBC datasource  system resource adapter name.
     */
    public static final String JDBCDATASOURCE_RA_NAME = "__ds_jdbc_ra";
    
    /** 
     *  JDBC connectionpool datasource  system resource adapter name.
     */
    public static final String JDBCCONNECTIONPOOLDATASOURCE_RA_NAME = "__cp_jdbc_ra";
    
    /** 
     *  JDBC XA datasource  system resource adapter name.
     */
    public static final String JDBCXA_RA_NAME = "__xa_jdbc_ra";

    /**
     *  JDBC Driver Manager system resource adapter name.
     */
    public static final String JDBCDRIVER_RA_NAME = "__dm_jdbc_ra";

    /** 
     *  JMS datasource  system resource adapter name.
     */
    public static final String DEFAULT_JMS_ADAPTER = "jmsra";


    /**
     * List of system resource adapter names 
     */
    public static final List<String> systemRarNames = Collections.unmodifiableList(
            Arrays.asList(
                JAXR_RA_NAME,
                JDBCDATASOURCE_RA_NAME,
                JDBCCONNECTIONPOOLDATASOURCE_RA_NAME,
                JDBCXA_RA_NAME,
                JDBCDRIVER_RA_NAME,
                DEFAULT_JMS_ADAPTER
            ));

    /**
     * Indicates the list of system-rars for which connector connection pools can be created
     */
    public static final List<String> systemRarsAllowingPoolCreation = Collections.unmodifiableList(
               Arrays.asList(
                       DEFAULT_JMS_ADAPTER,
                       JAXR_RA_NAME
               ));

    
    /** 
     *  Reserver JNDI context under which sub contexts for default resources 
     *  and all connector connection pools are created
     *  Subcontext for connector descriptors bounding is also done under 
     *  this context.
     */
    public static String RESERVE_PREFIX = "__SYSTEM";

    /**
     * Sub context for binding connector descriptors.
     */
    public static final String DD_PREFIX= RESERVE_PREFIX+"/descriptors/";

    /**
     * Constant used to determine whether execution environment is appserver
     * runtime. 
     */
    public static final int SERVER = 1;

    /**
     * Constant used to determine whether execution environment is application
     * client container. 
     */
    public static final int CLIENT = 2;

    /** 
     * Token used for generation of poolname pertaining to sun-ra.xml. 
     * Generated pool name will be 
     * rarName+POOLNAME_APPENDER+connectionDefName+SUN_RA_POOL.
     * SUNRA connector connections pools are are named and bound after 
     * this name. Pool object will be bound under POOLS_JNDINAME_PREFIX 
     * subcontext. To lookup a pool the jndi name should be 
     * POOLS_JNDINAME_PREFIX/rarName+POOLNAME_APPENDER+connectionDefName
     * +SUN_RA_POOL
     */
    public static final String SUN_RA_POOL = "sunRAPool";
    public static final String ADMINISTERED_OBJECT_FACTORY =
        "com.sun.enterprise.resource.naming.AdministeredObjectFactory";

    /**
     * Meta char for mapping the security for connection pools
     */
    public static String SECURITYMAPMETACHAR="*";

    /** 
     * Token used for default poolname generation. Generated pool name will be 
     * rarName+POOLNAME_APPENDER+connectionDefName.Default connector connections
     * pools are are named and bound after this name. Pool object will be bound
     * under POOLS_JNDINAME_PREFIX subcontext. To lookup a pool the jndi name
     * should be 
     * POOLS_JNDINAME_PREFIX/rarName+POOLNAME_APPENDER+connectionDefName
     */
    public static String POOLNAME_APPENDER="#";

    /** 
     * Token used for default connector resource generation.Generated connector
     * resource  name and JNDI names will be 
     * RESOURCE_JNDINAME_PREFIX+rarName+RESOURCENAME_APPENDER+connectionDefName
     * This name should be used to lookup connector resource.
     */
    public static String RESOURCENAME_APPENDER="#";


    /**
     * represents the monitoring-service level element name
     */
    public static String MONITORING_CONNECTOR_SERVICE_MODULE_NAME = "connector-service";

    /**
     * represents the monitoring-service hierarchy elements <br>
     * eg: server.connector-service.&lt;RA-NAME&gt;.work-management<br>
     */
    public static String MONITORING_CONNECTOR_SERVICE = "connector-service";
    public static String MONITORING_JMS_SERVICE = "jms-service";
    public static String MONITORING_WORK_MANAGEMENT = "work-management";
    public static String MONITORING_SEPARATOR = "/";

    /**
     *  Reserved sub-context where datasource-definition objets (resource and pool) are bound with generated names.
     */
    public static String DATASOURCE_DEFINITION_JNDINAME_PREFIX="__datasource_definition/";

    /**
     *  Reserved sub-context where pool objets are bound with generated names.
     */
    public static String POOLS_JNDINAME_PREFIX=RESERVE_PREFIX+"/pools/";

    /**
     *  Reserved sub-context where connector resource objects are bound with 
     *  generated names.
     */
    public static String RESOURCE_JNDINAME_PREFIX=RESERVE_PREFIX+"/resource/";
    public static String USERGROUPDISTINGUISHER="#";
    public static String CAUTION_MESSAGE="Please add the following permissions to the " +
            "server.policy file and restart the appserver.";
    
    /**
     * Token used for generating the name to refer to the embedded rars.
     * It will be AppName+EMBEDDEDRAR_NAME_DELIMITER+embeddedRarName.
     */

    public static String EMBEDDEDRAR_NAME_DELIMITER="#";

    /**
     * Property name for distinguishing the transaction exceptions 
     * propagation capability.
     */
    public final static String THROW_TRANSACTED_EXCEPTIONS_PROP
        = "resourceadapter.throw.transacted.exceptions";
 
    /**
     * System Property value for distinguishing the transaction exceptions 
     * propagation capability.
     */
    static String sysThrowExcp
        = System.getProperty(THROW_TRANSACTED_EXCEPTIONS_PROP);

    /**
     * Property value for distinguishing the transaction exceptions 
     * propagation capability.
     */
    public static boolean THROW_TRANSACTED_EXCEPTIONS
        = sysThrowExcp != null && !(sysThrowExcp.trim().equals("true")) ?
          false : true;
    
    public static final int DEFAULT_RESOURCE_ADAPTER_SHUTDOWN_TIMEOUT = 30;

   /**
     * Property value for defining NoTransaction transaction-support in
     * a connector-connection-pool
     */
    public String NO_TRANSACTION_TX_SUPPORT_STRING = "NoTransaction";
                                                                                                              
    /**
     * Property value for defining LocalTransaction transaction-support in
     * a connector-connection-pool
     */
    public String LOCAL_TRANSACTION_TX_SUPPORT_STRING = "LocalTransaction";
                                                                                                              
    /**
     * Property value for defining XATransaction transaction-support in
     * a connector-connection-pool
     */
    public String XA_TRANSACTION_TX_SUPPORT_STRING = "XATransaction";
                                                                                                              
    /**
     * Property value defining the NoTransaction transaction-support value
     * as an integer
     */
                                                                                                              
    public int NO_TRANSACTION_INT = 0;
    /**
     * Property value defining the LocalTransaction transaction-support value
     * as an integer
     */
                                                                                                              
    public int LOCAL_TRANSACTION_INT = 1;
                                                                                                              
    /**
     * Property value defining the XATransaction transaction-support value
     * as an integer
     */
    public int XA_TRANSACTION_INT = 2;
                                                                                                              
    /**
     * Property value defining an undefined transaction-support value
     * as an integer
     */
    public int UNDEFINED_TRANSACTION_INT = -1;

    /**
     * Min pool size for JMS connection pools.
     */
    public static int JMS_POOL_MINSIZE = 1;

    /**
     * Min pool size for JMS connection pools.
     */
    public static int JMS_POOL_MAXSIZE = 250;
    
    public static enum PoolType {

        ASSOCIATE_WITH_THREAD_POOL, STANDARD_POOL, PARTITIONED_POOL,
        POOLING_DISABLED;
    }

    public static int NON_ACC_CLIENT = 0;

    public static String PM_JNDI_SUFFIX = "__pm";

    public static String NON_TX_JNDI_SUFFIX = "__nontx" ;

    /**
     * Name of the JNDI environment property that can be provided so that the 
     * <code>ObjectFactory</code> can decide which type of datasource create.
     */
    public static String JNDI_SUFFIX_PROPERTY = "com.sun.enterprise.connectors.jndisuffix";
   
    /**
     * Valid values that can be provided to the JNDI property.
     */
    public static String[] JNDI_SUFFIX_VALUES = { PM_JNDI_SUFFIX , NON_TX_JNDI_SUFFIX };

    public static final String CCP = "ConnectorConnectionPool";
    public static final String CR  =  "ConnectorResource";
    public static final String AOR = "AdminObjectResource";
    public static final String SEC = "Security";
    public static final String RA = "ResourceAdapter";
    public static final String JDBC = "Jdbc";

    public static final String INSTALL_ROOT = "com.sun.aas.installRoot";

    /**
     * Constant to denote external jndi resource type.
     */
    public static final String RES_TYPE_EXTERNAL_JNDI = "external-jndi";

    public static final String RES_TYPE_JDBC = "jdbc";

    /**
     * Constant to denote jdbc connection pool resource type.
     */
    public static final String RES_TYPE_JCP = "jcp";

    /**
     * Constant to denote connector connection pool  resource type.
     */
    public static final String RES_TYPE_CCP = "ccp";

    /**
     * Constant to denote connector resource type.
     */
    public static final String RES_TYPE_CR = "cr";

    /**
     * Constant to denote custom resource type.
     */
    public static final String RES_TYPE_CUSTOM = "custom";

    /**
     * Constant to denote admin object resource type.
     */
    public static final String RES_TYPE_AOR = "aor";

    /**
     * Constant to denote resource adapter config type.
     */
    public static final String RES_TYPE_RAC = "rac";

    /**
     * Constant to denote connector-work-security-map type.
     */
    public static final String RES_TYPE_CWSM = "cwsm";

    /**
     * Constant to denote mail resource type.
     */
    public static final String RES_TYPE_MAIL = "mail";

    public static final String JMS_QUEUE = "javax.jms.Queue";
    public static final String JMS_TOPIC = "javax.jms.Topic";
    public static final String JMS_QUEUE_CONNECTION_FACTORY = "javax.jms.QueueConnectionFactory";
    public static final String JMS_TOPIC_CONNECTION_FACTORY = "javax.jms.TopicConnectionFactory";
    /** resource type residing in an external JNDI repository */
    public static final String EXT_JNDI_RES_TYPE = "external-jndi-resource";

    // name by which connector's implemenation of message-bean-client-factory service is available.
    // MDB-Container can use this constant to get connector's implementation of the factory
    public static final String CONNECTOR_MESSAGE_BEAN_CLIENT_FACTORY="ConnectorMessageBeanClientFactory";

    public static final String EXPLODED_EMBEDDED_RAR_EXTENSION="_rar";

    public static final String JAVA_BEAN_FACTORY_CLASS = "org.glassfish.resources.custom.factory.JavaBeanFactory";
    public static final String PRIMITIVES_AND_STRING_FACTORY_CLASS =
            "org.glassfish.resources.custom.factory.PrimitivesAndStringFactory";
    public static final String URL_OBJECTS_FACTORY = "org.glassfish.resources.custom.factory.URLObjectFactory";
    public static final String PROPERTIES_FACTORY = "org.glassfish.resources.custom.factory.PropertiesFactory";
}
