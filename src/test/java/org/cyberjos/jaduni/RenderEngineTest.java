/*
 * RenderEngineTest.java 2014/5/4
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.Reader;
import java.io.Writer;

import org.junit.Test;

/**
 * Test class for {@link RenderEngine}.
 *
 * @author Joseph S. Kuo
 * @since 0.0.1, 2014/5/4
 */
public class RenderEngineTest {
    /**
     * Test case for {@link RenderEngine#getDefault()}.
     */
    @Test
    public void testGetDefault() {
        final RenderEngine defaultRenderEngine = RenderEngine.getDefault();

        assertNotNull("The default render engine must not be null.", defaultRenderEngine);
    }

    /**
     * Test case for {@link RenderEngine#get(String)}.
     */
    @Test(expected = NullPointerException.class)
    public void testGetWithNullName() {
        RenderEngine.get(null);
    }

    /**
     * Test case for {@link RenderEngine#get(String)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetWithEmptyName() {
        RenderEngine.get("");
    }

    /**
     * Test case for {@link RenderEngine#get(String)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetWithBlankName() {
        RenderEngine.get("   ");
    }

    /**
     * Test case for {@link RenderEngine#get(String)}.
     */
    @Test
    public void testGetUnregisteredRenderEngine() {
        final RenderEngine renderEngine = RenderEngine.get("UnregisteredRenderEngine");

        assertNull("The returned render engine must be null", renderEngine);
    }

    /**
     * Test case for {@link RenderEngine#set(String, RenderEngine)}.
     */
    @Test(expected = NullPointerException.class)
    public void testSetWithNullName() {
        RenderEngine.set(null, null);
    }

    /**
     * Test case for {@link RenderEngine#set(String, RenderEngine)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetWithEmptyName() {
        RenderEngine.set("", null);
    }

    /**
     * Test case for {@link RenderEngine#set(String, RenderEngine)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetWithBlankName() {
        RenderEngine.set("   ", null);
    }

    /**
     * Test case for {@link RenderEngine#set(String, RenderEngine)}.
     */
    @Test(expected = NullPointerException.class)
    public void testSetDefaultWithNull() {
        RenderEngine.set("DefaultRenderEngine-RenderEngineImpl.class", null);
    }

    /**
     * Test case for {@link RenderEngine#set(String, RenderEngine)}.
     */
    @Test
    public void testSet() {
        final RenderEngine mockRenderEngine = new RenderEngine() {
            @Override
            public String render(final String source) throws RenderException {
                return null;
            }

            @Override
            public void render(final Reader in, final Writer out) throws RenderException {
                // empty
            }
        };

        RenderEngine.set("mockRenderEngine", mockRenderEngine);
        final RenderEngine returnedRenderEngine = RenderEngine.get("mockRenderEngine");
        assertNotNull("The return object must not bu null.", returnedRenderEngine);
        assertEquals("These two objects should be equal.", mockRenderEngine, returnedRenderEngine);
    }
}
