/*
 * Copyright 2014 Mikael Beyene
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package org.beyene.jcurry.function.wrap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

final class Primitives {

	private static final Map<Class<?>, Class<?>> primitive2Boxed;
	
	static {
		Map<Class<?>, Class<?>> tmp = new HashMap<>();
		tmp.put(void.class, Void.class);
		tmp.put(boolean.class, Boolean.class);
		tmp.put(char.class, Character.class);
		tmp.put(byte.class, Byte.class);
		tmp.put(short.class, Short.class);
		tmp.put(int.class, Integer.class);
		tmp.put(long.class, Long.class);
		tmp.put(float.class, Float.class);
		tmp.put(double.class, Double.class);
		primitive2Boxed = Collections.unmodifiableMap(tmp);
	}
	
	private Primitives() {
		throw new AssertionError("Class should not be instantiated!");
	}
	
	@SuppressWarnings("unchecked")
	public static <T> Class<T> wrap(Class<T> primitive) {
		return (Class<T>) primitive2Boxed.get(primitive);
	}
}