

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


package org.apache.catalina.net;


import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.UnrecoverableKeyException;
import java.security.KeyManagementException;


/**
 * Interface that describes the common characteristics of factory classes
 * that create server sockets which may be required by a Connector.  A concrete
 * implementation of this interface will be assigned to a Connector
 * via the <code>setFactory()</code> method.
 *
 * @author db@eng.sun.com
 * @author Harish Prabandham
 * @author Craig R. McClanahan
 */
public interface ServerSocketFactory {


    // --------------------------------------------------------- Public Methods


    /**
     * Returns a server socket which uses all network interfaces on
     * the host, and is bound to a the specified port.  The socket is
     * configured with the socket options (such as accept timeout)
     * given to this factory.
     *
     * @param port the port to listen to
     *
     * @exception IOException                input/output or network error
     * @exception KeyStoreException          error instantiating the
     *                                       KeyStore from file (SSL only)
     * @exception NoSuchAlgorithmException   KeyStore algorithm unsupported
     *                                       by current provider (SSL only)
     * @exception CertificateException       general certificate error (SSL only)
     * @exception UnrecoverableKeyException  internal KeyStore problem with
     *                                       the certificate (SSL only)
     * @exception KeyManagementException     problem in the key management
     *                                       layer (SSL only)
     */
    public ServerSocket createSocket (int port)
    throws IOException, KeyStoreException, NoSuchAlgorithmException,
           CertificateException, UnrecoverableKeyException,
           KeyManagementException;


    /**
     * Returns a server socket which uses all network interfaces on
     * the host, is bound to a the specified port, and uses the
     * specified connection backlog.  The socket is configured with
     * the socket options (such as accept timeout) given to this factory.
     *
     * @param port the port to listen to
     * @param backlog how many connections are queued
     *
     * @exception IOException                input/output or network error
     * @exception KeyStoreException          error instantiating the
     *                                       KeyStore from file (SSL only)
     * @exception NoSuchAlgorithmException   KeyStore algorithm unsupported
     *                                       by current provider (SSL only)
     * @exception CertificateException       general certificate error (SSL only)
     * @exception UnrecoverableKeyException  internal KeyStore problem with
     *                                       the certificate (SSL only)
     * @exception KeyManagementException     problem in the key management
     *                                       layer (SSL only)
     */
    public ServerSocket createSocket (int port, int backlog)
    throws IOException, KeyStoreException, NoSuchAlgorithmException,
           CertificateException, UnrecoverableKeyException,
           KeyManagementException;


    /**
     * Returns a server socket which uses only the specified network
     * interface on the local host, is bound to a the specified port,
     * and uses the specified connection backlog.  The socket is configured
     * with the socket options (such as accept timeout) given to this factory.
     *
     * @param port the port to listen to
     * @param backlog how many connections are queued
     * @param ifAddress the network interface address to use
     *
     * @exception IOException                input/output or network error
     * @exception KeyStoreException          error instantiating the
     *                                       KeyStore from file (SSL only)
     * @exception NoSuchAlgorithmException   KeyStore algorithm unsupported
     *                                       by current provider (SSL only)
     * @exception CertificateException       general certificate error (SSL only)
     * @exception UnrecoverableKeyException  internal KeyStore problem with
     *                                       the certificate (SSL only)
     * @exception KeyManagementException     problem in the key management
     *                                       layer (SSL only)
     */
    public ServerSocket createSocket (int port, int backlog,
                                      InetAddress ifAddress)
    throws IOException, KeyStoreException, NoSuchAlgorithmException,
           CertificateException, UnrecoverableKeyException,
           KeyManagementException;


}
