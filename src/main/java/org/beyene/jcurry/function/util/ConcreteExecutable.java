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
import java.lang.reflect.Executable;
import java.lang.reflect.Method;

public final class ConcreteExecutable<T> implements CommonExecutable<T> {

	private final CommonExecutable<T> ce;
	
	public ConcreteExecutable(Object invoker, Method method, Class<? extends T> returnType) {
		this.ce = new MethodExecutable<>(invoker, method, returnType);
	}

	public ConcreteExecutable(Constructor<T> c) {
		this.ce = new ConstructorExecutable<>(c);
	}
	
	@SuppressWarnings("unchecked")
	public static <R> ConcreteExecutable<R> get(Object invoker, Executable e, Class<? extends R> returnType) {
		if (e instanceof Method)
			return new ConcreteExecutable<R>(invoker, (Method) e, returnType);
		else if (e instanceof Constructor)
			return new ConcreteExecutable<R>((Constructor<R>) e);
		return null;
	}
	
	@Override
	public T call(Object... args) {
		return ce.call(args);
	}
}