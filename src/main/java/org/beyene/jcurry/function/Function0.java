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

import java.lang.reflect.Executable;
import java.util.Collection;

import org.beyene.jcurry.function.util.CommonExecutable;

public final class Function0<T> extends AbstractFunction<Void, T, T> {

	protected Function0(CommonExecutable<T> executable,
			Collection<Object> args) {
		super(executable, args);
	}

	public Function0(Object invoker, Executable e, Class<T> returnType) {
		super(invoker, e, returnType);
	}

	public Function0(Executable e, Class<T> returnType) {
		super(null, e, returnType);
	}

	@Override
	public T apply(Void t) {
		return executable.call(args.toArray());
	}

	public T call() {
		return apply(null);
	}

	@Override
	protected T lof(CommonExecutable<T> executable,
			Collection<Object> arguments) {
		return null;
	}
}