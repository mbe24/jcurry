package org.beyene.jcurry.function.wrap;

import java.lang.reflect.Method;

import org.junit.Assert;

import org.beyene.jcurry.Outline;
import org.junit.BeforeClass;
import org.junit.Test;

public class ConcreteExecutableTest {

	private static ConcreteExecutable<Integer, NumberFormatException> meParseInt;

	@BeforeClass
	public static void setUp() throws Exception {
		Outline<TestClass> ol = new Outline<>(TestClass.class);

		Method parse = ol.search("parse");

		meParseInt = new ConcreteExecutable<>(new TestClass(), parse,	Integer.class, NumberFormatException.class);

	}
	
	@Test(expected = NumberFormatException.class)
	public void testCall() throws Exception {
		meParseInt.call("55-fail");
		Assert.fail();
	}
}