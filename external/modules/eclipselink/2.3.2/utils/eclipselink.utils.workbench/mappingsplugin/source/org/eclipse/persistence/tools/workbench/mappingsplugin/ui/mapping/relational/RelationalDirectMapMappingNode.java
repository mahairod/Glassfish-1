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
package org.eclipse.persistence.tools.workbench.mappingsplugin.ui.mapping.relational;

import org.eclipse.persistence.tools.workbench.framework.app.SelectionActionsPolicy;
import org.eclipse.persistence.tools.workbench.mappingsmodel.mapping.relational.MWRelationalDirectMapMapping;
import org.eclipse.persistence.tools.workbench.mappingsplugin.ui.descriptor.MappingDescriptorNode;
import org.eclipse.persistence.tools.workbench.mappingsplugin.ui.mapping.MappingNode;


public final class RelationalDirectMapMappingNode extends MappingNode {

	
	public RelationalDirectMapMappingNode(MWRelationalDirectMapMapping value, SelectionActionsPolicy mappingNodeTypePolicy, MappingDescriptorNode parent) {
		super(value, mappingNodeTypePolicy, parent);
	}


	// ************** AbstractApplicationNode overrides *************

	protected String accessibleNameKey() {
		return "ACCESSIBLE_DIRECT_MAP_MAPPING_NODE";
	}


	// ************** ApplicationNode implementation *************
	
	public String helpTopicID() {
		return this.getDescriptorNode().mappingHelpTopicPrefix() + ".directMap";
	}

	protected String buildIconKey() {
		return ((MWRelationalDirectMapMapping) getMapping()).iconKey();		
	}


	// ********** MWApplicationNode overrides **********

	protected Class propertiesPageClass() {
		return RelationalDirectMapMappingTabbedPropertiesPage.class;
	}

}