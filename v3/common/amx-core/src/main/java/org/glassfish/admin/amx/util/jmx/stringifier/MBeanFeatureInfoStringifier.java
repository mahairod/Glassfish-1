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
 * $Header: /cvs/glassfish/appserv-api/src/java/com/sun/appserv/management/util/jmx/stringifier/MBeanFeatureInfoStringifier.java,v 1.2 2007/05/05 05:31:05 tcfujii Exp $
 * $Revision: 1.2 $
 * $Date: 2007/05/05 05:31:05 $
 */
 
package org.glassfish.admin.amx.util.jmx.stringifier;

import org.glassfish.admin.amx.util.ClassUtil;
import org.glassfish.admin.amx.util.stringifier.ArrayStringifier;

import javax.management.MBeanParameterInfo;
import java.lang.reflect.Array;
	
public class MBeanFeatureInfoStringifier
{
	final MBeanFeatureInfoStringifierOptions	mOptions;
	
	public static final MBeanFeatureInfoStringifierOptions	DEFAULT =
			new MBeanFeatureInfoStringifierOptions( true, ",");
	
	static final String sOperationDelimiter	= ",";
	
	MBeanFeatureInfoStringifier()
	{
		mOptions	= DEFAULT;
	}
	
	MBeanFeatureInfoStringifier( MBeanFeatureInfoStringifierOptions options )
	{
		mOptions	= options;
	}
	

	
		static String
	getPresentationTypeString( String type )
	{
		return( ClassUtil.getFriendlyClassname( type ) );
	}
	
	
		static String
	ParamsToString( final MBeanParameterInfo [] params, final MBeanFeatureInfoStringifierOptions options )
	{
		String	result	= "";
		
		if ( Array.getLength( params ) != 0 )
		{
			result	= ArrayStringifier.stringify( params,
							options.mArrayDelimiter,
							new MBeanParameterInfoStringifier( options ) );
		}
		else
		{
			result	= "void";
		}
		return( result );
	}

	
	
}