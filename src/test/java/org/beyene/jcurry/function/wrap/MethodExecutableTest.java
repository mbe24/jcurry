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
package org.beyene.jcurry.function.wrap;

import java.lang.reflect.Method;

import org.junit.Assert;

import org.beyene.jcurry.Outline;
import org.beyene.jcurry.function.wrap.exception.CommonExecutableException;
import org.beyene.jcurry.function.wrap.exception.NoException;
import org.junit.BeforeClass;
import org.junit.Test;

public class MethodExecutableTest {

	private static MethodExecutable<Integer, NoException> meAdd;
	private static MethodExecutable<Integer, NumberFormatException> meParseInt;

	@BeforeClass
	public static void setUp() throws Exception {
		Outline<TestClass> ol = new Outline<>(TestClass.class);

		Method add = ol.search("add");
		Method parse = ol.search("parse");

		meAdd = new MethodExecutable<>(null, add, Integer.class, NoException.class);
		meParseInt = new MethodExecutable<>(new TestClass(), parse,	Integer.class, NumberFormatException.class);

	}

	@Test
	public void testCall() throws Exception {
		Integer result = meAdd.call(9, 10);
		Assert.assertEquals(19, (int) result);

		result = meParseInt.call("55");
		Assert.assertEquals(55, (int) result);

	}

	@Test(expected = CommonExecutableException.class)
	public void testCallFail() throws Exception {
		meParseInt.call("55-fail");
		Assert.fail();
	}
	
	@Test
	public void testGetExceptionType() throws Exception {
		Assert.assertTrue(meParseInt.getExceptionType().equals(NumberFormatException.class));
	}
}