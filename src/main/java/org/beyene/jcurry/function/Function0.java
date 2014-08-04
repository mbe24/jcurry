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

import org.beyene.jcurry.function.util.CommonExecutable;

public final class Function0<T, E extends Exception> extends AbstractFunction<Void, T, T, E> {

	protected Function0(CommonExecutable<T, E> executable,
			Collection<Object> args) {
		super(executable, args);
	}

	public Function0(CommonExecutable<T, E> executable) {
		super(executable, new ArrayDeque<>());
	}

	@Override
	public T apply(Void t) {
		try {
			return executable.call(args.toArray());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public T call() throws E {
		return executable.call(args.toArray());
	}

	@Override
	protected T lof(CommonExecutable<T, E> executable,
			Collection<Object> arguments) {
		return null;
	}
}