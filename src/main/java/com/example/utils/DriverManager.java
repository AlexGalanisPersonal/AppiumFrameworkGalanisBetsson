package com.example.utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;

public class DriverManager {
    private static volatile AndroidDriver driver;
    private static final Object lock = new Object();
    private static final ConfigurationManager config = ConfigurationManager.getInstance();

    public static AndroidDriver getDriver() {
        if (driver == null) {
            synchronized (lock) {
                if (driver == null) {
                    setupDriver();
                }
            }
        }
        return driver;
    }

    private static void setupDriver() {
        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("platformName", "Android");
            caps.setCapability("automationName", "UiAutomator2");
            caps.setCapability("deviceName", config.getEnvProperty("device.name"));
            caps.setCapability("appPackage", config.getEnvProperty("app.package"));
            caps.setCapability("appActivity", config.getEnvProperty("app.activity"));
            caps.setCapability("noReset", false);
            caps.setCapability("fullReset", false);

            URL url = new URL(config.getEnvProperty("appium.server.url"));
            driver = new AndroidDriver(url, caps);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize driver", e);
        }
    }

    public static void resetApp() {
        try {
            String appPackage = config.getEnvProperty("app.package");
            if (driver != null) {
                driver.terminateApp(appPackage);
                Thread.sleep(1000);
                driver.activateApp(appPackage);
                Thread.sleep(2000);
            } else {
                getDriver();
            }
        } catch (Exception e) {
            quitDriver();
            getDriver();
        }
    }

    public static void quitDriver() {
        try {
            if (driver != null) {
                driver.terminateApp(config.getEnvProperty("app.package"));
                Thread.sleep(1000);
                driver.quit();
            }
        } catch (Exception e) {
            // Silently handle quit errors
        } finally {
            driver = null;
        }
    }
}