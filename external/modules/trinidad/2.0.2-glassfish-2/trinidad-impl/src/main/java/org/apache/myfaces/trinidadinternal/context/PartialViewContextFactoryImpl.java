/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.apache.myfaces.trinidadinternal.context;

import javax.faces.context.FacesContext;
import javax.faces.context.FacesContextFactory;
import javax.faces.context.PartialViewContext;
import javax.faces.context.PartialViewContextFactory;

public class PartialViewContextFactoryImpl
  extends PartialViewContextFactory
{
  public PartialViewContextFactoryImpl(PartialViewContextFactory factory)
  {
    _factory = factory;
  }

  /**
   * Creates a PartialViewContext instance that is optimized for Trinidad PPR.
   * @param context 
   * @return a PartialViewContext instance
   */
  public PartialViewContext getPartialViewContext(FacesContext context)
  {
    return new PartialViewContextImpl(context);
  }
  
  private final PartialViewContextFactory _factory;
  
}
