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

Scenario: Try to create and show an already created interest
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

<<<<<<< HEAD
Scenario: Create an interest without being logged in
    Given Don't exist Interest with user "user" and id "1"
    And There isn't a user with user "user"
    When I show interest to proposal id "1"
    Then The response code is 401
    And The error message is "Unauthorized"
    And There is 0 Interest created with user "user" and proposal id "1"


Scenario: Create an interest that already exists
    Given Exists an interest with user "user" and proposal id "1"
    When I show interest to proposal id "1"
    Then The response code is 401
    And The error message is "Unauthorized"
    And There is 1 Interest created with user "user" and proposal id "1"
=======
Scenario: Try to create and show a new interest
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
>>>>>>> 69a56d14ff59e85bd4e601ed06c8b7161cb4b77e
