package com.example.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {
    private static final Properties testConfig = new Properties();
    private static final Properties envConfig = new Properties();
    private static volatile ConfigurationManager instance;

    private ConfigurationManager() {
        loadProperties();
    }

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            synchronized (ConfigurationManager.class) {
                if (instance == null) {
                    instance = new ConfigurationManager();
                }
            }
        }
        return instance;
    }

    private void loadProperties() {
        try {
            // Load test configuration
            testConfig.load(new FileInputStream("src/test/resources/config/test.properties"));
            // Load environment configuration
            envConfig.load(new FileInputStream("src/test/resources/config/env.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration files", e);
        }
    }

    public String getTestProperty(String key) {
        return testConfig.getProperty(key);
    }

    public String getEnvProperty(String key) {
        return envConfig.getProperty(key);
    }
}