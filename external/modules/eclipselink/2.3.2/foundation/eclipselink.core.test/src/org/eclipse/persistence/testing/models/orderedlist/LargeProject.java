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
 *     05/05/2009 Andrei Ilitchev 
 *       - JPA 2.0 - OrderedList support.
 ******************************************************************************/  
package org.eclipse.persistence.testing.models.orderedlist;

import java.io.*;
import java.sql.Timestamp;

/**
 * <b>Purpose</b>: Larger scale projects within the Employee Demo
 * <p><b>Description</b>: LargeProject is a concrete subclass of Project. It is instantiated for Projects with type = 'L'. The additional
 * information (budget, & milestoneVersion) are mapped from the LPROJECT table.
 * @see Project
 */
public class LargeProject extends Project {
    // implements ChangeTracker for testing(Inherited from Project) 
    public double budget;
    public Timestamp milestoneVersion;

    public LargeProject() {
        super();
        this.budget = 0.0;
    }

    public LargeProject(String name) {
        super(name);
        this.budget = 0.0;
    }
    
    public double getBudget() {
        return budget;
    }

    public Timestamp getMilestoneVersion() {
        return milestoneVersion;
    }

    public void setBudget(double budget) {
        propertyChange("budget", new Double(this.budget), new Double(budget));
        this.budget = budget;
    }

    public void setMilestoneVersion(Timestamp milestoneVersion) {
        propertyChange("milestoneVersion", this.milestoneVersion, milestoneVersion);
        this.milestoneVersion = milestoneVersion;
    }

    /**
     * Print the project's data.
     */
    public String toString() {
        StringWriter writer = new StringWriter();

        writer.write("Large Project: ");
        writer.write(getName());
        writer.write(" ");
        writer.write(getDescription());
        writer.write(" " + getBudget());
        writer.write(" ");
        writer.write(String.valueOf(getMilestoneVersion()));
        return writer.toString();
    }
}