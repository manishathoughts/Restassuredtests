package com.elsevier.usage.apitests.utils;

import org.apache.commons.lang3.StringUtils;

public class PropertyResolver {
    public static final String ENV_PROP_NAME = "environment";
    public static final String ENV_NONPROD = "nonprod";
    private static final String environment;
    public static final String RETRY_PROP_NAME="maximum";
    private static final String maximum;
    public static final String RETRY_PROP_VALUE="10";
    static {
        String env = System.getenv(ENV_PROP_NAME);
        if  (StringUtils.isEmpty(env))
        {
            env = System.getProperty(ENV_PROP_NAME, ENV_NONPROD);
        }
       
        environment = env;
    }

    static {
        String max = System.getenv(RETRY_PROP_NAME);
        if  (StringUtils.isEmpty(max))
        {
            max = System.getProperty(RETRY_PROP_NAME, RETRY_PROP_VALUE);
        }

        maximum = max;
    }
    public static String envResolve() {
        return environment;
    }
    public static String retryValue() {
        return maximum;
    }
}
