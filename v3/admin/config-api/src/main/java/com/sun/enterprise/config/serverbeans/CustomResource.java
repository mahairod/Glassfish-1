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
import java.util.ArrayList;
import java.util.List;


/**
 *
 */

/* @XmlType(name = "", propOrder = {
    "description",
    "property"
}) */
@Configured
public class CustomResource implements Resource, Serializable {

    final transient private VetoableChangeSupport support = new VetoableChangeSupport(this);
    
    private final static long serialVersionUID = 1L;
    @Attribute(required = true)

    protected String jndiName;
    @Attribute(required = true)

    protected String resType;
    @Attribute(required = true)

    protected String factoryClass;
    @Attribute

    protected String objectType;
    @Attribute

    protected String enabled;
    protected String description;
    protected List<Property> property = new ConstrainedList<Property>("property", support);



    /**
     * Gets the value of the jndiName property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getJndiName() {
        return jndiName;
    }

    /**
     * Sets the value of the jndiName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setJndiName(String value) {
        try {
            support.fireVetoableChange("jndiName", this.jndiName, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.jndiName = value;
    }

    /**
     * Gets the value of the resType property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getResType() {
        return resType;
    }

    /**
     * Sets the value of the resType property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setResType(String value) {
        try {
            support.fireVetoableChange("resType", this.resType, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.resType = value;
    }

    /**
     * Gets the value of the factoryClass property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getFactoryClass() {
        return factoryClass;
    }

    /**
     * Sets the value of the factoryClass property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFactoryClass(String value) {
        try {
            support.fireVetoableChange("factoryClass", this.factoryClass, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.factoryClass = value;
    }

    /**
     * Gets the value of the objectType property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getObjectType() {
        if (objectType == null) {
            return "user";
        } else {
            return objectType;
        }
    }

    /**
     * Sets the value of the objectType property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setObjectType(String value) {
        try {
            support.fireVetoableChange("objectType", this.objectType, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.objectType = value;
    }

    /**
     * Gets the value of the enabled property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getEnabled() {
        if (enabled == null) {
            return "true";
        } else {
            return enabled;
        }
    }

    /**
     * Sets the value of the enabled property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setEnabled(String value) {
        try {
            support.fireVetoableChange("enabled", this.enabled, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.enabled = value;
    }

    /**
     * Gets the value of the description property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDescription(String value) {
        try {
            support.fireVetoableChange("description", this.description, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.description = value;
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
