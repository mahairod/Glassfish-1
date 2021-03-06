// $Id: ConstraintValidatorFactoryImpl.java 17620 2009-10-04 19:19:28Z hardy.ferentschik $
/*
* JBoss, Home of Professional Open Source
* Copyright 2009, Red Hat, Inc. and/or its affiliates, and individual contributors
* by the @authors tag. See the copyright.txt in the distribution for a
* full listing of individual contributors.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
* http://www.apache.org/licenses/LICENSE-2.0
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.hibernate.validator.engine;

import java.security.AccessController;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorFactory;

import org.hibernate.validator.util.NewInstance;

/**
 * Default <code>ConstraintValidatorFactory</code> using a no-arg constructor.
 *
 * @author Emmanuel Bernard
 * @author Hardy Ferentschik
 */
public class ConstraintValidatorFactoryImpl implements ConstraintValidatorFactory {

	public <T extends ConstraintValidator<?, ?>> T getInstance(Class<T> key) {
		NewInstance<T> newInstance = NewInstance.action( key, "" );
		if ( System.getSecurityManager() != null ) {
			return AccessController.doPrivileged( newInstance );
		}
		else {
			return newInstance.run();
		}
	}
}
