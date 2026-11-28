/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.parent.it;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junitpioneer.jupiter.DefaultLocale;
import org.junitpioneer.jupiter.SetSystemProperty;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Exercises each test dependency managed by the parent POM, on the JDK running the build.
 * <p>
 * A managed version that requires a newer Java version than the JDK running the build fails to compile or run here.
 * </p>
 */
@ExtendWith(MockitoExtension.class)
class ManagedDependenciesTest {

    /**
     * A concrete, non-final, non-serializable class, the hardest case for mock creation.
     */
    static class Greeter {

        String greet(final String name) {
            return "Hello " + name;
        }
    }

    @Mock
    private Greeter greeter;

    /** JUnit Pioneer. */
    @Test
    @DefaultLocale(language = "fr", country = "CA")
    @SetSystemProperty(key = "commons.parent.it", value = "pioneer")
    void testJUnitPioneer() {
        assertEquals(Locale.CANADA_FRENCH, Locale.getDefault());
        assertEquals("pioneer", System.getProperty("commons.parent.it"));
    }

    /** JUnit Jupiter params. */
    @ParameterizedTest
    @ValueSource(strings = { "a", "bb", "ccc" })
    void testJUnitJupiterParams(final String value) {
        assertEquals(value.length(), value.chars().filter(c -> c == value.charAt(0)).count());
    }

    /** Mockito core and Mockito JUnit Jupiter. */
    @Test
    void testMockito() {
        when(greeter.greet("Commons")).thenReturn("Bonjour Commons");
        assertEquals("Bonjour Commons", greeter.greet("Commons"));
        verify(greeter).greet("Commons");
    }
}
