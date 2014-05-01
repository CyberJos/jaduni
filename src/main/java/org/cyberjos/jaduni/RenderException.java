/*
 * RenderException.java 2014/5/2
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

/**
 * {@code RenderException} is the class that are thrown during a
 * {@link RenderEngine} converts text with specified format. These exceptions
 * may include errors occurred while parsing, converting and rendering.
 *
 * @author Joseph S. Kuo
 * @since 0.0.1, 2014/5/2
 */
public class RenderException extends RuntimeException {
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1142288415769991017L;

    /**
     * Constructs a new exception with the specified message.
     *
     * @param message the exception message
     */
    public RenderException(final String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified message and root cause.
     *
     * @param message the exception message
     * @param cause the root cause
     */
    public RenderException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
