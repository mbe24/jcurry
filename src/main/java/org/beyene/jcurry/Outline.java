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
package org.beyene.jcurry;

import java.lang.reflect.Constructor;
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

	private static final Outline<Object> oo = new Outline<>(Object.class);

	private final Class<? extends T> clazz;

	private final Collection<Method> methods;
	private final Map<String, List<Method>> index;
	private Map<String, List<Method>> indexFiltered;

	private final Collection<Constructor<T>> constructors;
	private final Map<Integer, List<Constructor<T>>> ctorIndex;

	public Outline(Class<? extends T> clazz) {
		this.clazz = clazz;
		this.methods = Arrays.asList(clazz.getMethods());

		final Map<String, List<Method>> map = new HashMap<>();
		this.methods.stream().forEach(
				method -> map.computeIfAbsent(method.getName(),
						name -> new ArrayList<>())
						.add(method));

		this.index = Collections.unmodifiableMap(map);

		Collection<Constructor<T>> ctors = new ArrayList<>();
		for (Constructor<?> ctor : clazz.getConstructors()) {
			@SuppressWarnings("unchecked")
			Constructor<T> typedCtor = (Constructor<T>) ctor;
			ctors.add(typedCtor);
		}
		this.constructors = Collections.unmodifiableCollection(ctors);

		final Map<Integer, List<Constructor<T>>> tmp = new HashMap<>();
		this.constructors.stream().forEach(
				ctor -> tmp.computeIfAbsent(ctor.getParameterCount(),
						pc -> new ArrayList<>()).add(ctor));
		this.ctorIndex = Collections.unmodifiableMap(tmp);
	}

	public Map<Integer, List<Constructor<T>>> constructorMap() {
		return ctorIndex;
	}

	public Map<String, List<Method>> methodMap() {
		return index;
	}

	public Map<String, List<Method>> methodMapFiltered() {
		if (indexFiltered != null)
			return indexFiltered;

		Map<String, List<Method>> map = new HashMap<>(index);
		for (String method : oo.methodMap().keySet())
			map.remove(method);
		this.indexFiltered = map;

		return indexFiltered;
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