package com.junit.with.junit5;

public class ReverseString {
	public String reverseString(String str) {
		
		StringBuilder sb = new StringBuilder(str);
		String revStr = sb.reverse().toString();
		return revStr;
		
	}
	
}
