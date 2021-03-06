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

import org.beyene.jcurry.function.wrap.CommonExecutable;

public final class Function0<T, E extends Exception> extends
		AbstractFunction<Void, T, T, E> {

	protected Function0(CommonExecutable<T, E> executable, Object[] args) {
		super(executable, args);
	}

	public Function0(CommonExecutable<T, E> executable) {
		super(executable, 0);
	}

	@Override
	public T apply(Void t) {
		try {
			return executable.call(args);
		} catch (Exception e) {
			return null;
		}
	}

	public T call() throws E {
		return executable.call(args);
	}

	@Override
	protected T lof(CommonExecutable<T, E> function, Object[] parameters) {
		return null;
	}

	@Override
	protected int argPos() {
		return -1;
	}
}