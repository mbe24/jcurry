package org.beyene.jcurry.function;

import java.lang.reflect.Method;
import java.util.Collection;

public final class Function2<T, P1, P2> extends
		AbstractFunction<P2, Function1<T, P1>, T> {

	protected Function2(Object invoker, Method method, Class<T> returnType,
			Collection<Object> arguments) {
		super(invoker, method, returnType, arguments);
	}

	public Function2(Object invoker, Method method, Class<T> returnType) {
		super(invoker, method, returnType);
	}

	public Function2(Method method, Class<T> returnType) {
		super(null, method, returnType);
	}

	public Function1<T, P1> p2(P2 t) {
		return apply(t);
	}

	@Override
	protected Function1<T, P1> lof(Object invoker, Method method,
			Class<T> returnType, Collection<Object> arguments) {
		return new Function1<>(invoker, method, returnType, arguments);
	}
}