/*******************************************************************************
 * Copyright (c) 1998, 2011 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0 
 * which accompanies this distribution. 
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at 
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Oracle - initial API and implementation from Oracle TopLink
 ******************************************************************************/  
package org.eclipse.persistence.testing.sdo.helper.xmlhelper.loadandsave.nodenullpolicy;

import junit.textui.TestRunner;

import commonj.sdo.helper.XMLDocument;
public class IsSetOptionalWithDefaultSetNullTestCases extends IsSetOptionalWithDefaultTestCases {
	
	// UC 3-3a
	/*
	<xsd:element name='employee'>
	<xsd:complexType><xsd:sequence>
		<xsd:element name='fn' type='xsd:string' default='John'/>
	</xsd:sequence></xsd:complexType>
	</xsd:element>

	Use Case #3-3a - Set to Null Value
	Unmarshal From						fn Property					Marshal To
	<employee><fn/></employee>			Get = null	IsSet = true	<employee><fn/></employee>
	 */
    public IsSetOptionalWithDefaultSetNullTestCases(String name) {
        super(name);
    }

    public void setUp() {
        super.setUp();
    }

    protected String getControlFileName() {
        return "./org/eclipse/persistence/testing/sdo/helper/xmlhelper/nodenullpolicy/IsSetOptionalWithDefaultSetNull.xml";
    }

    protected String getControlWriteFileName() {
        return "./org/eclipse/persistence/testing/sdo/helper/xmlhelper/nodenullpolicy/IsSetOptionalWithDefaultSetNullWrite.xml";
    }

    protected String getNoSchemaControlWriteFileName() {
        return "./org/eclipse/persistence/testing/sdo/helper/xmlhelper/nodenullpolicy/IsSetOptionalWithDefaultSetNullWriteNoSchema.xml";
    }

    protected String getNoSchemaControlFileName() {
        return "./org/eclipse/persistence/testing/sdo/helper/xmlhelper/nodenullpolicy/IsSetOptionalWithDefaultSetNullNoSchema.xml";
    }

    protected void verifyAfterLoad(XMLDocument doc) {
        super.verifyAfterLoad(doc);
        Object value = doc.getRootObject().get(ID_NAME);
        boolean isSet = doc.getRootObject().isSet(ID_NAME);
        // verify defaults
        assertNotSame(ID_DEFAULT, value);        
        assertNull(value);
        assertTrue(isSet);

        value = doc.getRootObject().get(FIRSTNAME_NAME);
        isSet = doc.getRootObject().isSet(FIRSTNAME_NAME);
        assertNotSame(FIRSTNAME_DEFAULT, value);
        assertNull(value);
        assertTrue(isSet);
    }

    public static void main(String[] args) {
        String[] arguments = { "-c", "org.eclipse.persistence.testing.sdo.helper.xmlhelper.loadandsave.nodenullpolicy.IsSetOptionalWithDefaultSetNullTestCases" };
        TestRunner.main(arguments);
    }
}