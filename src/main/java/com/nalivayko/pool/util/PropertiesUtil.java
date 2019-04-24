package com.nalivayko.pool.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

    private PropertiesUtil() {
    }

    public static String getProperty(String propName, String filename) {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(String.format("src/main/resources/%s.properties", filename)));
            return properties.getProperty(propName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
