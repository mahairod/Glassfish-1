

/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
 * 
 * Portions Copyright Apache Software Foundation.
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

package org.apache.naming.resources;

import java.net.URL;
import java.net.URLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.security.Permission;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;
import javax.naming.NamingException;
import javax.naming.NamingEnumeration;
import javax.naming.NameClassPair;
import javax.naming.directory.DirContext;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import org.apache.naming.JndiPermission;
import org.apache.naming.resources.Resource;
import org.apache.naming.resources.ResourceAttributes;

/**
 * Connection to a JNDI directory context.
 * <p/>
 * Note: All the object attribute names are the WebDAV names, not the HTTP 
 * names, so this class overrides some methods from URLConnection to do the
 * queries using the right names. Content handler is also not used; the 
 * content is directly returned.
 * 
 * @author <a href="mailto:remm@apache.org">Remy Maucherat</a>
 * @version $Revision: 1.3 $
 */
public class DirContextURLConnection 
    extends URLConnection {
    
    
    // ----------------------------------------------------------- Constructors
    
    
    public DirContextURLConnection(DirContext context, URL url) {
        super(url);
        if (context == null)
            throw new IllegalArgumentException
                ("Directory context can't be null");
        if (IS_SECURITY_ENABLED) {
            this.permission = new JndiPermission(url.toString());
	}
        this.context = context;
    }
    
    
    // ----------------------------------------------------- Instance Variables
    
    
    /**
     * Directory context.
     */
    protected DirContext context;
    
    
    /**
     * Associated resource.
     */
    protected Resource resource;
    
    
    /**
     * Associated DirContext.
     */
    protected DirContext collection;
    
    
    /**
     * Other unknown object.
     */
    protected Object object;
    
    
    /**
     * Attributes.
     */
    protected Attributes attributes;
    
    
    /**
     * Date.
     */
    protected long date;
    
    
    /**
     * Permission
     */
    protected Permission permission;


    /**
     * Is the Java SecurityManager enabled?
     */
    public static final boolean IS_SECURITY_ENABLED =
        (System.getSecurityManager() != null);


    // ------------------------------------------------------------- Properties
    
    
    /**
     * Connect to the DirContext, and retrive the bound object, as well as
     * its attributes. If no object is bound with the name specified in the
     * URL, then an IOException is thrown.
     * 
     * @throws IOException Object not found
     */
    public void connect()
        throws IOException {
        
        if (!connected) {
            
            try {
                date = System.currentTimeMillis();
                String path = getURL().getFile();
                if (context instanceof ProxyDirContext) {
                    ProxyDirContext proxyDirContext = 
                        (ProxyDirContext) context;
                    String hostName = proxyDirContext.getHostName();
                    String contextName = proxyDirContext.getContextName();
                    if (hostName != null) {
                        if (!path.startsWith("/" + hostName + "/"))
                            return;
                        path = path.substring(hostName.length()+ 1);
                    }
                    if (contextName != null) {
                        if (!path.startsWith(contextName + "/")) {
                            return;
                        } else {
                            path = path.substring(contextName.length());
                        }
                    }
                }
                object = context.lookup(path);
                attributes = context.getAttributes(path);
                if (object instanceof Resource)
                    resource = (Resource) object;
                if (object instanceof DirContext)
                    collection = (DirContext) object;
            } catch (NamingException e) {
                // Object not found
            }
            
            connected = true;
            
        }
        
    }
    
    
    /**
     * Return the content length value.
     */
    public int getContentLength() {
        return getHeaderFieldInt(ResourceAttributes.CONTENT_LENGTH, -1);
    }
    
    
    /**
     * Return the content type value.
     */
    public String getContentType() {
        return getHeaderField(ResourceAttributes.CONTENT_TYPE);
    }
    
    
    /**
     * Return the last modified date.
     */
    public long getDate() {
        return date;
    }
    
    
    /**
     * Return the last modified date.
     */
    public long getLastModified() {

        if (!connected) {
            // Try to connect (silently)
            try {
                connect();
            } catch (IOException e) {
            }
        }

        if (attributes == null)
            return 0;

        Attribute lastModified = 
            attributes.get(ResourceAttributes.LAST_MODIFIED);
        if (lastModified != null) {
            try {
                Date lmDate = (Date) lastModified.get();
                return lmDate.getTime();
            } catch (Exception e) {
            }
        }

        return 0;
    }
    
    
    /**
     * Returns the name of the specified header field.
     */
    public String getHeaderField(String name) {

        if (!connected) {
            // Try to connect (silently)
            try {
                connect();
            } catch (IOException e) {
            }
        }
        
        if (attributes == null)
            return (null);

        Attribute attribute = attributes.get(name);
        try {
            return attribute.get().toString();
        } catch (Exception e) {
            // Shouldn't happen, unless the attribute has no value
        }

        return (null);
        
    }
    
    
    /**
     * Get object content.
     */
    public Object getContent()
        throws IOException {
        
        if (!connected)
            connect();
        
        if (resource != null)
            return getInputStream();
        if (collection != null)
            return collection;
        if (object != null)
            return object;
        
        throw new FileNotFoundException();
        
    }
    
    
    /**
     * Get object content.
     */
    public Object getContent(Class[] classes)
        throws IOException {
        
        Object object = getContent();
        
        for (int i = 0; i < classes.length; i++) {
            if (classes[i].isInstance(object))
                return object;
        }
        
        return null;
        
    }
    
    
    /**
     * Get input stream.
     */
    public InputStream getInputStream() 
        throws IOException {
        
        if (!connected)
            connect();
        
        if (resource == null) {
            throw new FileNotFoundException();
        } else {
            // Reopen resource
            try {
                resource = (Resource) context.lookup(getURL().getFile());
            } catch (NamingException e) {
            }
        }
        
        return (resource.streamContent());
        
    }
    
    
    /**
     * Get the Permission for this URL
     */
    public Permission getPermission() {

        return permission;
    }


    // --------------------------------------------------------- Public Methods
    
    
    /**
     * List children of this collection. The names given are relative to this
     * URI's path. The full uri of the children is then : path + "/" + name.
     */
    public Enumeration list()
        throws IOException {
        
        if (!connected) {
            connect();
        }
        
        if ((resource == null) && (collection == null)) {
            throw new FileNotFoundException();
        }
        
        Vector result = new Vector();
        
        if (collection != null) {
            try {
                NamingEnumeration enumeration = context.list(getURL().getFile());
                while (enumeration.hasMoreElements()) {
                    NameClassPair ncp = (NameClassPair) enumeration.nextElement();
                    result.addElement(ncp.getName());
                }
            } catch (NamingException e) {
                // Unexpected exception
                throw new FileNotFoundException();
            }
        }
        
        return result.elements();
        
    }
    
    
}
