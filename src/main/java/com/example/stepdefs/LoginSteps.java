package com.example.stepdefs;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import com.example.context.TestContext;
import com.example.utils.ConfigurationManager;

public class LoginSteps {
    private final TestContext context;
    private final ConfigurationManager config;

    public LoginSteps(TestContext context) {
        this.context = context;
        this.config = ConfigurationManager.getInstance();
    }

    @Given("I am on the login screen")
    public void iAmOnTheLoginScreen() {
        // App starts on login screen by default
    }

    @When("I login as {string} user")
    public void iLoginAsUser(String userType) {
        String username = config.getTestProperty(userType + ".username");
        String password = config.getTestProperty(userType + ".password");

        // Enter username
        WebElement usernameField = context.getWait().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.widget.EditText[@content-desc='test-Username']")));
        usernameField.clear();
        usernameField.sendKeys(username);

        // Enter password
        WebElement passwordField = context.getWait().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.widget.EditText[@content-desc='test-Password']")));
        passwordField.clear();
        passwordField.sendKeys(password);

        // Click login
        context.getWait().until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//android.view.ViewGroup[@content-desc='test-LOGIN']")))
                .click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @When("I enter username {string}")
    public void iEnterUsername(String username) {
        WebElement usernameField = context.getWait().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.widget.EditText[@content-desc='test-Username']")));
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    @When("I enter password {string}")
    public void iEnterPassword(String password) {
        WebElement passwordField = context.getWait().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.widget.EditText[@content-desc='test-Password']")));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    @When("I tap on the login button")
    public void iTapOnTheLoginButton() {
        context.getWait().until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//android.view.ViewGroup[@content-desc='test-LOGIN']")))
                .click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("I should see the products page")
    public void iShouldSeeTheProductsPage() {
        boolean isDisplayed = context.getWait().until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//*[@content-desc='test-PRODUCTS' or @content-desc='PRODUCTS']")))
                .isDisplayed();
        Assert.assertTrue(isDisplayed, "Products page is not displayed");
    }

    @Then("I should see an error message containing {string}")
    public void iShouldSeeAnErrorMessageContaining(String errorMessage) {
        WebElement errorElement = context.getWait().until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//android.view.ViewGroup[@content-desc='test-Error message']/android.widget.TextView")));
        String actualError = errorElement.getText();
        Assert.assertTrue(actualError.contains(errorMessage),
                "Expected error message to contain '" + errorMessage + "' but got '" + actualError + "'");
    }
}