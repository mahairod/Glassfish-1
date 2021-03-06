/*******************************************************************************
 * Copyright (c) 1998, 2010 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0 
 * which accompanies this distribution. 
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at 
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     12/2/2009-2.1 Guy Pelletier 
 *       - 296612:  Add current annotation only metadata support of return insert/update to the EclipseLink-ORM.XML Schema
 ******************************************************************************/ 
package org.eclipse.persistence.internal.jpa.metadata.mappings;

import org.eclipse.persistence.internal.helper.DatabaseField;
import org.eclipse.persistence.internal.jpa.metadata.MetadataDescriptor;
import org.eclipse.persistence.internal.jpa.metadata.ORMetadata;
import org.eclipse.persistence.internal.jpa.metadata.accessors.objects.MetadataAccessibleObject;
import org.eclipse.persistence.internal.jpa.metadata.accessors.objects.MetadataAnnotation;

/**
 * Object to hold onto return insert metadata.
 * 
 * @author Guy Pelletier
 * @since EclipseLink 2.1
 */
public class ReturnInsertMetadata extends ORMetadata {
    private Boolean m_returnOnly;

    /**
     * INTERNAL:
     */
    public ReturnInsertMetadata() {
        super("<return-insert>");
    }
    
    /**
     * INTERNAL:
     */
    public ReturnInsertMetadata(MetadataAnnotation returnInsert, MetadataAccessibleObject accessibleObject) {
        super(returnInsert, accessibleObject);

        m_returnOnly = (Boolean) returnInsert.getAttributeBooleanDefaultFalse("returnOnly");
    }
    
    /**
     * INTERNAL:
     * Used for OX mapping.
     */
    public Boolean getReturnOnly() {
        return m_returnOnly;
    }

    /**
     * INTERNAL:
     */
    public void process(MetadataDescriptor descriptor, DatabaseField field) {
        if (m_returnOnly != null && m_returnOnly) {
            descriptor.addFieldForInsertReturnOnly(field);
        } else {
            descriptor.addFieldForInsert(field);
        }
    }
    
    /**
     * INTERNAL:
     * Used for OX mapping.
     */
    public void setReturnOnly(Boolean returnOnly) {
        m_returnOnly = returnOnly;
    }   
}
