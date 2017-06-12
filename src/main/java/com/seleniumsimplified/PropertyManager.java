package com.seleniumsimplified;

import java.io.*;
import java.util.Properties;

public class PropertyManager {
    private static Properties properties;

    public static String getProperty(String propertyKey) throws IOException {
        FileInputStream propertiesFileInput = new FileInputStream(new File("src/main/resources/config.properties.xml"));
        properties = new Properties();
        properties.loadFromXML(propertiesFileInput);
        propertiesFileInput.close();
        return properties.getProperty(propertyKey);
    }
}
