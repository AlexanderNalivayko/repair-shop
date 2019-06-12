package com.nalivayko.pool.repair_shop.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

    private PropertiesUtil() {
    }

    public static String getProperty(String propName, String filename) throws IOException {
        try (InputStream input =
                     PropertiesUtil.class.getClassLoader().getResourceAsStream(filename + ".properties")) {
            Properties properties = new Properties();
            properties.load(input);
            return properties.getProperty(propName);
        }
    }
}
