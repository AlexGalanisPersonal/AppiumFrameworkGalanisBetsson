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
    When I enter "John" as first name
    And I enter "Doe" as last name
    And I enter "12345" as zip code
    And I tap on the continue button
    Then I should see the item total
    And I should see the tax amount
    And I should see the total amount
    Then I should see the checkout overview page

  Scenario: Verify order summary
    Given I am on the checkout overview page
    Then I should see the item total
    And I should see the tax amount
    And I should see the total amount

  Scenario: Complete order
    Given I am on the checkout overview page
    When I tap on the finish button
    Then I should see "THANK YOU FOR YOUR ORDER" message