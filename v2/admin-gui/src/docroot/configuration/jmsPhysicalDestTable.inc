<!--
 DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 
 Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
 
 The contents of this file are subject to the terms of either the GNU
 General Public License Version 2 only ("GPL") or the Common Development
 and Distribution License("CDDL") (collectively, the "License").  You
 may not use this file except in compliance with the License. You can obtain
 a copy of the License at https://glassfish.dev.java.net/public/CDDL+GPL.html
 or glassfish/bootstrap/legal/LICENSE.txt.  See the License for the specific
 language governing permissions and limitations under the License.
 
 When distributing the software, include this License Header Notice in each
 file and include the License file at glassfish/bootstrap/legal/LICENSE.txt.
 Sun designates this particular file as subject to the "Classpath" exception
 as provided by Sun in the GPL Version 2 section of the License file that
 accompanied this code.  If applicable, add the following below the License
 Header, with the fields enclosed by brackets [] replaced by your own
 identifying information: "Portions Copyrighted [year]
 [name of copyright owner]"
 
 Contributor(s):
 
 If you wish your version of this file to be governed by only the CDDL or
 only the GPL Version 2, indicate your decision by adding "[Contributor]
 elects to include this software in this distribution under the [CDDL or GPL
 Version 2] license."  If you don't indicate a single choice of license, a
 recipient has the option to distribute your version of this file under
 either the CDDL, the GPL Version 2 or to extend the choice of license to
 its licensees as provided above.  However, if you add GPL Version 2 code
 and therefore, elected the GPL Version 2 license, then the option applies
 only if the new code is made subject to such option by the copyright
 holder.
-->
<!-- configuration/jmsPhysicalDestTable.inc -->

<sun:table id="configs" title="$resource{i18n.jmsPhysDestinations.TableTitle}">
    <!facet actionsTop>
         <sun:panelGroup id="topActionsGroup1">
            <sun:button id="newButton" text="$resource{i18n.button.New}" >
                <!command redirect(page="jmsPhysicalDestinationNew.jsf?configName=#{configName}"); />
            </sun:button>
              
            <sun:button id="deleteButton" text="$resource{i18n.button.Delete}" disabled="#{false}" primary="#{false}"
                onClick="if ( getConfirm(this,'$resource{i18n.msg.JS.confirmDeletePhysicalDestinations}') )
                { return submitAndDisable(this, '$resource{i18n.button.Processing}');} 
                else {return false;}" 
            >
            <!command 
                getUIComponent(clientId="$pageSession{configsTableRowGroupId}", component=>$attribute{tableRowGroup});      
                getSelectedSingleMapRows(TableRowGroup="${tableRowGroup}" selectedRows=>$attribute{selectedRows});
                deleteConfig(ConfigName="#{configName}", Type="jmsPhysicalDestination", selectedRows="${selectedRows}");
                setAttribute(key="selectedRows" value="${selectedRows}");
                redirect(page="jmsPhysicalDestinations.jsf?configName=#{configName}");
            />            
            </sun:button>
            <sun:button id="flushButton" text="$resource{i18n.button.Flush}" primary="#{false}" >
                <!command 
                    getUIComponent(clientId="$pageSession{configsTableRowGroupId}", component=>$attribute{tableRowGroup});      
                    getSelectedSingleMapRows(TableRowGroup="${tableRowGroup}" selectedRows=>$attribute{selectedRows});
                    flushJMSDestination(ConfigName="#{configName}", selectedRows="${selectedRows}");
                />
            </sun:button>
         </sun:panelGroup>
     </facet>

   <sun:tableRowGroup id="rowGroup1" selected="#{td.value.selected}" data={"$attribute{listOfRows}"} sourceVar="td">
     <!afterCreate
         getClientId(component="$this{component}" clientId=>$page{configsTableRowGroupId});
        />
    <sun:tableColumn selectId="select" rowHeader="$boolean{false}" id="col0">
            <sun:checkbox id="select" selected="#{td.value.selected}" selectedValue="$boolean{true}" />
    </sun:tableColumn>    

    <sun:tableColumn headerText="$resource{i18n.common.PropertyName}"  rowHeader="$boolean{true}" id="col11">
        <staticText id="nameCol" value="#{td.value.name}" />
    </sun:tableColumn>

    <sun:tableColumn headerText="$resource{i18n.common.type.header}"  rowHeader="$boolean{true}" id="col7">
        <staticText id="typeCol"  value="#{td.value.type}" />
    </sun:tableColumn>
  </sun:tableRowGroup>
  
</sun:table>
