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

import org.beyene.jcurry.function.wrap.exception.CommonExecutableException;

/**
 * Represents a function that can be executed as is.
 * 
 * Can be a static method that doesn't need an object to be invoked on, a
 * ordinary method or a constructor.
 * 
 * @author mbeyene
 *
 * @param <T>
 *            return type
 * @param <E>
 *            exception that is thrown by method call, if no exception is thrown
 *            specifiy any unchecked exception type e. g.
 *            {@link RuntimeException}
 */
public interface CommonExecutable<T, E extends Exception> {

	/**
	 * Calls function with given parameter list.
	 * 
	 * @param args
	 *            method parameter
	 * @return result of underlying function
	 * @throws E
	 *             exception to be thrown
	 * @throws CommonExecutableException
	 *             is thrown the common executable is misconfigured
	 */
	public T call(Object... args) throws E;

	/**
	 * Returns type of exception that may be thrown when call is invoked.
	 * 
	 * @return class of exception
	 */
	public Class<E> getExceptionType();
}