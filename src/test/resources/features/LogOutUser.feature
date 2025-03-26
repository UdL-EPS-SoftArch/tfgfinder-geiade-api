Feature: Logout User
  As an authenticated user
  I want to log out of my account
  So that I can end my session securely

  Scenario: Logout successfully
    Given There is a registered user with username "user" and password "password"
    When I login with username "user" and password "password"
    And I log out
    Then The response code is 204
    And I am not authenticated after logout

  Scenario: Logout without being logged in
    When I log out
    Then The response code is 204

  Scenario: Login after logout
    Given There is a registered user with username "user" and password "password"
    When I login with username "user" and password "password"
    And I log out
    And I login with username "user" and password "password"
    Then The response code is 200
    And I am successfully authenticated
