/*
 * @(#)$Id: ElementState.java 1566 2003-06-09 20:37:49Z kk122374 $
 *
 * Copyright 2001 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Sun Microsystems, Inc.  
 * Use is subject to license terms.
 * 
 */
package com.sun.msv.reader.trex.ng;


/**
 * parses &lt;element&gt; pattern.
 * 
 * @author <a href="mailto:kohsuke.kawaguchi@eng.sun.com">Kohsuke KAWAGUCHI</a>
 */
public class ElementState extends com.sun.msv.reader.trex.ElementState {
    
    private boolean previousDirectReference;
    
    protected void startSelf() {
        super.startSelf();
        
        // set directReference to false.
        previousDirectReference = ((RELAXNGReader)reader).directRefernce;
        ((RELAXNGReader)reader).directRefernce = false;
    }
    
    protected void endSelf() {
        final RELAXNGReader reader = (RELAXNGReader)this.reader;
        
        reader.directRefernce = previousDirectReference;
        super.endSelf();

        reader.restrictionChecker.checkNameClass(nameClass);
    }
}
