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

import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 *
 */

/* @XmlType(name = "", propOrder = {
    "clusterRefOrServerRef",
    "property"
}) */
@Configured
public class LbConfig
        implements Serializable {

	final transient private VetoableChangeSupport support = new VetoableChangeSupport(this);

    private final static long serialVersionUID = 1L;

    @Attribute(required = true)
    protected String name;

    @Attribute
    protected String responseTimeoutInSeconds;

    @Attribute
    protected String httpsRouting;

    @Attribute
    protected String reloadPollIntervalInSeconds;

    @Attribute
    protected String monitoringEnabled;

    @Attribute
    protected String routeCookieEnabled;

    @Element("*")
    protected List<Ref> clusterRefOrServerRef = new ConstrainedList<Ref>("clusterRefOrServerRef", support);


    protected List<Property> property = new ConstrainedList<Property>("property", support);


    /**
     * Gets the value of the name property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setName(String value) {
        try {
            support.fireVetoableChange("name", this.name, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.name = value;
    }

    /**
     * Gets the value of the responseTimeoutInSeconds property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getResponseTimeoutInSeconds() {
        if (responseTimeoutInSeconds == null) {
            return "60";
        } else {
            return responseTimeoutInSeconds;
        }
    }

    /**
     * Sets the value of the responseTimeoutInSeconds property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setResponseTimeoutInSeconds(String value) {
        try {
            support.fireVetoableChange("responseTimeoutInSeconds", this.responseTimeoutInSeconds, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.responseTimeoutInSeconds = value;
    }

    /**
     * Gets the value of the httpsRouting property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getHttpsRouting() {
        if (httpsRouting == null) {
            return "false";
        } else {
            return httpsRouting;
        }
    }

    /**
     * Sets the value of the httpsRouting property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setHttpsRouting(String value) {
        try {
            support.fireVetoableChange("httpsRouting", this.httpsRouting, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.httpsRouting = value;
    }

    /**
     * Gets the value of the reloadPollIntervalInSeconds property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getReloadPollIntervalInSeconds() {
        if (reloadPollIntervalInSeconds == null) {
            return "60";
        } else {
            return reloadPollIntervalInSeconds;
        }
    }

    /**
     * Sets the value of the reloadPollIntervalInSeconds property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setReloadPollIntervalInSeconds(String value) {
        try {
            support.fireVetoableChange("reloadPollIntervalInSeconds", this.reloadPollIntervalInSeconds, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.reloadPollIntervalInSeconds = value;
    }

    /**
     * Gets the value of the monitoringEnabled property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getMonitoringEnabled() {
        if (monitoringEnabled == null) {
            return "false";
        } else {
            return monitoringEnabled;
        }
    }

    /**
     * Sets the value of the monitoringEnabled property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMonitoringEnabled(String value) {
        try {
            support.fireVetoableChange("monitoringEnabled", this.monitoringEnabled, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.monitoringEnabled = value;
    }

    /**
     * Gets the value of the routeCookieEnabled property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getRouteCookieEnabled() {
        if (routeCookieEnabled == null) {
            return "true";
        } else {
            return routeCookieEnabled;
        }
    }

    /**
     * Sets the value of the routeCookieEnabled property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setRouteCookieEnabled(String value) {
        try {
            support.fireVetoableChange("routeCookieEnabled", this.routeCookieEnabled, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.routeCookieEnabled = value;
    }

    /**
     * Gets the value of the clusterRefOrServerRef property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the clusterRefOrServerRef property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClusterRefOrServerRef().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link ClusterRef }
     * {@link ServerRef }
     */
    public List<Ref> getClusterRefOrServerRef() {

        return this.clusterRefOrServerRef;
    }

    /**
     * Gets the value of the property property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the property property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProperty().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link Property }
     */
    public List<Property> getProperty() {
        return this.property;
    }

    public void addVetoableChangeListener(VetoableChangeListener param0) {
        support.addVetoableChangeListener(param0);
    }

    public void addVetoableChangeListener(String param0, VetoableChangeListener param1) {
        support.addVetoableChangeListener(param0, param1);
    }

    public void removeVetoableChangeListener(String param0, VetoableChangeListener param1) {
        support.removeVetoableChangeListener(param0, param1);
    }

    public void removeVetoableChangeListener(VetoableChangeListener param0) {
        support.removeVetoableChangeListener(param0);
    }

}
