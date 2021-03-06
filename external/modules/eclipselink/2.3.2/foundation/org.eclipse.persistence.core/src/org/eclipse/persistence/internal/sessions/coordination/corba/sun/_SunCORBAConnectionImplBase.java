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
package org.eclipse.persistence.internal.sessions.coordination.corba.sun;


/**
* org/eclipse/persistence/internal/remotecommand/corba/sun/_SunCORBAConnectionImplBase.java .
* Generated by the IDL-to-Java compiler (portable), version "3.1"
* from rcm.idl
* Tuesday, March 30, 2004 2:00:14 PM EST
*/
public abstract class _SunCORBAConnectionImplBase extends org.omg.CORBA.portable.ObjectImpl implements org.eclipse.persistence.internal.sessions.coordination.corba.sun.SunCORBAConnection, org.omg.CORBA.portable.InvokeHandler {
    // Constructors
    public _SunCORBAConnectionImplBase() {
    }

    private static java.util.Hashtable _methods = new java.util.Hashtable();

    static {
        _methods.put("executeCommand", new java.lang.Integer(0));
    }

    public org.omg.CORBA.portable.OutputStream _invoke(String $method, org.omg.CORBA.portable.InputStream in, org.omg.CORBA.portable.ResponseHandler $rh) {
        org.omg.CORBA.portable.OutputStream out = null;
        java.lang.Integer __method = (java.lang.Integer)_methods.get($method);
        if (__method == null) {
            throw new org.omg.CORBA.BAD_OPERATION(0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
        }

        switch (__method.intValue()) {
        case 0:// org/eclipse/persistence/internal/remotecommand/corba/sun/SunCORBAConnection/executeCommand
         {
            byte[] commandData = org.eclipse.persistence.internal.sessions.coordination.corba.sun.CommandDataHelper.read(in);
            byte[] $result = null;
            $result = this.executeCommand(commandData);
            out = $rh.createReply();
            org.eclipse.persistence.internal.sessions.coordination.corba.sun.CommandDataHelper.write(out, $result);
            break;
        }
        default:
            throw new org.omg.CORBA.BAD_OPERATION(0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
        }

        return out;
    }
    // _invoke

    // Type-specific CORBA::Object operations
    private static String[] __ids = { "IDL:org/eclipse/persistence/internal/remotecommand/corba/sun/SunCORBAConnection:1.0" };

    public String[] _ids() {
        return __ids.clone();
    }
}// class _SunCORBAConnectionImplBase
