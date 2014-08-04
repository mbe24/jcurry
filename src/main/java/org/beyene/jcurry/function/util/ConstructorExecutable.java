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

import org.beyene.jcurry.function.util.exception.CommonExecutableException;

class ConstructorExecutable<T, E extends Exception> implements CommonExecutable<T, E> {

	private final Constructor<T> constructor;
	private final Class<E> exceptionType;
	
	public ConstructorExecutable(Constructor<T> constructor, Class<E> exceptionType) {
		this.constructor = constructor;
		this.constructor.setAccessible(true);
		
		this.exceptionType = exceptionType;
	}

	@Override
	public T call(Object... args) throws E {
		try {
			return constructor.newInstance(args);
		} catch (ReflectiveOperationException e) {
			throw new CommonExecutableException(e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			throw new CommonExecutableException(e.getMessage(), e);
		}
	}
	
	@Override
	public String toString() {
		return constructor.getDeclaringClass().getName() + " constructor";
	}

	@Override
	public Class<E> getExceptionType() {
		return exceptionType;
	}
}