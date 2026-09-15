package com.junit.without.maven;

import static org.junit.Assert.*;

import org.junit.Test;

public class ReverseStringTest {

	@Test
	public void test() {
		ReverseString rs = new ReverseString();
		assertEquals("htraP", rs.reverseString("Parth"));
	}

}
