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
 *     04/24/2009-2.0 Guy Pelletier 
 *       - 270011: JPA 2.0 MappedById support
 ******************************************************************************/  
package org.eclipse.persistence.testing.models.jpa.advanced.derivedid;

import javax.persistence.Embeddable;

@Embeddable
public class PrivateId {
    String name;
    CorporalId corporalPK;
    
    public String getName() {
        return name;
    }
    
    public CorporalId getCorporalPK() {
        return corporalPK;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setCorporalPK(CorporalId corporalPK) {
        this.corporalPK = corporalPK;
    }
}
