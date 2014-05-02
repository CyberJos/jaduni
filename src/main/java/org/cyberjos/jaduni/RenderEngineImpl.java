/*
 * RenderEngineImpl.java 2014/5/2
 *
 * Copyright (c) 2014 Joseph S. Kuo
 * All Rights Reserved.
 *
 * --LICENSE NOTICE--
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * --LICENSE NOTICE--
 */
package org.cyberjos.jaduni;

import java.io.Reader;
import java.io.Writer;

/**
 * An implementation of {@link RenderEngine}. It is designed to handle Creole
 * wiki syntax.
 *
 * @author Joseph S. Kuo
 * @since 0.0.1, 2014/5/2
 */
class RenderEngineImpl implements RenderEngine {
    // TODO: implement this class
    /**
     *
     */
    public RenderEngineImpl() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String render(final String source) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Reader in, final Writer out) {
        // empty
    }
}
