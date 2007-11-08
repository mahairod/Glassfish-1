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


/**
 *
 */

/* @XmlType(name = "", propOrder = {
    "healthChecker"
}) */
@Configured
public class ClusterRef implements Ref, Serializable {

    final transient private VetoableChangeSupport support = new VetoableChangeSupport(this);
    
    private final static long serialVersionUID = 1L;
    @Attribute(required = true)

    protected String ref;
    @Attribute

    protected String lbPolicy;
    @Attribute

    protected String lbPolicyModule;
    @Element
    protected HealthChecker healthChecker;



    /**
     * Gets the value of the ref property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getRef() {
        return ref;
    }

    /**
     * Sets the value of the ref property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setRef(String value) {
        try {
            support.fireVetoableChange("ref", this.ref, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.ref = value;
    }

    /**
     * Gets the value of the lbPolicy property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getLbPolicy() {
        if (lbPolicy == null) {
            return "round-robin";
        } else {
            return lbPolicy;
        }
    }

    /**
     * Sets the value of the lbPolicy property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setLbPolicy(String value) {
        try {
            support.fireVetoableChange("lbPolicy", this.lbPolicy, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.lbPolicy = value;
    }

    /**
     * Gets the value of the lbPolicyModule property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getLbPolicyModule() {
        return lbPolicyModule;
    }

    /**
     * Sets the value of the lbPolicyModule property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setLbPolicyModule(String value) {
        try {
            support.fireVetoableChange("lbPolicyModule", this.lbPolicyModule, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.lbPolicyModule = value;
    }

    /**
     * Gets the value of the healthChecker property.
     *
     * @return possible object is
     *         {@link HealthChecker }
     */
    public HealthChecker getHealthChecker() {
        return healthChecker;
    }

    /**
     * Sets the value of the healthChecker property.
     *
     * @param value allowed object is
     *              {@link HealthChecker }
     */
    public void setHealthChecker(HealthChecker value) {
        try {
            support.fireVetoableChange("healthChecker", this.healthChecker, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.healthChecker = value;
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
