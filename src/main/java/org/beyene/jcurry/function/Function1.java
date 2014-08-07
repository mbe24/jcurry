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

public final class Function1<T, E extends Exception, P1> extends
		AbstractFunction<P1, Function0<T, E>, T, E> {

	protected Function1(CommonExecutable<T, E> executable,
			Object[] args) {
		super(executable, args);
	}

	public Function1(CommonExecutable<T, E> executable) {
		super(executable, 1);
	}

	public Function0<T, E> p1(P1 t) {
		return apply(t);
	}

	@Override
	protected Function0<T, E> lof(CommonExecutable<T, E> function, Object[] parameters) {
    return new Function0<>(function, parameters);
	}

	@Override
	protected int argPos() {
		return 0;
	}
}