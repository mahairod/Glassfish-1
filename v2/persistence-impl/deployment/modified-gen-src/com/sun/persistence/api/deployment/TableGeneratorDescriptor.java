/*
 * The contents of this file are subject to the terms 
 * of the Common Development and Distribution License 
 * (the "License").  You may not use this file except 
 * in compliance with the License.
 * 
 * You can obtain a copy of the license at 
 * glassfish/bootstrap/legal/CDDLv1.0.txt or 
 * https://glassfish.dev.java.net/public/CDDLv1.0.html. 
 * See the License for the specific language governing 
 * permissions and limitations under the License.
 * 
 * When distributing Covered Code, include this CDDL 
 * HEADER in each file and include the License file at 
 * glassfish/bootstrap/legal/CDDLv1.0.txt.  If applicable, 
 * add the following below this CDDL HEADER, with the 
 * fields enclosed by brackets "[]" replaced with your 
 * own identifying information: Portions Copyright [yyyy] 
 * [name of copyright owner]
 */

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-1973 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// This file may need to modified upon recompilation of the source schema. 
// Generated on: 2005.04.20 at 08:27:00 IST 
//


package com.sun.persistence.api.deployment;

import javax.xml.bind.annotation.AccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.sun.persistence.api.deployment.DescriptorNode;

@XmlAccessorType(value = AccessType.FIELD)
@XmlType(name = "table-generator", namespace = "http://java.sun.com/xml/ns/persistence_ORM")
public class TableGeneratorDescriptor
    extends DescriptorNode
{

    @XmlElement(name = "name", namespace = "http://java.sun.com/xml/ns/persistence_ORM", type = String.class)
    protected String name;
    @XmlElement(defaultValue = "", name = "table-name", namespace = "http://java.sun.com/xml/ns/persistence_ORM", type = String.class)
    protected String tableName;
    @XmlElement(defaultValue = "", name = "pk-column-value", namespace = "http://java.sun.com/xml/ns/persistence_ORM", type = String.class)
    protected String pkColumnValue;
    @XmlElement(defaultValue = "50", name = "allocation-size", namespace = "http://java.sun.com/xml/ns/persistence_ORM", type = Integer.class)
    protected Integer allocationSize;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    public void setName(String value) {
        this.name = value;
    }

    public boolean isSetName() {
        return (this.name!= null);
    }

    public void unsetName() {
        this.name = null;
    }

    /**
     * Gets the value of the tableName property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Sets the value of the tableName property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    public void setTableName(String value) {
        this.tableName = value;
    }

    public boolean isSetTableName() {
        return (this.tableName!= null);
    }

    public void unsetTableName() {
        this.tableName = null;
    }

    /**
     * Gets the value of the pkColumnValue property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String}
     */
    public String getPkColumnValue() {
        return pkColumnValue;
    }

    /**
     * Sets the value of the pkColumnValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    public void setPkColumnValue(String value) {
        this.pkColumnValue = value;
    }

    public boolean isSetPkColumnValue() {
        return (this.pkColumnValue!= null);
    }

    public void unsetPkColumnValue() {
        this.pkColumnValue = null;
    }

    /**
     * Gets the value of the allocationSize property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.Integer}
     */
    public Integer getAllocationSize() {
        return allocationSize;
    }

    /**
     * Sets the value of the allocationSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.Integer}
     */
    public void setAllocationSize(Integer value) {
        this.allocationSize = value;
    }

    public boolean isSetAllocationSize() {
        return (this.allocationSize!= null);
    }

    public void unsetAllocationSize() {
        this.allocationSize = null;
    }

    //Added code

    TableGeneratorDescriptor() {
    }

    public void accept(Visitor v) throws DeploymentException {
        v.visitTableGeneratorDescriptor(this);
    }

}
