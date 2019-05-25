package com.nalivayko.pool.util;

import com.nalivayko.pool.exceptions.AppInitialisationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesUtil.class);

    private PropertiesUtil() {
    }

    public static String getProperty(String propName, String filename) {
        try (InputStream input =
                     PropertiesUtil.class.getClassLoader().getResourceAsStream(filename + ".properties")) {
            Properties properties = new Properties();
            properties.load(input);
            return properties.getProperty(propName);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            throw new AppInitialisationException("Cant load property: " + filename);
        }
    }
}
