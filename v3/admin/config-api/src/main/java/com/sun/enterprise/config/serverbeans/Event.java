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
public class Event
        implements Serializable {

	final transient private VetoableChangeSupport support = new VetoableChangeSupport(this);

    private final static long serialVersionUID = 1L;
    @Attribute(required = true)

    protected String type;
    @Attribute

    protected String recordEvent;
    @Attribute

    protected String level;
    protected String description;
    protected List<Property> property = new ConstrainedList<Property>("property", support);



    /**
     * Gets the value of the type property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setType(String value) {
        try {
            support.fireVetoableChange("type", this.type, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.type = value;
    }

    /**
     * Gets the value of the recordEvent property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getRecordEvent() {
        if (recordEvent == null) {
            return "true";
        } else {
            return recordEvent;
        }
    }

    /**
     * Sets the value of the recordEvent property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setRecordEvent(String value) {
        try {
            support.fireVetoableChange("recordEvent", this.recordEvent, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.recordEvent = value;
    }

    /**
     * Gets the value of the level property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getLevel() {
        if (level == null) {
            return "INFO";
        } else {
            return level;
        }
    }

    /**
     * Sets the value of the level property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setLevel(String value) {
        try {
            support.fireVetoableChange("level", this.level, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.level = value;
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
