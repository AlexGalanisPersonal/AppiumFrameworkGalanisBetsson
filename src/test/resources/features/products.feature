Feature: Products Page Functionality

  Background:
    Given I am on the login screen
    When I enter username "standard_user"
    And I enter password "secret_sauce"
    And I tap on the login button
    Then I should see the products page

  Scenario: User can view product details
    When I tap on the first product
    Then I should see the product details page
    And I should see the product title
    And I should see the product price
    And I should see the product description

  Scenario: User can add product to cart
    When I tap on the "ADD TO CART" button for the first product
    Then I should see the cart badge with count "1"
    And the button text should change to "REMOVE"

  Scenario: User can remove product from cart
    When I tap on the "ADD TO CART" button for the first product
    And I tap on the "REMOVE" button for the first product
    Then I should not see the cart badge
    And the button text should change to "ADD TO CART"