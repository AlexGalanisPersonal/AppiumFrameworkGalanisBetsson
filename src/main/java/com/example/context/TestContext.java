package com.example.context;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.example.utils.DriverManager;

public class TestContext {
    private final AndroidDriver driver;
    private final WebDriverWait wait;

    public TestContext() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, 10);
    }

    public AndroidDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }
}