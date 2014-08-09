package org.beyene.jcurry.function.wrap;

import org.junit.Assert;

import org.junit.Test;

public class PrimitivesTest {

	@Test
	public void testWrap() throws Exception {
		Class<Integer> clazzInt = int.class;
		Class<Integer> integerClazz = Primitives.wrap(clazzInt);

		Assert.assertTrue(!clazzInt.equals(integerClazz));
		Assert.assertEquals(Integer.class, integerClazz);
		Assert.assertNotSame(int.class, Integer.class);
		
		Class<Void> clazzVoid = void.class;
		Class<Void> voidClazz =  Primitives.wrap(clazzVoid);
		
		Assert.assertTrue(!clazzVoid.equals(voidClazz));
		Assert.assertEquals(Void.class, voidClazz);
		Assert.assertNotSame(void.class, Void.class);
	}
}