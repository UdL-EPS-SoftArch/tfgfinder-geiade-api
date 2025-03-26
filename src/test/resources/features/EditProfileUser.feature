Feature: Edit Profile User
  In order to update my personal information
  As an authenticated user
  I want to edit my profile

  Scenario: Edit own profile with valid data
    Given There is a registered user with username "user" and password "password"
    When I login with username "user" and password "password"
    And I update my profile with email "newemail@sample.app"
    Then The response code is 200
    And The profile contains the email "newemail@sample.app"

  Scenario: Edit profile without authentication
    When I update my profile with email "unauthorized@sample.app"
    Then The response code is 401
    And The error message is "Unauthorized"
