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

import java.lang.reflect.Method;
import java.util.Collection;

public final class Function1<T, P1> extends
		AbstractFunction<P1, Function0<T>, T> {

	protected Function1(Object invoker, Method method, Class<T> returnType,
			Collection<Object> arguments) {
		super(invoker, method, returnType, arguments);
	}

	public Function1(Object invoker, Method method, Class<T> returnType) {
		super(invoker, method, returnType);
	}

	public Function1(Method method, Class<T> returnType) {
		super(null, method, returnType);
	}

	public Function0<T> p1(P1 t) {
		return apply(t);
	}

	@Override
	protected Function0<T> lof(Object invoker, Method method,
			Class<T> returnType, Collection<Object> arguments) {
		return new Function0<>(invoker, method, returnType, arguments);
	}
}