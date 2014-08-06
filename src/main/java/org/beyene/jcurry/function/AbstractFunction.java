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

import java.util.Arrays;
import java.util.function.Function;

import org.beyene.jcurry.function.wrap.CommonExecutable;

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
	protected final Object[] args;

	protected AbstractFunction(CommonExecutable<R, E> executable, Object[] args) {
		this.executable = executable;
		this.args = args;
	}

	public AbstractFunction(CommonExecutable<R, E> executable, int argc) {
		this(executable, new Object[argc]);
	}

	@Override
	public LOF apply(P t) {
		Object[] copy = Arrays.copyOf(args, args.length);
		copy[argPos()] = t;
		return lof(executable, copy);
	}

	protected abstract LOF lof(CommonExecutable<R, E> executable, Object[] args);

	protected abstract int argPos();

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (args.length < 1)
			return executable.toString();

		sb.append(executable.toString() + " ");
		sb.append("(");
		sb.append(convert(args[0]));
		if (args.length > 1) {
			for (int i = 1; i < this.args.length; i++)
				sb.append(", " + convert(args[i]));
		}
		sb.append(")");
		return sb.toString();
	}

	private String convert(Object o) {
		if (o == null)
			return "__";
		else
			return o.toString();
	}
}