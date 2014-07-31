package org.beyene.jcurry;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class Outline<T> implements Iterable<Method> {

	private final Class<? extends T> clazz;

	private final Collection<Method> methods;
	private final Map<String, List<Method>> index;

	public Outline(Class<? extends T> clazz) {
		this.clazz = clazz;
		this.methods = Arrays.asList(clazz.getMethods());

		Map<String, List<Method>> map = new HashMap<>();
		this.methods.stream().forEach(
				method -> map.computeIfAbsent(method.getName(),
						k -> new ArrayList<>()).add(method));

		this.index = Collections.unmodifiableMap(map);
	}

	public Map<String, List<Method>> methodMap() {
		return index;
	}

	public boolean hasMethod(String name) {
		return !index.get(name).isEmpty();
	}

	public Method getMethodUnique(String name) {
		return index.get(name).get(0);
	}

	private Method regExSearch(String name) {
		for (Method m : methods)
			if (m.getName().toLowerCase()
					.matches(String.format(".*%s.*", name.toLowerCase())))
				return m;
		return null;
	}

	public boolean hasLike(String name) {
		Method m = regExSearch(name);
		if (m != null)
			return true;
		return false;
	}

	public Method search(String name) throws NoSuchMethodException {
		Method m = regExSearch(name);
		if (m != null)
			return m;

		throw new NoSuchMethodException(String.format(
				"There is no method in class %s matching '%s'!",
				clazz.getCanonicalName(), name));
	}

	@Override
	public Iterator<Method> iterator() {
		return methods.iterator();
	}
}