/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
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
package com.sun.enterprise.deployment.annotation.impl;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.jar.JarFile;
import java.util.jar.JarEntry;

import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import com.sun.enterprise.deployment.ApplicationClientDescriptor;
import com.sun.enterprise.deployment.ServiceReferenceDescriptor;

/**
 * Implementation of the Scanner interface for AppClient
 *
 * @author Shing Wai Chan
 */
public class AppClientScanner extends ModuleScanner {
    private ApplicationClientDescriptor descriptor;
    
    public AppClientScanner(File archiveFile, ApplicationClientDescriptor desc)
            throws IOException {
        this(archiveFile, desc, null);
    }

    /**
     * This scanner will scan the given main class for annotation processing.
     * The archiveFile and libJarFiles correspond to classpath.
     * @param archiveFile
     * @param descriptor
     * @param classLoader
     */
    public AppClientScanner(File archiveFile, ApplicationClientDescriptor desc,
            ClassLoader classLoader) throws IOException {
        if (AnnotationUtils.getLogger().isLoggable(Level.FINE)) {
            AnnotationUtils.getLogger().fine("archiveFile is " + archiveFile);
            AnnotationUtils.getLogger().fine("classLoader is " + classLoader);
        }
        this.descriptor = desc;

        //always add main class
        String mainClassName = desc.getMainClassName();
        addScanClassName(mainClassName);        

        // add callback handle if it exist in appclient-client.xml
        String callbackHandler = desc.getCallbackHandler();
        if (callbackHandler != null && !callbackHandler.trim().equals("")) {
            addScanClassName(desc.getCallbackHandler());
        }

        this.classLoader = classLoader;
        this.archiveFile = archiveFile;
    }
}
