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

import java.lang.reflect.Method;

import org.beyene.jcurry.function.util.exception.CommonExecutableException;
import org.beyene.jcurry.function.util.exception.ReturnTypeException;

class MethodExecutable<T, E extends Exception> implements CommonExecutable<T, E> {

	private final Object invoker;
	private final Method method;
	private final Class<? extends T> returnType;
	private final Class<E> exceptionType;

	public MethodExecutable(Object invoker, Method method, Class<? extends T> returnType, Class<E> exceptionType) {
		this.invoker = invoker;
		this.method = method;
		this.method.setAccessible(true);
		this.returnType = returnType;
		
		Class<?> rt = method.getReturnType();
		if (!rt.isAssignableFrom(returnType))
			throw new ReturnTypeException(rt, returnType);
		
		this.exceptionType = exceptionType;
	}

	@Override
	public T call(Object... args) throws E {
		try {
			return returnType.cast(method.invoke(invoker, args));
		} catch (ReflectiveOperationException e) {
			throw new CommonExecutableException(e.getMessage(), e);
		} catch (ClassCastException e) {
			throw new CommonExecutableException(e.getMessage(), e);
		}
	}
	
	@Override
	public String toString() {
		return method.getName();
	}

	@Override
	public Class<E> getExceptionType() {
		return exceptionType;
	}
}