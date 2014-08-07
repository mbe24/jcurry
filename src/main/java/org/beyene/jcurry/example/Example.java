package org.beyene.jcurry.example;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Function;

import org.beyene.jcurry.Outline;
import org.beyene.jcurry.function.Function0;
import org.beyene.jcurry.function.Function1;
import org.beyene.jcurry.function.Function2;
import org.beyene.jcurry.function.Function4;
import org.beyene.jcurry.function.wrap.CommonExecutable;
import org.beyene.jcurry.function.wrap.ConcreteExecutable;
import org.beyene.jcurry.function.wrap.exception.NoException;

public class Example {

	public static void main(String[] args) throws NoSuchMethodException,
			SecurityException, ClassNotFoundException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			InstantiationException {
		Car car = new Car();
		Class<? extends Car> clazz = car.getClass();

		Outline<Car> co = new Outline<Car>(clazz);

		for (Entry<String, List<Method>> entry : co.methodMapFiltered()
				.entrySet())
			System.out.printf("name=%s, count=%s, method(s)=%s%n", entry
					.getKey(), entry.getValue().size(), entry.getValue()
					.toString());

		Method byRegEx = co.search("tir");
		System.out.printf("Method matched: name=%s%n", byRegEx.getName());
		CommonExecutable<String, NoException> tires = ConcreteExecutable.get(
				car, byRegEx, String.class);

		Function4<String, NoException, Integer, Integer, Integer, Integer> f4 = new Function4<>(
				tires);
		Function1<String, NoException, Integer> f1 = f4.p4(5).p3(5).p2(5);
		System.out.println(f1.toString());
		Function0<String, NoException> f0 = f1.p1(5);

		String resF0 = f0.call();
		System.out.printf("Function %s evaluates to '%s'.%n", f0.toString(),
				resF0);

		Function<Integer, Integer> h = (Integer a) -> (a + 5);
		@SuppressWarnings("unchecked")
		Outline<Function<Integer, Integer>> fo = new Outline<>(
				(Class<? extends Function<Integer, Integer>>) h.getClass());
		Method apply = fo.search("app");
		System.out.printf("Method matched: name=%s%n", apply.getName());

		CommonExecutable<Integer, NoException> ceApply = ConcreteExecutable
				.get(h, apply, Integer.class);
		Function1<Integer, NoException, Integer> h1 = new Function1<>(ceApply);
		Function0<Integer, NoException> h0 = h1.p1(10);

		Integer resH0 = h0.call();
		System.out.printf("Function evaluates to '%d'.%n", resH0);

		for (Entry<Integer, List<Constructor<Car>>> entry : co.constructorMap()
				.entrySet())
			System.out.printf("#parameter=%d, count=%s, method(s)=%s%n", entry
					.getKey(), entry.getValue().size(), entry.getValue()
					.toString());

		Constructor<Car> ctor = co.constructorMap().get(2).iterator().next();
		Car ccar = ctor
				.newInstance("carname", Calendar.getInstance().getTime());
		System.out.println(ccar);

		CommonExecutable<Car, NoException> ceCtor = ConcreteExecutable
				.get(ctor);
		Function2<Car, NoException, String, Date> ctorF2 = new Function2<Car, NoException, String, Date>(
				ceCtor);
		Function1<Car, NoException, String> ctorF1 = ctorF2.p2(Calendar
				.getInstance().getTime());
		Function0<Car, NoException> ctorF0 = ctorF1
				.p1("jcurry-builder-pattern-car");
		Car jcbc = ctorF0.call();
		System.out.println(jcbc);
		System.out.println(ctorF0.toString());

		Method fill = co.search("fill");
		CommonExecutable<Boolean, IOException> ceFill = ConcreteExecutable.get(
				car, fill, boolean.class, IOException.class);
		Function1<Boolean, IOException, Integer> funcFillF1 = new Function1<Boolean, IOException, Integer>(
				ceFill);
		Function0<Boolean, IOException> call4ex = funcFillF1.p1(5);
		try {
			call4ex.call();
		} catch (IOException e) {
			System.out.println("Successfully forwarded exception. And extracted message: " + e.getMessage());
		}
	}

	static class Car {

		private final String name;

		private final Date createdAt;

		public Car() {
			this.name = "undefined";
			this.createdAt = Calendar.getInstance().getTime();
		}

		public Car(String name, Date createdAt) {
			this.name = name;
			this.createdAt = (Date) createdAt.clone();
		}

		public double drive(double kmh, double hours) {
			return kmh * hours;
		}

		public String tires(int w1, int w2, int w3, int w4) {
			return String.valueOf(w1 + w2 + w3 + w4);
		}

		public String getName() {
			return name;
		}

		public boolean fillTank(@SuppressWarnings ("unused") int liter) throws IOException {
			throw new IOException("tank is full");
		}

		public Date getCreatedAt() {
			return (Date) createdAt.clone();
		}

		@Override
		public String toString() {
			return "Car [name=" + name + ", createdAt=" + createdAt + "]";
		}
	}
}