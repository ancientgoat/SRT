package com.premierinc.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.StringWriter;

/**
 * Simple helper singleton class around Bean-to-Json.
 */
public class JsonHelper {

	private JsonHelper() {
	}

	/**
	 * @param obj Object to convert into Json
	 * @return String  Convert a Bean to a Json String.
	 */
	public static final String beanToJsonString(final Object obj) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			StringWriter stringWriter = new StringWriter();
			mapper.writeValue(stringWriter, obj);
			return stringWriter.toString();
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}
}

