Feature: Create Interest
    In order to show interest
    As a User
    I want to create an invite

    
Background:
    Given There is no registered user with username "user"
    And I'm not logged in
    When I register a new user with username "user", email "user@sample.app" and password "password"
    Then The response code is 201
    And It has been created a user with username "user" and email "user@sample.app", the password is not returned
    And I can login with username "user" and password "password"

Scenario: Try to create an already created interest
    Given I can login with username "user" and password "password"
    And There is a proposal created
    When There already is an interest with the following details:
      | proposalId  | 1     |
      | username    | user  |
      | status      |pending|
      | date        | 2024-03-17T08:00:00+01:00 |
    And I try to create an interest with the following details:
      | proposalId  | 1     |
      | username    | user  |
      | status      |pending|
      | date        | 2024-03-17T08:00:00+01:00 |
    Then The response code is 200
    And There is only 1 interest with the details:
      | proposalId  | 1     |
      | username    | user  |
      | status      |pending|
      | date        | 2024-03-17T08:00:00+01:00 |

@current
Scenario: Try to create a new interest
    Given I can login with username "user" and password "password"
    And There is a proposal created
    When I try to create an interest with the following details:
      | proposalId  | 1     |
      | username    | user  |
      | status      |pending|
      | date        | 2024-03-17T08:00:00+01:00 |
    Then The response code is 201
    And There is only 1 interest with the details:
      | proposalId  | 1     |
      | username    | user  |
      | status      |pending|
      | date        | 2024-03-17T08:00:00+01:00 |
