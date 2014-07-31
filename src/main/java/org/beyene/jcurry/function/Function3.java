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

public final class Function3<T, P1, P2, P3> extends
		AbstractFunction<P3, Function2<T, P1, P2>, T> {

	protected Function3(Object invoker, Method method, Class<T> returnType,
			Collection<Object> arguments) {
		super(invoker, method, returnType, arguments);
	}

	public Function3(Object invoker, Method method, Class<T> returnType) {
		super(invoker, method, returnType);
	}

	public Function3(Method method, Class<T> returnType) {
		super(null, method, returnType);
	}

	public Function2<T, P1, P2> p3(P3 t) {
		return apply(t);
	}

	@Override
	protected Function2<T, P1, P2> lof(Object invoker, Method method,
			Class<T> returnType, Collection<Object> arguments) {
		return new Function2<>(invoker, method, returnType, arguments);
	}
}