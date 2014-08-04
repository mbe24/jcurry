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

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.beyene.jcurry.function.util.exception.NoException;

public final class ConcreteExecutable<T, E extends Exception> implements
		CommonExecutable<T, E> {

	private final CommonExecutable<T, E> ce;

	public ConcreteExecutable(Object invoker, Method method,
			Class<? extends T> returnType, Class<E> exceptionType) {
		this.ce = new MethodExecutable<>(invoker, method, returnType,
				exceptionType);
	}

	public ConcreteExecutable(Constructor<T> c, Class<E> exceptionType) {
		this.ce = new ConstructorExecutable<>(c, exceptionType);
	}


	public static <R> CommonExecutable<R, NoException> get(
			Object invoker, Method m, Class<? extends R> returnType) {
		return get(invoker, m, returnType, NoException.class);
	}
	
	public static <R, E extends Exception> CommonExecutable<R, E> get(
			Object invoker, Method m, Class<? extends R> returnType,
			Class<E> exceptionType) {
		return new ConcreteExecutable<R, E>(invoker, m, returnType,
				exceptionType);
	}

	public static <R> CommonExecutable<R, NoException> get(
			Constructor<R> c) {
		return get(c, NoException.class);
	}

	public static <R, E extends Exception> CommonExecutable<R, E> get(Constructor<R> c,
			Class<E> exceptionType) {
		return new ConcreteExecutable<R, E>(c, exceptionType);
	}

	@Override
	public T call(Object... args) throws E {
		return ce.call(args);
	}
	
	@Override
	public String toString() {
		return ce.toString();
	}

	@Override
	public Class<E> getExceptionType() {
		return ce.getExceptionType();
	}
}