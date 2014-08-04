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

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.function.Function;

import org.beyene.jcurry.function.util.CommonExecutable;

/**
 * 
 * @author mbeyene
 *
 * @param <P>
 *            parameter type
 * @param <LOF>
 *            lower-order function
 * @param <R>
 *            return type
 * @param <E>
 *            type of exception thrown by call method
 */
abstract class AbstractFunction<P, LOF, R, E extends Exception> implements
		Function<P, LOF> {

	protected final CommonExecutable<R, E> executable;
	protected final Collection<Object> args;

	protected AbstractFunction(CommonExecutable<R, E> executable,
			Collection<Object> args) {
		this.executable = executable;
		this.args = Collections.unmodifiableCollection(args);
	}

	public AbstractFunction(CommonExecutable<R, E> executable) {
		this(executable, new ArrayDeque<>());
	}

	@Override
	public LOF apply(P t) {
		Deque<Object> copy = new ArrayDeque<>(args);
		copy.offerFirst(t);
		return lof(executable, copy);
	}

	protected abstract LOF lof(CommonExecutable<R, E> executable,
			Collection<Object> arguments);
}