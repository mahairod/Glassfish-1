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
public class HttpFileCache
        implements Serializable {

	final transient private VetoableChangeSupport support = new VetoableChangeSupport(this);

    private final static long serialVersionUID = 1L;
    @Attribute

    protected String globallyEnabled;
    @Attribute

    protected String fileCachingEnabled;
    @Attribute

    protected String maxAgeInSeconds;
    @Attribute

    protected String mediumFileSizeLimitInBytes;
    @Attribute

    protected String mediumFileSpaceInBytes;
    @Attribute

    protected String smallFileSizeLimitInBytes;
    @Attribute

    protected String smallFileSpaceInBytes;
    @Attribute

    protected String fileTransmissionEnabled;
    @Attribute

    protected String maxFilesCount;
    @Attribute

    protected String hashInitSize;



    /**
     * Gets the value of the globallyEnabled property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getGloballyEnabled() {
        if (globallyEnabled == null) {
            return "true";
        } else {
            return globallyEnabled;
        }
    }

    /**
     * Sets the value of the globallyEnabled property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setGloballyEnabled(String value) {
        try {
            support.fireVetoableChange("globallyEnabled", this.globallyEnabled, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.globallyEnabled = value;
    }

    /**
     * Gets the value of the fileCachingEnabled property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getFileCachingEnabled() {
        if (fileCachingEnabled == null) {
            return "on";
        } else {
            return fileCachingEnabled;
        }
    }

    /**
     * Sets the value of the fileCachingEnabled property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFileCachingEnabled(String value) {
        try {
            support.fireVetoableChange("fileCachingEnabled", this.fileCachingEnabled, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.fileCachingEnabled = value;
    }

    /**
     * Gets the value of the maxAgeInSeconds property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getMaxAgeInSeconds() {
        if (maxAgeInSeconds == null) {
            return "30";
        } else {
            return maxAgeInSeconds;
        }
    }

    /**
     * Sets the value of the maxAgeInSeconds property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMaxAgeInSeconds(String value) {
        try {
            support.fireVetoableChange("maxAgeInSeconds", this.maxAgeInSeconds, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.maxAgeInSeconds = value;
    }

    /**
     * Gets the value of the mediumFileSizeLimitInBytes property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getMediumFileSizeLimitInBytes() {
        if (mediumFileSizeLimitInBytes == null) {
            return "537600";
        } else {
            return mediumFileSizeLimitInBytes;
        }
    }

    /**
     * Sets the value of the mediumFileSizeLimitInBytes property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMediumFileSizeLimitInBytes(String value) {
        try {
            support.fireVetoableChange("mediumFileSizeLimitInBytes", this.mediumFileSizeLimitInBytes, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.mediumFileSizeLimitInBytes = value;
    }

    /**
     * Gets the value of the mediumFileSpaceInBytes property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getMediumFileSpaceInBytes() {
        if (mediumFileSpaceInBytes == null) {
            return "10485760";
        } else {
            return mediumFileSpaceInBytes;
        }
    }

    /**
     * Sets the value of the mediumFileSpaceInBytes property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMediumFileSpaceInBytes(String value) {
        try {
            support.fireVetoableChange("mediumFileSpaceInBytes", this.mediumFileSpaceInBytes, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.mediumFileSpaceInBytes = value;
    }

    /**
     * Gets the value of the smallFileSizeLimitInBytes property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getSmallFileSizeLimitInBytes() {
        if (smallFileSizeLimitInBytes == null) {
            return "2048";
        } else {
            return smallFileSizeLimitInBytes;
        }
    }

    /**
     * Sets the value of the smallFileSizeLimitInBytes property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSmallFileSizeLimitInBytes(String value) {
        try {
            support.fireVetoableChange("smallFileSizeLimitInBytes", this.smallFileSizeLimitInBytes, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.smallFileSizeLimitInBytes = value;
    }

    /**
     * Gets the value of the smallFileSpaceInBytes property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getSmallFileSpaceInBytes() {
        if (smallFileSpaceInBytes == null) {
            return "1048576";
        } else {
            return smallFileSpaceInBytes;
        }
    }

    /**
     * Sets the value of the smallFileSpaceInBytes property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSmallFileSpaceInBytes(String value) {
        try {
            support.fireVetoableChange("smallFileSpaceInBytes", this.smallFileSpaceInBytes, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.smallFileSpaceInBytes = value;
    }

    /**
     * Gets the value of the fileTransmissionEnabled property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getFileTransmissionEnabled() {
        if (fileTransmissionEnabled == null) {
            return "false";
        } else {
            return fileTransmissionEnabled;
        }
    }

    /**
     * Sets the value of the fileTransmissionEnabled property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFileTransmissionEnabled(String value) {
        try {
            support.fireVetoableChange("fileTransmissionEnabled", this.fileTransmissionEnabled, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.fileTransmissionEnabled = value;
    }

    /**
     * Gets the value of the maxFilesCount property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getMaxFilesCount() {
        if (maxFilesCount == null) {
            return "1024";
        } else {
            return maxFilesCount;
        }
    }

    /**
     * Sets the value of the maxFilesCount property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMaxFilesCount(String value) {
        try {
            support.fireVetoableChange("maxFilesCount", this.maxFilesCount, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.maxFilesCount = value;
    }

    /**
     * Gets the value of the hashInitSize property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getHashInitSize() {
        if (hashInitSize == null) {
            return "0";
        } else {
            return hashInitSize;
        }
    }

    /**
     * Sets the value of the hashInitSize property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setHashInitSize(String value) {
        try {
            support.fireVetoableChange("hashInitSize", this.hashInitSize, value);
        } catch (PropertyVetoException _x) {
            return;
        }
        this.hashInitSize = value;
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
