package org.beyene.jcurry;

public final class Methods {

	private Methods() {
		throw new AssertionError();
	}

	public static <T> Outline<T> get(Class<T> clazz) {
		return new Outline<T>(clazz);
	}
}