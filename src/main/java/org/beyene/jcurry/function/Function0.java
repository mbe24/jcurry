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
package org.beyene.jcurry.function;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

public final class Function0<T> extends AbstractFunction<Void, T, T> {

	protected Function0(Object invoker, Method method, Class<T> returnType,
			Collection<Object> arguments) {
		super(invoker, method, returnType, arguments);
	}

	public Function0(Object invoker, Method method, Class<T> returnType) {
		super(invoker, method, returnType);
	}

	public Function0(Method method, Class<T> returnType) {
		super(null, method, returnType);
	}

	@Override
	public T apply(Void t) {
		// MethodAccessor ma = null;
		try {
			return returnType.cast(method.invoke(invoker, args.toArray()));
		} catch (IllegalAccessException | InvocationTargetException
				| IllegalArgumentException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public T call() {
		return apply(null);
	}

	@Override
	protected T lof(Object invoker, Method method, Class<T> returnType,
			Collection<Object> arguments) {
		return null;
	}
}