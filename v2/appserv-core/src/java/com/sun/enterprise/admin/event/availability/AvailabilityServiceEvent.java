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

package com.sun.enterprise.admin.event.availability;

import com.sun.enterprise.admin.event.ElementChangeEvent;

/**
 * <pre>
 *      When any of the following attributes of availability-service 
 *      element is changed,
 *      AvailabilityServiceEvent is sent.
 *          auto-manage-ha-store, ha-agent-hosts, ha-agent-port, 
 *          ha-store-healthcheck-enabled, 
 *          ha-store-healthcheck-interval-in-seconds, ha-store-name,          
 *          and store-pool-name.
 * </pre>
 *
 * @author: Larry White
 */
public class AvailabilityServiceEvent extends ElementChangeEvent {

    /**
     * Event type
     */
    public static final String eventType = AvailabilityServiceEvent.class.getName();

    /**
     * Create a new AvailabilityServiceEvent.
     *
     * @param instance name of the instance to which the event applies
     * @param action type of action - one of ElementChangeEvent.ACTION_ELEMENT_*
     *
     * @throws IllegalArgumentException if specified action is not valid
     */
    public AvailabilityServiceEvent(String instance, int action) {
        super(instance, AvailabilityServiceEvent.eventType, action, null);
    }

}
