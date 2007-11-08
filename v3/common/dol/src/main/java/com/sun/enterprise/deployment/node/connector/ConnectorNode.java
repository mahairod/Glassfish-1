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

/*
 * ConnectorNode.java. This class is responsible for encapsulating all information specific to the Connector DTD
 *
 * Created on February 1, 2002, 3:07 PM
 */

package com.sun.enterprise.deployment.node.connector;

import java.util.*;
import org.w3c.dom.Node;
import org.xml.sax.Attributes;
import java.util.logging.Level;

import com.sun.enterprise.deployment.EnvironmentProperty;
import com.sun.enterprise.deployment.ConnectorDescriptor;
import com.sun.enterprise.deployment.Descriptor;
import com.sun.enterprise.deployment.node.DescriptorFactory;
import com.sun.enterprise.deployment.AdminObject;
import com.sun.enterprise.deployment.node.BundleNode;
import com.sun.enterprise.deployment.xml.ConnectorTagNames;
import com.sun.enterprise.deployment.xml.TagNames;
import com.sun.enterprise.deployment.node.XMLElement;
import com.sun.enterprise.deployment.node.RootXMLNode;
import com.sun.enterprise.deployment.node.XMLNode;
import com.sun.enterprise.deployment.MessageListener;
import com.sun.enterprise.deployment.node.connector.MessageListenerNode;
import com.sun.enterprise.deployment.util.DOLUtils;


/**
 * The top connector node class
 * @author Sheetal Vartak
 * @version 
 */
public class ConnectorNode extends BundleNode implements RootXMLNode {

    // Descriptor class we are using   
    private ConnectorDescriptor descriptor; 
    public static final String VERSION_10 = "1.0";
    public static final String VERSION_15 = "1.5";
    private String specVersion;

    //connector1.0
    public static final String PUBLIC_DTD_ID_10 = "-//Sun Microsystems, Inc.//DTD Connector 1.0//EN";
    public static final String SYSTEM_ID_10 = "http://java.sun.com/dtd/connector_1_0.dtd";

    
    //connector1.5
    public final static String PUBLIC_DTD_ID = "-//Sun Microsystems, Inc.//DTD Connector 1.5//EN";
    public final  static String SYSTEM_ID = "http://java.sun.com/dtd/connector_1_5.dtd";
    
    public final static String SCHEMA_ID = "connector_1_5.xsd";
    public final static String SPEC_VERSION = VERSION_15;
    private final static List<String> systemIDs = initSystemIDs();

    public final static XMLElement tag = new XMLElement(ConnectorTagNames.CONNECTOR);

    private static List<String> initSystemIDs() {
        List<String> systemIDs = new ArrayList<String>();
        systemIDs.add(SCHEMA_ID);
        return Collections.unmodifiableList(systemIDs);
    }
    
    /**
     * register this node as a root node capable of loading entire DD files
     * 
     * @param publicIDToDTD is a mapping between xml Public-ID to DTD 
     * @return the doctype tag name
     */
    public static String registerBundle(Map publicIDToDTD) {
        publicIDToDTD.put(PUBLIC_DTD_ID, SYSTEM_ID);
        publicIDToDTD.put(PUBLIC_DTD_ID_10, SYSTEM_ID_10);
        return tag.getQName();
   }

    public ConnectorNode()  {
        super();
        registerElementHandler(new XMLElement(ConnectorTagNames.LICENSE), 
            LicenseNode.class, "setLicenseDescriptor");
    }


   /**
    * @return the descriptor instance to associate with this XMLNode
    */    
    public Object getDescriptor() {
        if (descriptor == null) {
            descriptor = (ConnectorDescriptor) DescriptorFactory.getDescriptor(getXMLPath());
        } 
        return descriptor;
    } 

    /**
     * parsed an attribute of an element
     *
     * @param the element name
     * @param the attribute name
     * @param the attribute value
     * @return true if the attribute was processed
     */
    protected boolean setAttributeValue(XMLElement elementName, 
        XMLElement attributeName, String value) {
        getDescriptor();
        if (descriptor==null) {
            throw new RuntimeException(
                "Trying to set values on a null descriptor");
        }
        // the version attribute value is the spec version we use
        // and it's only available from schema based xml
        if (attributeName.getQName().equals(ConnectorTagNames.VERSION)) {
	    descriptor.setSpecVersion(value);
            specVersion = value;
            return true;
        } else if (attributeName.getQName().equals(TagNames.ID)) {
            // we do not support id attribute for the moment
            return true;
        }

        return false;
    }

    /**
     * receives notification of the value for a particular tag
     * 
     * @param element the xml element
     * @param value it's associated value
     */
    public void setElementValue(XMLElement element, String value) {        
        getDescriptor();
        if (descriptor==null) {
            throw new RuntimeException(
                "Trying to set values on a null descriptor");
        } if (ConnectorTagNames.SPEC_VERSION.equals(element.getQName())) {
	    descriptor.setSpecVersion(value);
            specVersion = value;
        // the version element value is the resourve adapter version
        // and it's only available from dtd based xml
        } else if (ConnectorTagNames.VERSION.equals(element.getQName())) {
            descriptor.setResourceAdapterVersion(value);
        } else
	super.setElementValue(element, value);
    }

    /**
     *  @return true if the element tag can be handled by any registered sub nodes of the
     * current XMLNode
     */
    public boolean handlesElement(XMLElement element) {
	if (ConnectorTagNames.RESOURCE_ADAPTER.equals(element.getQName())) {
            return false;
	} 
	return super.handlesElement(element);
    }

    /**
     * @return the handler registered for the subtag element of the curent  XMLNode
     */
    public  XMLNode getHandlerFor(XMLElement element) {
	if (ConnectorTagNames.RESOURCE_ADAPTER.equals(element.getQName())) {
	    /** For resourceadapter tag, we need to find out what version of DTD we are handling 
	    * in order to correctly read/write the XML file
	    */
	    if (VERSION_10.equals(specVersion)) {
		OutBoundRANode outboundRANode = new OutBoundRANode(element);
		outboundRANode.setParentNode(this);
		outboundRANode.createConDefDescriptorFor10();
		return outboundRANode;
	    } else  {
		RANode raNode = new RANode(element);
		raNode.setParentNode(this);
		return raNode;
	    } 
	} else {
	    return super.getHandlerFor(element);
	}
    }  
    
    /**
     * @return the XML tag associated with this XMLNode
     */
    protected XMLElement getXMLRootTag() {
        return tag;
    }
    
    /**
     * Adds  a new DOL descriptor instance to the descriptor instance associated with 
     * this XMLNode
     *
     * @param descriptor the new descriptor
     */
    public void addDescriptor(Object newDescriptor) {
    }
        
    /**
     * all sub-implementation of this class can use a dispatch table to map xml element to
     * method name on the descriptor class for setting the element value. 
     *  
     * @return the map with the element name as a key, the setter method as a value
     */    
    protected Map getDispatchTable() {
        // no need to be synchronized for now
        Map table = super.getDispatchTable();
        table.put(ConnectorTagNames.VENDOR_NAME, "setVendorName");
	table.put(ConnectorTagNames.EIS_TYPE, "setEisType");

	// support for 1.0 DTD and 1.5 schema and not 1.5 DTD
	table.put(ConnectorTagNames.RESOURCEADAPTER_VERSION, "setResourceAdapterVersion");

        return table;
    } 

    /**
     * @return the DOCTYPE of the XML file
     */
    public String getDocType() {
	return null;
    }
    
    /**
     * @return the SystemID of the XML file
     */
    public String getSystemID() {
	    return SCHEMA_ID;
    } 

    /**
     * @return the list of SystemID of the XML schema supported
     */
    public List<String> getSystemIDs() {
        return systemIDs;
    }

    /**
     * write the descriptor class to a DOM tree and return it
     *
     * @param parent node for the DOM tree
     * @param the descriptor to write
     * @return the DOM tree top node
     */    
    public Node writeDescriptor(Node parent, Descriptor descriptor) {

        if (! (descriptor instanceof ConnectorDescriptor)) {
            throw new IllegalArgumentException(getClass() + " cannot handle descriptors of type " + descriptor.getClass());
        }
        ConnectorDescriptor conDesc = (ConnectorDescriptor) descriptor;
	conDesc.setSpecVersion(VERSION_15);
        Node connectorNode = super.writeDescriptor(parent, conDesc);      
	appendTextChild(connectorNode, ConnectorTagNames.VENDOR_NAME, conDesc.getVendorName());  
	appendTextChild(connectorNode, ConnectorTagNames.EIS_TYPE, conDesc.getEisType()); 
	appendTextChild(connectorNode, ConnectorTagNames.RESOURCEADAPTER_VERSION, conDesc.getResourceAdapterVersion());   

	//license info
        LicenseNode licenseNode = new LicenseNode();
        connectorNode = licenseNode.writeDescriptor(connectorNode, conDesc);

	// resource adapter node
	RANode raNode = new RANode();
	connectorNode = raNode.writeDescriptor(connectorNode, conDesc);  
	return connectorNode;
    }   
    
    /**
     * @return the default spec version level this node complies to
     */
    public String getSpecVersion() {
        return SPEC_VERSION;
    }

    /**
     * @return the schema URL
     */
    protected String getSchemaURL() {
        return TagNames.J2EE_NAMESPACE + "/" + getSystemID();
    }
}
