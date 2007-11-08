//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1.3-04/12/2007 12:08 AM(kohsuke)-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2007.08.24 at 01:25:15 PM PDT 
//


package com.sun.enterprise.config.serverbeans;

import org.jvnet.hk2.config.Attribute;
import org.jvnet.hk2.config.Configured;

import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.io.Serializable;


/**
 *
 */

/* @XmlType(name = "") */
@Configured
public class AccessLog
        implements Serializable {

	final transient private VetoableChangeSupport support = new VetoableChangeSupport(this);

    private final static long serialVersionUID = 1L;
    @Attribute

    protected String format;
    @Attribute

    protected String rotationPolicy;
    @Attribute

    protected String rotationIntervalInMinutes;
    @Attribute

    protected String rotationSuffix;
    @Attribute

    protected String rotationEnabled;



    /**
     * Gets the value of the format property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getFormat() {
        if (format == null) {
            return "%client.name% %auth-user-name% %datetime% %request% %status% %response.length%";
        } else {
            return format;
        }
    }

    /**
     * Sets the value of the format property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFormat(String value) {
        try {
            support.fireVetoableChange("format", this.format, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.format = value;
    }

    /**
     * Gets the value of the rotationPolicy property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getRotationPolicy() {
        if (rotationPolicy == null) {
            return "time";
        } else {
            return rotationPolicy;
        }
    }

    /**
     * Sets the value of the rotationPolicy property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setRotationPolicy(String value) {
        try {
            support.fireVetoableChange("rotationPolicy", this.rotationPolicy, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.rotationPolicy = value;
    }

    /**
     * Gets the value of the rotationIntervalInMinutes property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getRotationIntervalInMinutes() {
        if (rotationIntervalInMinutes == null) {
            return "1440";
        } else {
            return rotationIntervalInMinutes;
        }
    }

    /**
     * Sets the value of the rotationIntervalInMinutes property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setRotationIntervalInMinutes(String value) {
        try {
            support.fireVetoableChange("rotationIntervalInMinutes", this.rotationIntervalInMinutes, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.rotationIntervalInMinutes = value;
    }

    /**
     * Gets the value of the rotationSuffix property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getRotationSuffix() {
        if (rotationSuffix == null) {
            return "yyyyMMdd-HH'h'mm'm'ss's'";
        } else {
            return rotationSuffix;
        }
    }

    /**
     * Sets the value of the rotationSuffix property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setRotationSuffix(String value) {
        try {
            support.fireVetoableChange("rotationSuffix", this.rotationSuffix, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.rotationSuffix = value;
    }

    /**
     * Gets the value of the rotationEnabled property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getRotationEnabled() {
        if (rotationEnabled == null) {
            return "true";
        } else {
            return rotationEnabled;
        }
    }

    /**
     * Sets the value of the rotationEnabled property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setRotationEnabled(String value) {
        try {
            support.fireVetoableChange("rotationEnabled", this.rotationEnabled, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.rotationEnabled = value;
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
