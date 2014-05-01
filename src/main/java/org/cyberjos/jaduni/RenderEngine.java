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

/**
 * {@code RenderEngine} is an interface which is responsible for converting the
 * given input source to another text with the specified format. The input
 * source can be a string, a {@link Renderable} object or a {@code Reader}
 * object. Whatever the input source is, it is supposed to be able to provide
 * text with the particular syntax which can be handled by this render engine. A
 * render engine parses the given input, converts it by following predefined
 * rules of conversion, and then output to the specified destination. The output
 * can be a string, or a {@code Writer} object for output.
 *
 * @author Joseph S. Kuo
 * @since 0.0.1, 2014/4/30
 */
public interface RenderEngine {
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
