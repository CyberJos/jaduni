/*
 * RenderEngine.java 2014/4/30
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
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.Validate;

/**
 * {@code RenderEngine} is an interface which is responsible for converting the
 * given input source to another text with the specified format. The input
 * source can be a string, a {@link Renderable} object or a {@code Reader}
 * object. Whatever the input source is, it is supposed to be able to provide
 * text with the particular syntax which can be handled by this render engine. A
 * render engine parses the given input, converts it by following predefined
 * rules of conversion, and then output to the specified destination. The output
 * can be a string, or a {@code Writer} object for output.
 * <p />
 * This interface contains a singleton enumeration and uses it to simply manage
 * all render engine. However, a developer only needs to deal with this
 * interface without accessing the enumeration directly:
 *
 * <pre>
 * // Retrieve the default render engine which is designed for Creole
 * RenderEngine defaultRenderEngine = RenderEngine.getDefault();
 * 
 * // Add or retrieve a new render engine
 * RenderEngine.set(&quot;MyRenderEngine&quot;, new MyRenderEngine());
 * RenderEngine myRenderEngine = RenderEngine.get(&quot;MyRenderEngine&quot;);
 * 
 * // Remove a render engine. It cannot remove the default render engine.
 * RenderEngine.set(&quot;MyRenderEngine&quot;, null);
 * </pre>
 *
 * @author Joseph S. Kuo
 * @since 0.0.1, 2014/4/30
 */
public interface RenderEngine {
    /**
     * The {@code RenderEngine} holder is a singleton enumeration for easily
     * managing all render engines in a JVM. It creates and registers the
     * default render engine by default while initialization. A user doesn't
     * need to use this enumeration to manage render engines. Instead, he should
     * use those static methods in {@link RenderEngine} to get or set a render
     * engine object associated with the specified name.
     *
     * @author Joseph S. Kuo
     * @since 0.0.1, 2014/5/2
     */
    public enum Holder {
        /**
         * Singleton instance.
         */
        INSTANCE;

        /**
         * Constant for default render engine.
         */
        private static final String DEFAULT_RENDER_ENGINE = "DefaultRenderEngine-RenderEngineImpl.class";

        /**
         * The map to store render engines.
         */
        final Map<String, RenderEngine> renderEngineMapping = new ConcurrentHashMap<>();

        /**
         * Constructs without any arguments. It creates the default render
         * engine and put it into mapping while initialization.
         */
        private Holder() {
            this.renderEngineMapping.put(DEFAULT_RENDER_ENGINE, new RenderEngineImpl());
        }
    }

    /**
     * Returns the default render engine. It never returns a {@code null}
     * object.
     *
     * @return the default render engine which is never {@code null}
     */
    public static RenderEngine getDefault() {
        return Holder.INSTANCE.renderEngineMapping.get(Holder.DEFAULT_RENDER_ENGINE);
    }

    /**
     * Returns the render engine associated with the given name, or {@code null}
     * if not found.
     *
     * @param renderEngineName the name of the render engine
     * @return the render engine associated with the given name, or {@code null}
     *         if not found
     * @throws NullPointerException if the given name is {@code null}
     * @throws IllegalArgumentException if the given name is blank
     */
    public static RenderEngine get(final String renderEngineName) {
        Validate.notBlank(renderEngineName, "The given name must not be null.");
        return Holder.INSTANCE.renderEngineMapping.get(renderEngineName);
    }

    /**
     * Sets the render engine into the mapping with the given name. The passed
     * render engine can be {@code null}.
     *
     * @param renderEngineName the name of render engine
     * @param renderEngine the render engine. It can be {@code null}
     * @throws NullPointerException if the given name is {@code null}
     * @throws IllegalArgumentException if the given name is blank
     */
    public static void set(final String renderEngineName, final RenderEngine renderEngine) {
        Validate.notBlank(renderEngineName, "The given name must not be null.");

        if (Holder.DEFAULT_RENDER_ENGINE.equals(renderEngineName)) {
            Objects.requireNonNull(renderEngine, "Cannot set the default render engine to a null object.");
        }

        Holder.INSTANCE.renderEngineMapping.put(renderEngineName, renderEngine);
    }

    /**
     * Parses the given source and then returns the converted string. The source
     * could be a string, or a location of data source which contains text. It
     * all depends on how this render engine implements this method and how it
     * treats the passed parameter. It throws a {@link RenderException} once if
     * it encounters errors during conversion.
     *
     * @param source the source to be parsed
     * @return the converted string
     * @throws RenderException if any exception occurs while a
     *         {@link RenderEngine} parses input, converts format and renders
     *         output
     */
    public String render(String source) throws RenderException;

    /**
     * Parses the given source and then writes the converted string to the
     * specified output. The source or destination could be a string, a local
     * file, or a resource via network. The reader should contain a string which
     * can be parsed. It throws a {@link RenderException} once if it encounters
     * errors during conversion.
     *
     * @param in the {@code Reader} which can provide string that can be parsed
     * @param out the {@code Writer} which receives the converted string
     * @throws RenderException if any exception occurs while a
     *         {@link RenderEngine} parses input, converts format and renders
     *         output
     */
    public void render(Reader in, Writer out) throws RenderException;
}
