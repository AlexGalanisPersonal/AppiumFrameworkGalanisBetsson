Feature: Checkout Process

  Background:
    Given I am on the login screen
    When I login as "standard" user
    And I tap on the "ADD TO CART" button for the first product
    And I tap on the shopping cart
    And I tap on the checkout button

  Scenario: Submit empty checkout form
    Given I am on the checkout information page
    When I tap on the continue button
    Then I should see a checkout error message "First Name is required"

  Scenario: Enter valid checkout information
    Given I am on the checkout information page
    When I enter "John" as first name, "Doe" as last name, and "12345" as zip code
    And I tap on the continue button
    Then I should see the item total, tax amount, and total amount
    When I tap on the finish button
    Then I should see the order completion message