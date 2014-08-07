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

import java.lang.reflect.Constructor;

import junit.framework.Assert;

import org.beyene.jcurry.Outline;
import org.beyene.jcurry.function.wrap.exception.NoException;
import org.junit.BeforeClass;
import org.junit.Test;

public class ConstructorExecutableTest {

	private static ConstructorExecutable<TestMessage, NoException> ceCtor;

	private static String message = "ConcreteMessage";
	private static String author = "Human";

	@BeforeClass
	public static void setUp() throws Exception {
		Outline<TestMessage> ol = new Outline<>(TestMessage.class);

		Constructor<TestMessage> ctor = ol.constructorMap().get(2).iterator().next();
		ceCtor = new ConstructorExecutable<>(ctor, NoException.class);
	}

	@Test
	public void testCall() throws Exception {
		TestMessage object = ceCtor.call(message, author);
		Assert.assertEquals(message, object.getMessage());
		Assert.assertEquals(author, object.getAuthor());
	}

	@Test
	public void testGetExceptionType() throws Exception {
		Assert.assertTrue(ceCtor.getExceptionType().equals(NoException.class));
	}
}