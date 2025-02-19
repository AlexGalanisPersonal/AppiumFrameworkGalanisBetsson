package com.example.stepdefs;

import io.cucumber.java.en.*;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import com.example.context.TestContext;

public class CheckoutSteps {
    private final TestContext context;
    private final AndroidDriver driver;

    public CheckoutSteps(TestContext context) {
        this.context = context;
        this.driver = context.getDriver();
    }

    private void scrollToElement(String contentDesc) {
        String uiAutomatorString =
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
                        + "new UiSelector().descriptionContains(\"" + contentDesc + "\"))";
        driver.findElement(MobileBy.AndroidUIAutomator(uiAutomatorString));
    }

    @When("I tap on the shopping cart")
    public void iTapOnTheShoppingCart() {
        WebElement cartButton = context.getWait().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.view.ViewGroup[@content-desc='test-Cart']/android.view.ViewGroup")));
        cartButton.click();
    }

    @When("I tap on the checkout button")
    public void iTapOnTheCheckoutButton() {
        WebElement checkoutButton = context.getWait().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.view.ViewGroup[@content-desc='test-CHECKOUT']")));
        checkoutButton.click();
    }

    @Then("I should see the checkout information page")
    @Given("I am on the checkout information page")
    public void iAmOnTheCheckoutInformationPage() {
        WebElement checkoutForm = context.getWait().until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//android.widget.ScrollView[@content-desc='test-Checkout: Your Info']")));
        Assert.assertTrue(checkoutForm.isDisplayed(), "Checkout information form is not displayed");
    }

    @When("I enter {string} as first name, {string} as last name, and {string} as zip code")
    public void iEnterDetails(String firstName, String lastName, String zipCode) {
        WebElement firstNameField = context.getWait().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.widget.EditText[@content-desc='test-First Name']")));
        firstNameField.clear();
        firstNameField.sendKeys(firstName);

        WebElement lastNameField = context.getWait().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.widget.EditText[@content-desc='test-Last Name']")));
        lastNameField.clear();
        lastNameField.sendKeys(lastName);

        WebElement zipCodeField = context.getWait().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.widget.EditText[@content-desc='test-Zip/Postal Code']")));
        zipCodeField.clear();
        zipCodeField.sendKeys(zipCode);
    }

    @When("I tap on the continue button")
    public void iTapOnTheContinueButton() {
        WebElement continueButton = context.getWait().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.view.ViewGroup[@content-desc='test-CONTINUE']")));
        continueButton.click();
    }

    @Then("I should see a checkout error message {string}")
    public void iShouldSeeCheckoutErrorMessage(String errorMessage) {
        WebElement errorElement = context.getWait().until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//android.view.ViewGroup[@content-desc='test-Error message']/android.widget.TextView")));
        Assert.assertTrue(errorElement.getText().contains(errorMessage),
                "Expected error message containing '" + errorMessage + "' but got '" + errorElement.getText() + "'");
    }

    @Then("I should see the item total, tax amount, and total amount")
    public void iShouldSeeTheOrderSummary() {
        WebElement overviewContainer = context.getWait().until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//android.widget.TextView[contains(@text, 'CHECKOUT: OVERVIEW')]")));
        Assert.assertTrue(overviewContainer.isDisplayed(), "Checkout overview page is not displayed");

        java.util.List<WebElement> textElements = driver.findElements(By.xpath("//android.widget.TextView"));
        boolean foundItemTotal = false;
        boolean foundTax = false;
        boolean foundTotal = false;

        for (WebElement element : textElements) {
            String text = element.getText();
            if (text.contains("Item total:")) foundItemTotal = true;
            else if (text.contains("Tax:")) foundTax = true;
            else if (text.contains("Total:")) foundTotal = true;
        }

        Assert.assertTrue(foundItemTotal, "Item total is not displayed");
        Assert.assertTrue(foundTax, "Tax amount is not displayed");
        Assert.assertTrue(foundTotal, "Total amount is not displayed");
    }

    @When("I tap on the finish button")
    public void iTapOnTheFinishButton() {
        scrollToElement("test-FINISH");
        WebElement finishButton = context.getWait().until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.view.ViewGroup[@content-desc='test-FINISH']")));
        finishButton.click();
    }

    @Then("I should see the order completion message")
    public void iShouldSeeTheOrderCompletionMessage() {
        WebElement thankYouMessage = context.getWait().until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//android.widget.TextView[contains(@text, 'THANK YOU FOR YOU ORDER')]")));
        Assert.assertTrue(thankYouMessage.isDisplayed(), "Order completion message is not displayed");
    }
}