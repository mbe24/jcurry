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
package org.beyene.jcurry.function.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class MethodExecutable<T> implements CommonExecutable<T> {

	protected final Object invoker;
	protected final Method method;
	protected final Class<? extends T> returnType;

	public MethodExecutable(Object invoker, Method method, Class<? extends T> returnType) {
		this.invoker = invoker;
		this.method = method;
		this.returnType = returnType;
	}

	@Override
	public T call(Object... args) {
		try {
			return returnType.cast(method.invoke(invoker, args));
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
	}
}