package org.beyene.jcurry.function;

import java.lang.reflect.Method;
import java.util.Collection;

public class Function5<T, P1, P2, P3, P4, P5> extends
		AbstractFunction<P5, Function4<T, P1, P2, P3, P4>, T> {

	protected Function5(Object invoker, Method method, Class<T> returnType,
			Collection<Object> args) {
		super(invoker, method, returnType, args);
	}

	public Function5(Object invoker, Method method, Class<T> returnType) {
		super(invoker, method, returnType);
	}

	public Function5(Method method, Class<T> returnType) {
		super(null, method, returnType);
	}

	public Function4<T, P1, P2, P3, P4> p5(P5 t) {
		return apply(t);
	}

	@Override
	protected Function4<T, P1, P2, P3, P4> lof(Object invoker, Method method,
			Class<T> returnType, Collection<Object> arguments) {
		return new Function4<>(invoker, method, returnType, arguments);
	}

}