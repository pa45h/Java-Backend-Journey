package com.parth.katariya;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalcTest {

	@Test
	public void test() {
		
		Calc c = new Calc();
		
		assertEquals(4, c.add(2, 2));
		
	}

}
