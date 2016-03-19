package com.premierinc.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 *
 */
public class ExceptionHelper {
	private ExceptionHelper (){}

	public static final String toString(final Throwable inThrowable){
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter( stringWriter );
		inThrowable.printStackTrace( printWriter );
		printWriter.flush();
		return stringWriter.toString();
	}
}
