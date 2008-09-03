/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright 1997-2008 Sun Microsystems, Inc. All rights reserved.
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

package com.sun.enterprise.admin.cli.remote;

import com.sun.enterprise.cli.framework.*;
import com.sun.enterprise.universal.NameValue;
import com.sun.enterprise.universal.StringUtils;
import com.sun.enterprise.universal.collections.CollectionUtils;
import com.sun.enterprise.universal.glassfish.AdminCommandResponse;
import java.io.*;
import java.util.*;
import java.util.jar.*;

/**
 *
 * @author bnevins
 */
class ManifestManager implements ResponseManager {
    ManifestManager(InputStream inStream) throws RemoteException, IOException  {
        response = new AdminCommandResponse(inStream);
    }

    public Map<String,String> getMainAtts() {
        return response.getMainAtts();
    }
    
    public void process() throws RemoteException {
        Log.finer("PROCESSING MANIFEST...");
        
        // remember these are "succeed-fast".  They will throw a 
        // RemoteSuccessException if they succeed...
        processGeneratedManPage();
        processManPage();
        processGeneric();

        // No RemoteSuccessException was thrown -- this is now an Error!!
        throw new RemoteFailureException("Could not process");
    }

    
    private void processManPage() throws RemoteSuccessException {
        String manPage = response.getValue(AdminCommandResponse.MANPAGE);

        if(!ok(manPage)) 
            return;

        throw new RemoteSuccessException(manPage);
    }

    private void processGeneratedManPage() throws RemoteException {
        if(!response.isGeneratedHelp())
            return;
        GeneratedManPageManager mgr = new GeneratedManPageManager(response);
        mgr.process();
    }

    private void processGeneric() throws RemoteSuccessException, RemoteFailureException {
        StringBuilder sb = new StringBuilder();
        String msg = response.getMainMessage();
        if(ok(msg))
            sb.append(msg).append(EOL);

        processOneLevel("", null, response.getMainAtts(), sb);

        if(!response.wasSuccess()) {
            final String cause = response.getCause();
            if(ok(cause)){
                sb.append(cause);
            }
            throw new RemoteFailureException(sb.toString());
        }        

        throw new RemoteSuccessException(sb.toString());
    }

    // this is just HORRIBLE -- but that's the way it is presented from the 
    // server.  I imagine tons of bug reports on this coming up...
    private void processOneLevel(String prefix, String key, 
            Map<String,String> atts, StringBuilder sb) {

        if(atts == null)
            return;

        processProps(prefix, atts, sb);
        processChildren(prefix, key, atts, sb);
    }

    private void processProps(String prefix, Map<String, String> atts, StringBuilder sb) {
        // output "properties=(a=b,c=d)"
        List<NameValue<String,String>> props = response.getKeys(atts);

        for(Iterator<NameValue<String,String>> it = props.iterator(); it.hasNext(); ) {
            NameValue<String,String> nv = it.next();
            if(nv.getName().startsWith("nb-"))
                it.remove();
        }
        
        if(props == null || props.isEmpty()) 
            return;
        
        sb.append(prefix).append("properties=(");
        boolean first = true;

        for(NameValue<String,String> nv : props) {
            String name = nv.getName();
            String value = nv.getValue();

            if(first)
                first = false;
            else
                sb.append(",");

            sb.append(name + "=" + value);
        }
        sb.append(")").append(EOL);
    }

    private void processChildren(String prefix, String parent, Map<String, String> atts, StringBuilder sb) {

        Map<String,Map<String,String>> kids = response.getChildren(atts);
        
        if(kids == null || kids.isEmpty())
            return;

        String childrenType = atts.get(AdminCommandResponse.CHILDREN_TYPE);
        int index = (parent == null) ? 0 : parent.length() + 1; 
        
        for(Map.Entry<String, Map<String,String>> entry : kids.entrySet()) {
            String container = entry.getKey();
            
            if(ok(childrenType))
                sb.append(prefix).append(childrenType).append(" : ");
            
            sb.append(container.substring(index)).append(EOL);
            processOneLevel(prefix + TAB, container, entry.getValue(), sb);
        }
    }

    private void dump() {
        
    }
    
    private boolean ok(String s) {
        return s != null && s.length() > 0 && !s.equals("null");
    }

    private AdminCommandResponse response;
    private static final String EOL = StringUtils.EOL;
    private static final String TAB = "    ";
}
 
