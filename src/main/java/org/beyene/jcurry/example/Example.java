package org.beyene.jcurry.example;

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

public class Example {

	public static void main(String[] args) throws NoSuchMethodException,
			SecurityException, ClassNotFoundException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, InstantiationException {
		Car car = new Car();
		Class<? extends Car> clazz = car.getClass();

		Outline<Car> co = new Outline<Car>(clazz);

		for (Entry<String, List<Method>> entry : co.methodMapFiltered().entrySet())
			System.out.printf("name=%s, count=%s, method(s)=%s%n", entry
					.getKey(), entry.getValue().size(), entry.getValue()
					.toString());

		Method byRegEx = co.search("tir");
		System.out.printf("Method matched: name=%s%n", byRegEx.getName());

		Function4<String, Integer, Integer, Integer, Integer> f4 = new Function4<>(
				car, byRegEx, String.class);
		Function1<String, Integer> f1 = f4.p4(5).p3(5).p2(5);
		Function0<String> f0 = f1.p1(5);

		String resF0 = f0.call();
		System.out.printf("Function evaluates to '%s'.%n", resF0);

		Function<Integer, Integer> h = (Integer a) -> (a + 5);
		@SuppressWarnings("unchecked")
		Outline<Function<Integer, Integer>> fo = new Outline<>(
				(Class<? extends Function<Integer, Integer>>) h.getClass());
		Method apply = fo.search("app");
		System.out.printf("Method matched: name=%s%n", apply.getName());

		Function1<Integer, Integer> h1 = new Function1<>(h, apply,
				Integer.class);
		Function0<Integer> h0 = h1.p1(10);

		Integer resH0 = h0.call();
		System.out.printf("Function evaluates to '%d'.%n", resH0);
		
		for (Entry<Integer, List<Constructor<Car>>> entry : co.constructorMap().entrySet())
			System.out.printf("#parameter=%d, count=%s, method(s)=%s%n", entry
					.getKey(), entry.getValue().size(), entry.getValue()
					.toString());
		
		Constructor<Car> ctor = co.constructorMap().get(2).iterator().next();
		Car ccar = ctor.newInstance("carname", Calendar.getInstance().getTime());
		System.out.println(ccar);
		Function2<Car, String, Date> ctorF2 = new Function2<Car, String, Date>(ctor, clazz);
		Function1<Car, String> ctorF1 = ctorF2.p2(Calendar.getInstance().getTime());
		Car jcbc = ctorF1.p1("jcurry-builder-pattern-car").call();
		System.out.println(jcbc);
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

		public Date getCreatedAt() {
			return (Date) createdAt.clone();
		}

		@Override
		public String toString() {
			return "Car [name=" + name + ", createdAt=" + createdAt + "]";
		}
	}
}