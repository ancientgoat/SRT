package com.premierinc.util;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 */
public class TestExceptionHelper {

	@Test
	public void testExceptionhelper() {
		try {
			throw new IllegalArgumentException("Ouch");
		} catch (Exception e) {
			String error = ExceptionHelper.toString(e);
			if (null == error || 10 >= error.length()) {
				Assert.fail("Exception string needs to be longer than 10 chars.");
			}
		}
	}
}
