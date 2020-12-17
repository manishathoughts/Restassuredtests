package com.elsevier.usage.apitests.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CounterDataServiceUtils {

	private static Properties props;

	public static void loadProps(String environment) throws IOException {
		InputStream fis = CounterDataServiceUtils.class.getClassLoader().getResourceAsStream(String.format("%s.properties", environment));
		props = new Properties();
		props.load(fis);
	}

	public static Properties getProps() {
		return props;
	}

}
