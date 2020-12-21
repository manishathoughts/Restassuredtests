package com.elsevier.usage.apitests.utils;

import org.apache.commons.lang3.StringUtils;

public class PropertyResolver {
    public static final String ENV_PROP_NAME = "environment";
    public static final String ENV_NONPROD = "prod";
    private static final String environment;
    static {
        String env = System.getenv(ENV_PROP_NAME);
        if  (StringUtils.isEmpty(env))
        {
            env = System.getProperty(ENV_PROP_NAME, ENV_NONPROD);
        }
       
        environment = env;
    }

    public static String envResolve() {
        return environment;
    }

}
