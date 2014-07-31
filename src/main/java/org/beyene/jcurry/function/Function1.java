package org.beyene.jcurry.function;

import java.lang.reflect.Method;
import java.util.Collection;

public final class Function1<T, P1> extends
		AbstractFunction<P1, Function0<T>, T> {

	protected Function1(Object invoker, Method method, Class<T> returnType,
			Collection<Object> arguments) {
		super(invoker, method, returnType, arguments);
	}

	public Function1(Object invoker, Method method, Class<T> returnType) {
		super(invoker, method, returnType);
	}

	public Function1(Method method, Class<T> returnType) {
		super(null, method, returnType);
	}

	public Function0<T> p1(P1 t) {
		return apply(t);
	}

	@Override
	protected Function0<T> lof(Object invoker, Method method,
			Class<T> returnType, Collection<Object> arguments) {
		return new Function0<>(invoker, method, returnType, arguments);
	}
}