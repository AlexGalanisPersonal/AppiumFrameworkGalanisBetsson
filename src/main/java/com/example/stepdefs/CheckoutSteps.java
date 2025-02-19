package com.example.stepdefs;

import io.cucumber.java.en.*;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import com.example.context.TestContext;
import java.time.Duration;

public class CheckoutSteps {
    private final TestContext context;
    private final AndroidDriver driver;

    public CheckoutSteps(TestContext context) {
        this.context = context;
        this.driver = context.getDriver();
    }

    @When("I tap on the shopping cart")
    public void iTapOnTheShoppingCart() {
        try {
            Thread.sleep(1000);
            WebElement cartButton = context.getWait().until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//android.view.ViewGroup[@content-desc='test-Cart']")));
            cartButton.click();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void scrollToElement(String contentDesc) {
        try {
            driver.findElement(MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
                            + "new UiSelector().description(\"" + contentDesc + "\"))"));
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @When("I tap on the checkout button")
    public void iTapOnTheCheckoutButton() throws InterruptedException {
        try {
            Thread.sleep(1000); // Wait for cart page to be fully loaded

            // Find and click checkout button
            WebElement checkoutButton = context.getWait().until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//android.view.ViewGroup[@content-desc='test-CHECKOUT']")));
            checkoutButton.click();

            Thread.sleep(2000); // Wait for navigation

        } catch (Exception e) {
            System.out.println("Error during checkout: " + e.getMessage());
            System.out.println("Current page source:");
            System.out.println(driver.getPageSource());
            e.printStackTrace();
            throw e;
        }
    }

    @Then("I should see the checkout information page")
    @Given("I am on the checkout information page")
    public void iAmOnTheCheckoutInformationPage() throws InterruptedException {
        try {
            Thread.sleep(2000);

            // Print page source for debugging
            System.out.println("Current page source:");
            System.out.println(driver.getPageSource());

            // Try different locator strategies
            boolean isDisplayed = false;
            try {
                // Try by content-desc
                isDisplayed = driver.findElement(
                                By.xpath("//android.view.ViewGroup[@content-desc='test-YOUR INFORMATION']"))
                        .isDisplayed();
            } catch (Exception e1) {
                try {
                    // Try by text
                    isDisplayed = driver.findElement(
                                    By.xpath("//android.widget.TextView[contains(@text, 'YOUR INFORMATION')]"))
                            .isDisplayed();
                } catch (Exception e2) {
                    try {
                        // Try by partial text
                        isDisplayed = driver.findElement(
                                        By.xpath("//android.widget.TextView[contains(@text, 'INFORMATION')]"))
                                .isDisplayed();
                    } catch (Exception e3) {
                        // Try one last time with a longer wait
                        isDisplayed = context.getWait().until(ExpectedConditions.presenceOfElementLocated(
                                        By.xpath("//*[contains(@text, 'INFORMATION') or contains(@content-desc, 'INFORMATION')]")))
                                .isDisplayed();
                    }
                }
            }

            Assert.assertTrue(isDisplayed, "Checkout information page is not displayed");
        } catch (Exception e) {
            System.out.println("Failed to verify checkout information page: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @When("I enter {string} as first name")
    public void iEnterAsFirstName(String firstName) {
        WebElement firstNameField = context.getWait().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.widget.EditText[@content-desc='test-First Name']")));
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    @When("I enter {string} as last name")
    public void iEnterAsLastName(String lastName) {
        WebElement lastNameField = context.getWait().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.widget.EditText[@content-desc='test-Last Name']")));
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    @When("I enter {string} as zip code")
    public void iEnterAsZipCode(String zipCode) {
        WebElement zipCodeField = context.getWait().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.widget.EditText[@content-desc='test-Zip/Postal Code']")));
        zipCodeField.clear();
        zipCodeField.sendKeys(zipCode);
    }

    @When("I tap on the continue button")
    public void iTapOnTheContinueButton() {
        try {
            Thread.sleep(500);
            WebElement continueButton = context.getWait().until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//android.view.ViewGroup[@content-desc='test-CONTINUE']")));
            continueButton.click();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("I should see a checkout error message {string}")
    public void iShouldSeeCheckoutErrorMessage(String errorMessage) {
        try {
            Thread.sleep(1000);
            WebElement errorElement = context.getWait().until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//android.view.ViewGroup[@content-desc='test-Error message']/android.widget.TextView")));
            Assert.assertTrue(errorElement.getText().contains(errorMessage),
                    "Expected error message containing '" + errorMessage + "' but got '" + errorElement.getText() + "'");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("I should see the checkout overview page")
    @Given("I am on the checkout overview page")
    public void iShouldSeeTheCheckoutOverviewPage() {
        try {
            Thread.sleep(2000);
            boolean isDisplayed = context.getWait().until(ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//android.view.ViewGroup[@text='CHECKOUT: OVERVIEW']")))
                    .isDisplayed();
            Assert.assertTrue(isDisplayed, "Checkout overview page is not displayed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("I should see the item total")
    public void iShouldSeeTheItemTotal() {
        WebElement itemTotal = context.getWait().until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//android.view.ViewGroup[@content-desc='test-Price total']")));
        Assert.assertTrue(itemTotal.isDisplayed(), "Item total is not displayed");
    }

    @Then("I should see the tax amount")
    public void iShouldSeeTheTaxAmount() {
        WebElement tax = context.getWait().until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//android.view.ViewGroup[@content-desc='test-Tax']")));
        Assert.assertTrue(tax.isDisplayed(), "Tax amount is not displayed");
    }

    @Then("I should see the total amount")
    public void iShouldSeeTheTotalAmount() {
        WebElement total = context.getWait().until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//android.view.ViewGroup[@content-desc='test-Total']")));
        Assert.assertTrue(total.isDisplayed(), "Total amount is not displayed");
    }

    @When("I tap on the finish button")
    public void iTapOnTheFinishButton() {
        try {
            WebElement finishButton = context.getWait().until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//android.view.ViewGroup[@content-desc='test-FINISH']")));
            finishButton.click();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("I should see {string} message")
    public void iShouldSeeMessage(String message) {
        try {
            Thread.sleep(1000);
            WebElement thankyouMessage = context.getWait().until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//android.widget.TextView[contains(@text, '" + message + "')]")));
            Assert.assertTrue(thankyouMessage.isDisplayed(),
                    "Expected message '" + message + "' is not displayed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}