package com.sodabottle.freadr.utils;

import com.google.common.collect.Maps;
import org.springframework.context.annotation.Configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Loads the Properties from the specified file as key-value pairs
 *
 * @author RV
 */
@Configuration(value = "notificationPropertyLoader")
public class PropertyLoader {

    /**
     * Returns the Properties from the specified property file as key-value pairs
     *
     * @param fileName
     * @return Map<String   ,   String> (property key-value pairs)
     * @throws IOException
     */
    public Map<String, String> getPropertyValuesAsMap(String fileName) throws IOException {
        Map<String, String> propertyMap = new LinkedHashMap<>();
        InputStream inputStream = null;
        try {
            Properties prop = new Properties();
            inputStream = getClass().getClassLoader().getResourceAsStream(fileName);

            if (inputStream != null)
                prop.load(inputStream);
            else
                throw new FileNotFoundException("--- property file --- '" + fileName + "' not found in the classpath");

            if (null != prop) {
                propertyMap = Maps.fromProperties(prop);

            }

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            if (inputStream != null)
                inputStream.close();
        }

        return null == propertyMap ? new HashMap<>() : propertyMap;
    }
}