/*
 * 
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright 2008 Sun Microsystems, Inc. All rights reserved.
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
//  
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1.3-04/12/2007 12:08 AM(kohsuke)-fcs
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2007.08.24 at 01:25:15 PM PDT
//
    
   
package com.sun.enterprise.config.serverbeans;

import org.jvnet.hk2.config.Attribute;
import org.jvnet.hk2.config.Configured;
import org.jvnet.hk2.config.Element;
import org.jvnet.hk2.config.ConfigBeanProxy;
import org.jvnet.hk2.component.Injectable;

import java.beans.PropertyVetoException;
import java.util.List;

import org.glassfish.api.admin.config.PropertiesDesc;
import org.glassfish.api.admin.config.Property;
import org.glassfish.api.admin.config.PropertyBag;

import org.glassfish.quality.ToDo;

/**
 *
 */

/* @XmlType(name = "", propOrder = {
    "description",
    "securityMap",
    "property"
}) */
@org.glassfish.api.amx.AMXConfigInfo( amxInterfaceName="com.sun.appserv.management.config.ConnectorConnectionPoolConfig")
@Configured
public interface ConnectorConnectionPool extends ConfigBeanProxy, Injectable, Resource, ResourcePool,
        PropertyBag {

    @Attribute(required = true)
    public String getResourceAdapterName();

    /**
     * Sets the value of the resourceAdapterName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setResourceAdapterName(String value) throws PropertyVetoException;

    /**
     * Gets the value of the connectionDefinitionName property.
     *
     * @return possible object is
     *         {@link String }
     */
    @Attribute(required = true)
    public String getConnectionDefinitionName();

    /**
     * Sets the value of the connectionDefinitionName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setConnectionDefinitionName(String value) throws PropertyVetoException;

    /**
     * Gets the value of the steadyPoolSize property.
     *
     * @return possible object is
     *         {@link String }
     */
    @Attribute (defaultValue="8")
    public String getSteadyPoolSize();

    /**
     * Sets the value of the steadyPoolSize property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSteadyPoolSize(String value) throws PropertyVetoException;

    /**
     * Gets the value of the maxPoolSize property.
     *
     * @return possible object is
     *         {@link String }
     */
    @Attribute (defaultValue="32")
    public String getMaxPoolSize();

    /**
     * Sets the value of the maxPoolSize property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMaxPoolSize(String value) throws PropertyVetoException;

    /**
     * Gets the value of the maxWaitTimeInMillis property.
     *
     * @return possible object is
     *         {@link String }
     */
    @Attribute (defaultValue="60000")
    public String getMaxWaitTimeInMillis();

    /**
     * Sets the value of the maxWaitTimeInMillis property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMaxWaitTimeInMillis(String value) throws PropertyVetoException;

    /**
     * Gets the value of the poolResizeQuantity property.
     *
     * @return possible object is
     *         {@link String }
     */
    @Attribute (defaultValue="2")
    public String getPoolResizeQuantity();

    /**
     * Sets the value of the poolResizeQuantity property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPoolResizeQuantity(String value) throws PropertyVetoException;

    /**
     * Gets the value of the idleTimeoutInSeconds property.
     *
     * @return possible object is
     *         {@link String }
     */
    @Attribute (defaultValue="300")
    public String getIdleTimeoutInSeconds();

    /**
     * Sets the value of the idleTimeoutInSeconds property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setIdleTimeoutInSeconds(String value) throws PropertyVetoException;

    /**
     * Gets the value of the failAllConnections property.
     *
     * @return possible object is
     *         {@link String }
     */
    @Attribute (defaultValue="false")
    public String getFailAllConnections();

    /**
     * Sets the value of the failAllConnections property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFailAllConnections(String value) throws PropertyVetoException;

    /**
     * Gets the value of the transactionSupport property.
     *
     * @return possible object is
     *         {@link String }
     */
    @Attribute
    public String getTransactionSupport();

    /**
     * Sets the value of the transactionSupport property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setTransactionSupport(String value) throws PropertyVetoException;

    /**
     * Gets the value of the isConnectionValidationRequired property.
     *
     * @return possible object is
     *         {@link String }
     */
    @Attribute (defaultValue="false")
    public String getIsConnectionValidationRequired();

    /**
     * Sets the value of the isConnectionValidationRequired property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setIsConnectionValidationRequired(String value) throws PropertyVetoException;

    /**
     * Gets the value of the validateAtmostOncePeriodInSeconds property.
     *
     * @return possible object is
     *         {@link String }
     */
    @Attribute (defaultValue="0")
    public String getValidateAtmostOncePeriodInSeconds();

    /**
     * Sets the value of the validateAtmostOncePeriodInSeconds property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setValidateAtmostOncePeriodInSeconds(String value) throws PropertyVetoException;

    /**
     * Gets the value of the connectionLeakTimeoutInSeconds property.
     *
     * @return possible object is
     *         {@link String }
     */
    @Attribute (defaultValue="0")
    public String getConnectionLeakTimeoutInSeconds();

    /**
     * Sets the value of the connectionLeakTimeoutInSeconds property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setConnectionLeakTimeoutInSeconds(String value) throws PropertyVetoException;

    /**
     * Gets the value of the connectionLeakReclaim property.
     *
     * @return possible object is
     *         {@link String }
     */
    @Attribute (defaultValue="false")
    public String getConnectionLeakReclaim();

    /**
     * Sets the value of the connectionLeakReclaim property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setConnectionLeakReclaim(String value) throws PropertyVetoException;

    /**
     * Gets the value of the connectionCreationRetryAttempts property.
     *
     * @return possible object is
     *         {@link String }
     */
    @Attribute (defaultValue="0")
    public String getConnectionCreationRetryAttempts();

    /**
     * Sets the value of the connectionCreationRetryAttempts property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setConnectionCreationRetryAttempts(String value) throws PropertyVetoException;

    /**
     * Gets the value of the connectionCreationRetryIntervalInSeconds property.
     *
     * @return possible object is
     *         {@link String }
     */
    @Attribute (defaultValue="10")
    public String getConnectionCreationRetryIntervalInSeconds();

    /**
     * Sets the value of the connectionCreationRetryIntervalInSeconds property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setConnectionCreationRetryIntervalInSeconds(String value) throws PropertyVetoException;

    /**
     * Gets the value of the lazyConnectionEnlistment property.
     *
     * @return possible object is
     *         {@link String }
     */
    @Attribute (defaultValue="false")
    public String getLazyConnectionEnlistment();

    /**
     * Sets the value of the lazyConnectionEnlistment property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setLazyConnectionEnlistment(String value) throws PropertyVetoException;

    /**
     * Gets the value of the lazyConnectionAssociation property.
     *
     * @return possible object is
     *         {@link String }
     */
    @Attribute (defaultValue="false")
    public String getLazyConnectionAssociation();

    /**
     * Sets the value of the lazyConnectionAssociation property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setLazyConnectionAssociation(String value) throws PropertyVetoException;

    /**
     * Gets the value of the associateWithThread property.
     *
     * @return possible object is
     *         {@link String }
     */
    @Attribute (defaultValue="false")
    public String getAssociateWithThread();

    /**
     * Sets the value of the associateWithThread property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAssociateWithThread(String value) throws PropertyVetoException;

    /**
     * Gets the value of the matchConnections property.
     *
     * @return possible object is
     *         {@link String }
     */
    @Attribute (defaultValue="true")
    public String getMatchConnections();

    /**
     * Sets the value of the matchConnections property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMatchConnections(String value) throws PropertyVetoException;

    /**
     * Gets the value of the maxConnectionUsageCount property.
     *
     * @return possible object is
     *         {@link String }
     */
    @Attribute (defaultValue="0")
    public String getMaxConnectionUsageCount();

    /**
     * Sets the value of the maxConnectionUsageCount property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMaxConnectionUsageCount(String value) throws PropertyVetoException;

    /**
     * Gets the value of the description property.
     *
     * @return possible object is
     *         {@link String }
     */
    @Attribute
    public String getDescription();

    /**
     * Sets the value of the description property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDescription(String value) throws PropertyVetoException;

    /**
     * Gets the value of the securityMap property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the securityMap property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSecurityMap().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link SecurityMap }
     */
    @Element
    public List<SecurityMap> getSecurityMap();
    
    /**
    	Properties as per {@link PropertyBag}
     */
    @ToDo(priority=ToDo.Priority.IMPORTANT, details="Provide PropertyDesc for legal props" )
    @PropertiesDesc(props={})
    @Element
    List<Property> getProperty();
}
