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
    "property"
}) */
@Configured
public class EjbTimerService
        implements Serializable {

	final transient private VetoableChangeSupport support = new VetoableChangeSupport(this);

    private final static long serialVersionUID = 1L;
    @Attribute

    protected String minimumDeliveryIntervalInMillis;
    @Attribute

    protected String maxRedeliveries;
    @Attribute

    protected String timerDatasource;
    @Attribute

    protected String redeliveryIntervalInternalInMillis;
    protected List<Property> property = new ConstrainedList<Property>("property", support);



    /**
     * Gets the value of the minimumDeliveryIntervalInMillis property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getMinimumDeliveryIntervalInMillis() {
        if (minimumDeliveryIntervalInMillis == null) {
            return "7000";
        } else {
            return minimumDeliveryIntervalInMillis;
        }
    }

    /**
     * Sets the value of the minimumDeliveryIntervalInMillis property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMinimumDeliveryIntervalInMillis(String value) {
        try {
            support.fireVetoableChange("minimumDeliveryIntervalInMillis", this.minimumDeliveryIntervalInMillis, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.minimumDeliveryIntervalInMillis = value;
    }

    /**
     * Gets the value of the maxRedeliveries property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getMaxRedeliveries() {
        if (maxRedeliveries == null) {
            return "1";
        } else {
            return maxRedeliveries;
        }
    }

    /**
     * Sets the value of the maxRedeliveries property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMaxRedeliveries(String value) {
        try {
            support.fireVetoableChange("maxRedeliveries", this.maxRedeliveries, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.maxRedeliveries = value;
    }

    /**
     * Gets the value of the timerDatasource property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getTimerDatasource() {
        return timerDatasource;
    }

    /**
     * Sets the value of the timerDatasource property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setTimerDatasource(String value) {
        try {
            support.fireVetoableChange("timerDatasource", this.timerDatasource, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.timerDatasource = value;
    }

    /**
     * Gets the value of the redeliveryIntervalInternalInMillis property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getRedeliveryIntervalInternalInMillis() {
        if (redeliveryIntervalInternalInMillis == null) {
            return "5000";
        } else {
            return redeliveryIntervalInternalInMillis;
        }
    }

    /**
     * Sets the value of the redeliveryIntervalInternalInMillis property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setRedeliveryIntervalInternalInMillis(String value) {
        try {
            support.fireVetoableChange("redeliveryIntervalInternalInMillis", this.redeliveryIntervalInternalInMillis, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.redeliveryIntervalInternalInMillis = value;
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
