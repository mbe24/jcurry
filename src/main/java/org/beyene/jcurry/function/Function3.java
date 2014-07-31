package org.beyene.jcurry.function;

import java.lang.reflect.Method;
import java.util.Collection;

public final class Function3<T, P1, P2, P3> extends
		AbstractFunction<P3, Function2<T, P1, P2>, T> {

	protected Function3(Object invoker, Method method, Class<T> returnType,
			Collection<Object> arguments) {
		super(invoker, method, returnType, arguments);
	}

	public Function3(Object invoker, Method method, Class<T> returnType) {
		super(invoker, method, returnType);
	}

	public Function3(Method method, Class<T> returnType) {
		super(null, method, returnType);
	}

	public Function2<T, P1, P2> p3(P3 t) {
		return apply(t);
	}

	@Override
	protected Function2<T, P1, P2> lof(Object invoker, Method method,
			Class<T> returnType, Collection<Object> arguments) {
		return new Function2<>(invoker, method, returnType, arguments);
	}
}