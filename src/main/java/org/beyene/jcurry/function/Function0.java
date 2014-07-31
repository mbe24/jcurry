package org.beyene.jcurry.function;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

public final class Function0<T> extends AbstractFunction<Void, T, T> {

	protected Function0(Object invoker, Method method, Class<T> returnType,
			Collection<Object> arguments) {
		super(invoker, method, returnType, arguments);
	}

	public Function0(Object invoker, Method method, Class<T> returnType) {
		super(invoker, method, returnType);
	}

	public Function0(Method method, Class<T> returnType) {
		super(null, method, returnType);
	}

	@Override
	public T apply(Void t) {
		// MethodAccessor ma = null;
		try {
			return returnType.cast(method.invoke(invoker, args.toArray()));
		} catch (IllegalAccessException | InvocationTargetException
				| IllegalArgumentException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public T call() {
		return apply(null);
	}

	@Override
	protected T lof(Object invoker, Method method, Class<T> returnType,
			Collection<Object> arguments) {
		return null;
	}
}