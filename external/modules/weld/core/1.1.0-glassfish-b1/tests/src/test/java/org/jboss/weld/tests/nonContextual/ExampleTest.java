/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat, Inc., and individual contributors
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
package org.jboss.weld.tests.nonContextual;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.inject.spi.InjectionTarget;

import org.jboss.metadata.validation.ValidationException;
import org.jboss.testharness.impl.packaging.Artifact;
import org.jboss.weld.test.AbstractWeldTest;
import org.testng.Assert;
import org.testng.annotations.Test;

@Artifact
public class ExampleTest extends AbstractWeldTest
{
   @Test
   public void testNonContextual() throws Exception 
   {
      NonContextual<External> nonContextual = new NonContextual<External>(getCurrentManager(), External.class);
      
      External external = new External();
      Assert.assertNull(external.bean);
      nonContextual.postConstruct(external);
      Assert.assertNotNull(external.bean);
      nonContextual.preDestroy(external);
      // preDestroy doesn't cause any dis-injection
      Assert.assertNotNull(external.bean);      
   }
   
   @Test
   public void validateNonContextual() throws Exception
   {
      NonContextual<External> nonContextual = new NonContextual<External>(getCurrentManager(), External.class);

      for (InjectionPoint point : nonContextual.it.getInjectionPoints())
      {
         try
         {
            getCurrentManager().validate(point);
         }
         catch(ValidationException e)
         {
            Assert.fail("Should have been valid");
         }
      }
   }
   
   
   public class NonContextual<T> {

      final InjectionTarget<T> it;
      final BeanManager manager;

      public NonContextual(BeanManager manager, Class<T> clazz) {
         this.manager = manager;
         AnnotatedType<T> type = manager.createAnnotatedType(clazz);
         this.it = manager.createInjectionTarget(type);
      }

      public CreationalContext<T> postConstruct(T instance) {
         CreationalContext<T> cc = manager.createCreationalContext(null);
         it.inject(instance, cc);
         it.postConstruct(instance);
         return cc;
      }

      public void preDestroy(T instance) {
         it.preDestroy(instance);
      }
   }

}
