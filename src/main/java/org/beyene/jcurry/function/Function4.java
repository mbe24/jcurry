package org.beyene.jcurry.function;

import java.lang.reflect.Method;
import java.util.Collection;

public final class Function4<T, P1, P2, P3, P4> extends
		AbstractFunction<P4, Function3<T, P1, P2, P3>, T> {

	protected Function4(Object invoker, Method method, Class<T> returnType,
			Collection<Object> arguments) {
		super(invoker, method, returnType, arguments);
	}

	public Function4(Object invoker, Method method, Class<T> returnType) {
		super(invoker, method, returnType);
	}

	public Function4(Method method, Class<T> returnType) {
		super(null, method, returnType);
	}

	public Function3<T, P1, P2, P3> p4(P4 t) {
		return apply(t);
	}

	@Override
	protected Function3<T, P1, P2, P3> lof(Object invoker, Method method,
			Class<T> returnType, Collection<Object> arguments) {
		return new Function3<>(invoker, method, returnType, arguments);
	}
}