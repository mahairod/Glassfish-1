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

/*
 * Copyright 2004-2005 Sun Microsystems, Inc.  All rights reserved.
 * Use is subject to license terms.
 */
package com.sun.jts.codegen.otsidl;


/**
* com/sun/jts/codegen/otsidl/CoordinatorResourcePOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.1"
* from com/sun/jts/ots.idl
* Tuesday, February 5, 2002 12:57:23 PM PST
*/


//#-----------------------------------------------------------------------------
public abstract class CoordinatorResourcePOA extends org.omg.PortableServer.Servant
 implements com.sun.jts.codegen.otsidl.CoordinatorResourceOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("commit_subtransaction", new java.lang.Integer (0));
    _methods.put ("rollback_subtransaction", new java.lang.Integer (1));
    _methods.put ("prepare", new java.lang.Integer (2));
    _methods.put ("rollback", new java.lang.Integer (3));
    _methods.put ("commit", new java.lang.Integer (4));
    _methods.put ("commit_one_phase", new java.lang.Integer (5));
    _methods.put ("forget", new java.lang.Integer (6));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // CosTransactions/SubtransactionAwareResource/commit_subtransaction
       {
         org.omg.CosTransactions.Coordinator parent = org.omg.CosTransactions.CoordinatorHelper.read (in);
         this.commit_subtransaction (parent);
         out = $rh.createReply();
         break;
       }

       case 1:  // CosTransactions/SubtransactionAwareResource/rollback_subtransaction
       {
         this.rollback_subtransaction ();
         out = $rh.createReply();
         break;
       }

       case 2:  // CosTransactions/Resource/prepare
       {
         try {
           org.omg.CosTransactions.Vote $result = null;
           $result = this.prepare ();
           out = $rh.createReply();
           org.omg.CosTransactions.VoteHelper.write (out, $result);
         } catch (org.omg.CosTransactions.HeuristicMixed $ex) {
           out = $rh.createExceptionReply ();
           org.omg.CosTransactions.HeuristicMixedHelper.write (out, $ex);
         } catch (org.omg.CosTransactions.HeuristicHazard $ex) {
           out = $rh.createExceptionReply ();
           org.omg.CosTransactions.HeuristicHazardHelper.write (out, $ex);
         }
         break;
       }

       case 3:  // CosTransactions/Resource/rollback
       {
         try {
           this.rollback ();
           out = $rh.createReply();
         } catch (org.omg.CosTransactions.HeuristicCommit $ex) {
           out = $rh.createExceptionReply ();
           org.omg.CosTransactions.HeuristicCommitHelper.write (out, $ex);
         } catch (org.omg.CosTransactions.HeuristicMixed $ex) {
           out = $rh.createExceptionReply ();
           org.omg.CosTransactions.HeuristicMixedHelper.write (out, $ex);
         } catch (org.omg.CosTransactions.HeuristicHazard $ex) {
           out = $rh.createExceptionReply ();
           org.omg.CosTransactions.HeuristicHazardHelper.write (out, $ex);
         }
         break;
       }

       case 4:  // CosTransactions/Resource/commit
       {
         try {
           this.commit ();
           out = $rh.createReply();
         } catch (org.omg.CosTransactions.NotPrepared $ex) {
           out = $rh.createExceptionReply ();
           org.omg.CosTransactions.NotPreparedHelper.write (out, $ex);
         } catch (org.omg.CosTransactions.HeuristicRollback $ex) {
           out = $rh.createExceptionReply ();
           org.omg.CosTransactions.HeuristicRollbackHelper.write (out, $ex);
         } catch (org.omg.CosTransactions.HeuristicMixed $ex) {
           out = $rh.createExceptionReply ();
           org.omg.CosTransactions.HeuristicMixedHelper.write (out, $ex);
         } catch (org.omg.CosTransactions.HeuristicHazard $ex) {
           out = $rh.createExceptionReply ();
           org.omg.CosTransactions.HeuristicHazardHelper.write (out, $ex);
         }
         break;
       }

       case 5:  // CosTransactions/Resource/commit_one_phase
       {
         try {
           this.commit_one_phase ();
           out = $rh.createReply();
         } catch (org.omg.CosTransactions.HeuristicHazard $ex) {
           out = $rh.createExceptionReply ();
           org.omg.CosTransactions.HeuristicHazardHelper.write (out, $ex);
         }
         break;
       }

       case 6:  // CosTransactions/Resource/forget
       {
         this.forget ();
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:otsidl/CoordinatorResource:1.0", 
    "IDL:omg.org/CosTransactions/SubtransactionAwareResource:1.0", 
    "IDL:omg.org/CosTransactions/Resource:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public CoordinatorResource _this() 
  {
    return CoordinatorResourceHelper.narrow(
    super._this_object());
  }

  public CoordinatorResource _this(org.omg.CORBA.ORB orb) 
  {
    return CoordinatorResourceHelper.narrow(
    super._this_object(orb));
  }


} // class CoordinatorResourcePOA
