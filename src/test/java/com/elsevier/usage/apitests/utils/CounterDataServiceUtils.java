package com.elsevier.usage.apitests.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CounterDataServiceUtils {

	private static Properties props;

	public static void loadProps(String environment) throws IOException {
		String projectPath = System.getProperty("user.dir");

		FileInputStream fis = new FileInputStream(projectPath + String.format("/src/test/resources/%s.properties",environment));
		props = new Properties();
		props.load(fis);
	}

	public static Properties getProps() {
		return props;
	}

}
