Feature: Login User
  In order to access my account
  As a registered user
  I want to log in to the system

  Scenario: Login with correct credentials
    Given There is a registered user with username "user" and password "password"
    When I login with username "user" and password "password"
    Then The response code is 200
    And I am successfully authenticated

  Scenario: Login with incorrect password
    Given There is a registered user with username "user" and password "password"
    When I login with username "user" and password "wrongpassword"
    Then The response code is 401
    And I am not authenticated

  Scenario: Login with non-existent username
    When I login with username "nonexistent" and password "password"
    Then The response code is 401
    And I am not authenticated

  Scenario: Login with empty username or password
    When I login with username "" and password "password"
    Then The response code is 401
    And The error message is "Unauthorized"

    When I login with username "user" and password ""
    Then The response code is 401
    And The error message is "Unauthorized"

  Scenario: Login when already authenticated
    Given I login as "user" with password "password"
    When I login with username "user" and password "password"
    Then The response code is 401
    And I remain logged in as "user"