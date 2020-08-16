package com.equifax.library.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AddNumbersTest {

	@Test
	void test() {
		AddNumbers addNumbers = new AddNumbers();
		int expected=2;
		int actual =addNumbers.add(1, 1);
		assertEquals(expected, actual);
	}

}
