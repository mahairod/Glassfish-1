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

package com.sun.enterprise.cli.commands;

import com.sun.enterprise.cli.framework.*;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import com.sun.appserv.management.util.misc.ExceptionUtil;
import com.sun.appserv.management.base.Util;
import javax.management.MalformedObjectNameException;
import java.lang.NullPointerException;
import javax.management.RuntimeOperationsException;

import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import java.lang.IllegalArgumentException;
import java.io.File;
import java.io.IOException;



/**
 *  @version  $Revision: 1.3 $
 */
public class AMXDeleteResourceCommand extends S1ASCommand
{
    public final static String DOMAIN_CONFIG_OBJECT_NAME = "amx:j2eeType=X-DomainConfig,name=na";
    public final static String SERVER_CONFIG_OBJECT_NAME = "amx:j2eeType=X-StandaloneServerConfig,name=";
    public final static String CLUSTER_CONFIG_OBJECT_NAME = "amx:j2eeType=X-ClusterConfig,name=";
    public final static String TARGET_NAME = "target";

    /**
     *  An abstract method that validates the options 
     *  on the specification in the xml properties file
     *  This method verifies for the correctness of number of 
     *  operands and if all the required options are supplied by the client.
     *  @return boolean returns true if success else returns false
     */
    public boolean validateOptions() throws CommandValidationException
    {
    	return super.validateOptions();
    }
   
    /**
     *  An abstract method that Executes the command
     *  @throws CommandException
     */
    public void runCommand() throws CommandException, CommandValidationException
    {
        if (!validateOptions())
            throw new CommandValidationException("Validation is false");
        
        //use http connector
        MBeanServerConnection mbsc = getMBeanServerConnection(getHost(), getPort(), 
                                                              getUser(), getPassword());
        final String targetName = (String)getOption(TARGET_NAME);
        
        //if targetName is not null, then try to get the Config ObjectName of the
        //target before deleting the resource because we don't want to delete
        //the resource if the target does not exist.
        ObjectName scON = (targetName!=null && !targetName.equals(DOMAIN))?
                          getTargetConfigObjectName(mbsc, targetName):null;

        final Object[] params = getParamsInfo();
        final String operationName = getOperationName();
        final String[] types = getTypesInfo();
        
        try {
            if (scON!=null)
            {
                //remove reference to the target
                mbsc.invoke(scON, "removeResourceRefConfig",
                            new Object[]{new String((String)getOperands().get(0))},
                            new String[]{"java.lang.String"} );
                        
            }
            Object returnValue = mbsc.invoke(Util.newObjectName(DOMAIN_CONFIG_OBJECT_NAME),
                                             operationName,
                                             params, types);
            CLILogger.getInstance().printDetailMessage(getLocalizedString(
                                                       "CommandSuccessful",
                                                       new Object[] {name}));
        }
        catch (Exception e) {
            displayExceptionMessage(e);
        }


    }

    
        /**
         * This routine will display the exception message if the option
         * --terse is given.  This routine will get the root of the exception
         * and display the message.  It will then wrap it with CommaneException
         * and throw the exception to be handled by CLI framework.
         * @param e
         * @throws CommandException
         */
    public void displayExceptionMessage(Exception e) throws CommandException
    {
            //get the root cause of the exception

        Throwable rootException = ExceptionUtil.getRootCause(e);

        if (rootException.getLocalizedMessage() != null)
            CLILogger.getInstance().printDetailMessage(rootException.getLocalizedMessage());
        throw new CommandException(getLocalizedString("CommandUnSuccessful",
                                                      new Object[] {name} ), e);

    }



        /**
         *  This routine will get the StandaloneServerConfig or ClusterConfig
         *  by the given target name.
         *  @param MBeanServerConnection
         *  @param targetName
         *  @return ObjectName
         */
    private ObjectName getTargetConfigObjectName(final MBeanServerConnection mbsc,
                                                 final String targetName)
        throws CommandException
    {

        try {
            ObjectName scON = Util.newObjectName(SERVER_CONFIG_OBJECT_NAME+targetName);
            if (!mbsc.isRegistered(scON))
                scON = Util.newObjectName(CLUSTER_CONFIG_OBJECT_NAME+targetName);
            if (!mbsc.isRegistered(scON))
                throw new CommandException(getLocalizedString("InvalidTargetName"));
        
            return scON;            
        }
        catch (RuntimeOperationsException roe)
        {
            throw new CommandException(roe);
        }
        catch (IOException ioe)
        {
            throw new CommandException(ioe);
        }
    }

}
