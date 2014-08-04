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

public class Function5<T, E extends Exception, P1, P2, P3, P4, P5> extends
		AbstractFunction<P5, Function4<T, E, P1, P2, P3, P4>, T, E> {

	protected Function5(CommonExecutable<T, E> executable,
			Collection<Object> arguments) {
		super(executable, arguments);
	}

	public Function5(CommonExecutable<T, E> executable) {
		super(executable, new ArrayDeque<>());
	}

	public Function4<T, E, P1, P2, P3, P4> p5(P5 t) {
		return apply(t);
	}

	@Override
	protected Function4<T, E, P1, P2, P3, P4> lof(
			CommonExecutable<T, E> executable, Collection<Object> arguments) {
		return new Function4<>(executable, arguments);
	}
}