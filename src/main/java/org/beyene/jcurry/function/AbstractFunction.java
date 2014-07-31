package org.beyene.jcurry.function;

import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.function.Function;

/**
 * 
 * @author mbeyene
 *
 * @param <P>
 *            parameter type
 * @param <LOF>
 *            lower-order function
 * @param <R>
 *            return type
 */
abstract class AbstractFunction<P, LOF, R> implements Function<P, LOF> {

	protected final Object invoker;
	protected final Method method;

	protected final Class<R> returnType;

	protected final Collection<Object> args;

	protected AbstractFunction(Object invoker, Method method,
			Class<R> returnType, Collection<Object> args) {
		this.invoker = invoker;

		this.method = method;
		method.setAccessible(true);

		this.returnType = returnType;
		this.args = Collections.unmodifiableCollection(args);
	}

	protected AbstractFunction(Object invoker, Method method,
			Class<R> returnType) {
		this(invoker, method, returnType, new ArrayDeque<>());
	}

	@Override
	public LOF apply(P t) {
		Deque<Object> copy = new ArrayDeque<>(args);
		copy.offerLast(t);
		return lof(invoker, method, returnType, copy);
	}

	protected abstract LOF lof(Object invoker, Method method,
			Class<R> returnType, Collection<Object> arguments);
}