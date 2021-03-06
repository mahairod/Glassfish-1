/*******************************************************************************
 * Copyright (c) 1998-2009 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Mike Norman - from Proof-of-concept, become production code
 ******************************************************************************/
package  org.eclipse.persistence.platform.database.oracle.publisher.sqlrefl;

/**
 * A Method returns REF CURSOR
 */
public interface CursorMethod {

    public TypeClass getReturnEleType();

    public boolean isSingleCol();

    public String singleColName();

    public boolean returnBeans();

    public boolean returnResultSet();
}
