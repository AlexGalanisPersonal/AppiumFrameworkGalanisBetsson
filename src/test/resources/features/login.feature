@login
Feature: Authentication
  As a Swag Labs mobile app user
  I want to be able to authenticate with my credentials
  So that I can access the application features

  Background:
    Given I am on the login screen

  @smoke @positive
  Scenario: Successful login with standard user
    When I login as "standard" user
    Then I should see the products page

  @negative
  Scenario Outline: Failed login attempts with invalid credentials
    When I enter username "<username>"
    And I enter password "<password>"
    And I tap on the login button
    Then I should see an error message containing "<error_message>"

    Examples:
      | username      | password        | error_message                          |
      | invalid_user  | secret_sauce    | Username and password do not match     |
      | standard_user | wrong_password  | Username and password do not match     |
      |              |                  | Username is required                    |

  @negative @locked
  Scenario: Locked out user cannot access the system
    When I login as "locked" user
    Then I should see an error message containing "Sorry, this user has been locked out"