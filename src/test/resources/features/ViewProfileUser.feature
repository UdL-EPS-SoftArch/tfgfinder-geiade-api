Feature: View User Profile
  In order to see profile information
  As a user
  I want to retrieve a user's profile

  Scenario: View own profile when authenticated
    Given There is a registered user with username "user" and password "password"
    When I login with username "user" and password "password"
    And I request the profile of user "user"
    Then The response code is 200
    And The profile contains the username "user"

  Scenario: View profile of another user
    Given There is a registered user with username "user1" and password "password1"
    And There is a registered user with username "user2" and password "password2"
    When I login with username "user1" and password "password1"
    And I request the profile of user "user2"
    Then The response code is 200
    And The profile contains the username "user2"

  Scenario: View profile of non-existent user
    Given There is a registered user with username "user" and password "password"
    When I login with username "user" and password "password"
    And I request the profile of user "ghost"
    Then The response code is 404
    And The error message is "User not found"
