package com.example.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import com.example.utils.DriverManager;

public class Hooks {
    @Before
    public void beforeScenario(Scenario scenario) {
        DriverManager.resetApp();
    }

    @After
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            // Take screenshot or perform other failure handling if needed
            DriverManager.getDriver().getPageSource();
        }
    }

    @AfterAll
    public static void afterAll() {
        DriverManager.quitDriver();
    }
}