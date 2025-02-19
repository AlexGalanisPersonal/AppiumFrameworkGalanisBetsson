package com.example.stepdefs;

import io.cucumber.java.en.*;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.example.utils.DriverManager;
import io.appium.java_client.android.AndroidDriver;

public class ProductsSteps {
    private final WebDriverWait wait;
    private final AndroidDriver driver;

    public ProductsSteps() {
        this.driver = DriverManager.getDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @When("I tap on the first product")
    public void iTapOnTheFirstProduct() {
        // Wait for products to be visible first
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//android.widget.ScrollView[@content-desc='test-PRODUCTS']")));

        // Find and click the first product's clickable container
        MobileElement firstProduct = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.view.ViewGroup[@content-desc='test-Item']//android.view.ViewGroup[@clickable='true']")));
        firstProduct.click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @When("I tap on the {string} button for the first product")
    public void iTapOnTheButtonForTheFirstProduct(String buttonText) throws InterruptedException {
        try {
            Thread.sleep(1000); // Wait for any animations
            String xpath;
            if (buttonText.equals("ADD TO CART")) {
                xpath = "(//android.view.ViewGroup[@content-desc='test-ADD TO CART'])[1]";
            } else if (buttonText.equals("REMOVE")) {
                xpath = "(//android.view.ViewGroup[@content-desc='test-REMOVE'])[1]";
            } else {
                xpath = String.format("(//android.view.ViewGroup[@content-desc='test-%s'])[1]", buttonText);
            }

            MobileElement button = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath(xpath)));
            button.click();

            Thread.sleep(2000); // Increased wait time after button click
        } catch (Exception e) {
            System.out.println("Failed to click button. Current page source:");
            System.out.println(driver.getPageSource());
            throw e;
        }
    }

    @Then("I should see the cart badge with count {string}")
    public void iShouldSeeTheCartBadgeWithCount(String count) {
        try {
            Thread.sleep(1000); // Wait for badge to update
            MobileElement cartBadge = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//android.view.ViewGroup[@content-desc='test-Cart']//android.widget.TextView")));
            Assert.assertEquals(cartBadge.getText(), count, "Cart badge count doesn't match expected value");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("I should not see the cart badge")
    public void iShouldNotSeeTheCartBadge() {
        try {
            Thread.sleep(2000); // Increased wait time for badge to disappear

            // Wait for badge to disappear (up to 5 seconds)
            WebDriverWait shortWait = new WebDriverWait(driver, 5);
            try {
                shortWait.until(ExpectedConditions.invisibilityOfElementLocated(
                        By.xpath("//android.view.ViewGroup[@content-desc='test-Cart']//android.widget.TextView")));
            } catch (TimeoutException e) {
                // If element is still present after waiting, fail the test
                Assert.fail("Cart badge is still visible when it should be hidden");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("the button text should change to {string}")
    public void theButtonTextShouldChangeTo(String expectedText) {
        try {
            Thread.sleep(1000); // Wait for button text to update
            String xpath = String.format("//android.view.ViewGroup[@content-desc='test-%s']", expectedText);
            MobileElement button = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath(xpath)));
            Assert.assertTrue(button.isDisplayed(), "Button with text '" + expectedText + "' is not displayed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("I should see the product details page")
    public void iShouldSeeTheProductDetailsPage() {
        try {
            Thread.sleep(1000); // Wait for transition
            // Check for either the back button or image container
            boolean hasBackButton = !driver.findElements(
                    By.xpath("//android.view.ViewGroup[@content-desc='test-BACK TO PRODUCTS']")).isEmpty();
            boolean hasImageContainer = !driver.findElements(
                    By.xpath("//android.view.ViewGroup[@content-desc='test-Image Container']")).isEmpty();
            Assert.assertTrue(hasBackButton || hasImageContainer, "Product details page is not displayed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("I should see the product title")
    public void iShouldSeeTheProductTitle() {
        // Look for any text element containing a product name
        MobileElement element = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//android.widget.TextView[contains(@text, 'Sauce Labs')]")));
        Assert.assertTrue(element.isDisplayed(), "Product title is not displayed");
    }

    @Then("I should see the product price")
    public void iShouldSeeTheProductPrice() {
        MobileElement element = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//android.widget.TextView[@content-desc='test-Price']")));
        Assert.assertTrue(element.isDisplayed(), "Product price is not displayed");
        Assert.assertTrue(element.getText().contains("$"), "Price format is incorrect");
    }

    @Then("I should see the product description")
    public void iShouldSeeTheProductDescription() {
        // The description is inside the test-Description container
        MobileElement element = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//android.view.ViewGroup[@content-desc='test-Description']//android.widget.TextView[2]")));
        Assert.assertTrue(element.isDisplayed(), "Product description is not displayed");
        Assert.assertFalse(element.getText().isEmpty(), "Product description is empty");
    }
}