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



package com.sun.enterprise.config.serverbeans;

import org.jvnet.hk2.config.Attribute;
import org.jvnet.hk2.config.Element;
import org.jvnet.hk2.config.Configured;
import org.jvnet.hk2.config.ConfigBeanProxy;
import org.jvnet.hk2.component.Injectable;

import java.beans.PropertyVetoException;
import java.io.Serializable;
import java.util.List;

import org.glassfish.api.admin.config.PropertyDesc;
import org.glassfish.api.admin.config.PropertiesDesc;
import org.glassfish.api.admin.config.Property;
import org.glassfish.api.admin.config.PropertyBag;

import org.glassfish.quality.ToDo;

/**
 *
 */

/* @XmlType(name = "", propOrder = {
    "property"
}) */
@org.glassfish.api.amx.AMXConfigInfo( amxInterfaceName="com.sun.appserv.management.config.DASConfig", singleton=true)
@Configured
public interface DasConfig extends ConfigBeanProxy, Injectable, PropertyBag {

    /**
     * Gets the value of the dynamicReloadEnabled property.
     *
     * @return possible object is
     *         {@link String }
     */
    @Attribute (defaultValue="false")
    public String getDynamicReloadEnabled();

    /**
     * Sets the value of the dynamicReloadEnabled property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDynamicReloadEnabled(String value) throws PropertyVetoException;

    /**
     * Gets the value of the dynamicReloadPollIntervalInSeconds property.
     *
     * @return possible object is
     *         {@link String }
     */
    @Attribute (defaultValue="2")
    public String getDynamicReloadPollIntervalInSeconds();

    /**
     * Sets the value of the dynamicReloadPollIntervalInSeconds property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDynamicReloadPollIntervalInSeconds(String value) throws PropertyVetoException;

    /**
     * Gets the value of the autodeployEnabled property.
     *
     * @return possible object is
     *         {@link String }
     */
    @Attribute (defaultValue="false")
    public String getAutodeployEnabled();

    /**
     * Sets the value of the autodeployEnabled property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAutodeployEnabled(String value) throws PropertyVetoException;

    /**
     * Gets the value of the autodeployPollingIntervalInSeconds property.
     *
     * @return possible object is
     *         {@link String }
     */
    @Attribute (defaultValue="2")
    public String getAutodeployPollingIntervalInSeconds();

    /**
     * Sets the value of the autodeployPollingIntervalInSeconds property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAutodeployPollingIntervalInSeconds(String value) throws PropertyVetoException;

    /**
     * Gets the value of the autodeployDir property.
     *
     * @return possible object is
     *         {@link String }
     */
    @Attribute (defaultValue="autodeploy")
    public String getAutodeployDir();

    /**
     * Sets the value of the autodeployDir property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAutodeployDir(String value) throws PropertyVetoException;

    /**
     * Gets the value of the autodeployVerifierEnabled property.
     *
     * @return possible object is
     *         {@link String }
     */
    @Attribute (defaultValue="false")
    public String getAutodeployVerifierEnabled();

    /**
     * Sets the value of the autodeployVerifierEnabled property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAutodeployVerifierEnabled(String value) throws PropertyVetoException;

    /**
     * Gets the value of the autodeployJspPrecompilationEnabled property.
     *
     * @return possible object is
     *         {@link String }
     */
    @Attribute (defaultValue="false")
    public String getAutodeployJspPrecompilationEnabled();

    /**
     * Sets the value of the autodeployJspPrecompilationEnabled property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAutodeployJspPrecompilationEnabled(String value) throws PropertyVetoException;

    /**
     * Gets the value of the autodeployRetryTimeout property.
     *
     * @return possible object is
     *         {@link String }
     */
    @Attribute (defaultValue="4")
    public String getAutodeployRetryTimeout();

    /**
     * Sets the value of the autodeployRetryTimeout property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAutodeployRetryTimeout(String value) throws PropertyVetoException;

    /**
     * Gets the value of the deployXmlValidation property.
     *
     * @return possible object is
     *         {@link String }
     */
    @Attribute (defaultValue="true")
    public String getDeployXmlValidation();

    /**
     * Sets the value of the deployXmlValidation property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDeployXmlValidation(String value) throws PropertyVetoException;

    /**
     * Gets the value of the adminSessionTimeoutInMinutes property.
     *
     * @return possible object is
     *         {@link String }
     */
    @Attribute (defaultValue="60")
    public String getAdminSessionTimeoutInMinutes();

    /**
     * Sets the value of the adminSessionTimeoutInMinutes property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAdminSessionTimeoutInMinutes(String value) throws PropertyVetoException;
    
    /**
    	Properties as per {@link PropertyBag}
     */
    @ToDo(priority=ToDo.Priority.IMPORTANT, details="Provide PropertyDesc for legal props" )
    @PropertiesDesc(props={})
    @Element
    List<Property> getProperty();
}
