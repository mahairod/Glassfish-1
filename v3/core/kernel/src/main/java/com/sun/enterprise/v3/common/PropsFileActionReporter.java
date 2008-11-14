/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2008 Sun Microsystems, Inc. All rights reserved.
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
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
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

package com.sun.enterprise.v3.common;

import org.jvnet.hk2.annotations.Service;
import org.jvnet.hk2.annotations.Scoped;
import org.jvnet.hk2.component.PerLookup;

import java.io.PrintWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.jar.Manifest;
import java.util.jar.Attributes;
import java.util.Map;

/**
 * Action reporter to a manifest file
 * @author Jerome Dochez
 */
@Service(name="hk2-agent")
@Scoped(PerLookup.class)
public class PropsFileActionReporter extends ActionReporter {

    public void writeReport(OutputStream os) throws IOException {

        Manifest out = new Manifest();
        Attributes mainAttr = out.getMainAttributes();
        mainAttr.put(Attributes.Name.SIGNATURE_VERSION, "1.0");
        mainAttr.putValue("exit-code", exitCode.toString());
        mainAttr.putValue("use-main-children-attribute", Boolean.toString(useMainChildrenAttr));
        
        if(exitCode == ExitCode.FAILURE)
            writeCause(mainAttr);
        
        writeReport(null, topMessage, out, mainAttr);
        out.write(os);
    }

    public void writeReport(String prefix, MessagePart part, Manifest m,  Attributes attr) {
        
        attr.putValue("message", part.getMessage());
        if (part.getProps().size()>0) {
            String keys=null;
            for (Map.Entry entry : part.getProps().entrySet()) {
                String key  = entry.getKey().toString().replaceAll(" ", "_");
                keys = (keys==null?key:keys + ";" + key);
                attr.putValue(key+"_name", entry.getKey().toString());
                attr.putValue(key+"_value", entry.getValue().toString());
            }
         
            attr.putValue("keys", keys);
        }
        if (part.getChildren().size()>0) {
            attr.putValue("children-type", part.getChildrenType());
            String keys=null;
            for (MessagePart child : part.getChildren()) {
                String newPrefix = (prefix==null?child.getMessage():prefix+"."+child.getMessage());
                keys = (keys==null?newPrefix:keys + ";" + newPrefix);
                Attributes childAttr = new Attributes();
                m.getEntries().put(newPrefix, childAttr);
                writeReport(newPrefix, child, m, childAttr);
            }
            attr.putValue("children", keys);
        }
    }

    private void writeCause(Attributes mainAttr) {
        Throwable t = getFailureCause();

        if(t == null)
            return;

        String causeMessage = t.toString();
        mainAttr.putValue("cause", causeMessage);
    }

   /* Issue 5918 Keep output sorted. If set to true ManifestManager will grab 
    * "children" from main attributes. "children" is in original order of
    * output set by server-side
    */
    public void useMainChildrenAttribute(boolean useMainChildrenAttr) {
        this.useMainChildrenAttr = useMainChildrenAttr;
    }

    private boolean useMainChildrenAttr = false;
}

