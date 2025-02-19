package com.example.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import com.example.utils.DriverManager;

public class Hooks {

    @Before
    public void beforeScenario(Scenario scenario) {
        System.out.println("Starting scenario: " + scenario.getName());
        // Reset the app state before each scenario
        DriverManager.resetApp();
    }

    @After
    public void afterScenario(Scenario scenario) {
        System.out.println("Completed scenario: " + scenario.getName());
        if (scenario.isFailed()) {
            // If the scenario failed, print the page source for debugging
            System.out.println("Scenario failed. Current page source:");
            System.out.println(DriverManager.getDriver().getPageSource());
        }
    }

    @AfterAll
    public static void afterAll() {
        // Quit the driver after all scenarios are complete
        DriverManager.quitDriver();
    }
}