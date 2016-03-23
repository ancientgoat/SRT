package com.premierinc.controller;

import com.premierinc.util.ExceptionHelper;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 */
public class TestParentChildControllerTest {

	@Test
	public void testExceptionhelper() {
		try {
			throw new IllegalArgumentException("Ouch");
		} catch (Exception e) {
			String error = ExceptionHelper.toString(e);
			if (null == error || 10 >= error.length()) {
				Assert.fail("Exception string needs to be not null and longer than 10 chars.");
			}
		}
	}
}
