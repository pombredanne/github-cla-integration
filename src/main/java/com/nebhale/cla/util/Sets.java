/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nebhale.cla.util;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Utilities for dealing with {@link Set}s
 */
public final class Sets {

    /**
     * Creates a {@link Set} containing specified items
     * 
     * @param items The items to put in the created set
     * @return The created {@link Set}
     */
    @SafeVarargs
    public static <T> Set<T> asSet(T... items) {
        Set<T> set = new HashSet<>();

        for (T item : items) {
            set.add(item);
        }

        return set;
    }

    /**
     * Creates a {@link SortedSet} containing specified items
     * 
     * @param items The items to put in the created set
     * @return The created {@link SortedSet}
     */
    @SafeVarargs
    public static <T> SortedSet<T> asSortedSet(T... items) {
        SortedSet<T> set = new TreeSet<>();

        for (T item : items) {
            set.add(item);
        }

        return set;
    }
}