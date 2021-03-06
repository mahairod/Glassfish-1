/*
 * @(#)$Id: StringCareLevelCalculator.java 1566 2003-06-09 20:37:49Z kk122374 $
 *
 * Copyright 2001 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Sun Microsystems, Inc.  
 * Use is subject to license terms.
 * 
 */
package com.sun.msv.verifier.regexp;

import com.sun.msv.grammar.AttributeExp;
import com.sun.msv.grammar.DataExp;
import com.sun.msv.grammar.ElementExp;
import com.sun.msv.grammar.Expression;
import com.sun.msv.grammar.ListExp;
import com.sun.msv.grammar.MixedExp;
import com.sun.msv.grammar.ValueExp;
import com.sun.msv.grammar.util.ExpressionFinder;
import com.sun.msv.verifier.Acceptor;

/**
 * calculates how character literals should be treated.
 * 
 * This class is thread-safe: multiple threads can simultaneously
 * access the same instance. Note that there is no guarantee that the
 * derived class is thread-safe.
 * 
 * @author <a href="mailto:kohsuke.kawaguchi@eng.sun.com">Kohsuke KAWAGUCHI</a>
 */
public class StringCareLevelCalculator extends ExpressionFinder {
    
    protected StringCareLevelCalculator(){}
    
    /** singleton instance. */
    protected static final StringCareLevelCalculator theInstance = new StringCareLevelCalculator();
    
    // those expressions which are sensitive about string must return true
    public boolean onAttribute( AttributeExp exp )        { return false; }
    public boolean onElement( ElementExp exp )            { return false; }
    public boolean onMixed( MixedExp exp )                { return true; }
    public boolean onList( ListExp exp )                { return true; }
    public boolean onAnyString()                        { return true; }
    public boolean onData( DataExp exp )                { return true; }
    public boolean onValue( ValueExp exp )                { return true; }

    public static int calc( Expression exp )
    {
        // if and only if the top-level element is mixed,
        // it can ignores strings.
        if( exp instanceof MixedExp )    return Acceptor.STRING_IGNORE;
        
        if( exp.visit(theInstance) )
            // somebody claims that string is necessary.
            return Acceptor.STRING_STRICT;
        else
            // nobody claims that string is necessary.
            return Acceptor.STRING_PROHIBITED;
    }
}
