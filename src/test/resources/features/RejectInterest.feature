Feature: Reject Interest
    In order to reject interest
    As a User
    I want to reject an invite

    
Background:
    Given There is no registered user with username "user"
    And I'm not logged in
    When I register a new user with username "user", email "user@sample.app" and password "password"
    Then The response code is 201
    And It has been created a user with username "user" and email "user@sample.app", the password is not returned
    And I can login with username "user" and password "password"


Scenario: Try to reject an already created interest
    Given I can login with username "user" and password "password"
    And There is a proposal created
    When There already is an interest with the following details:
      | interestTitle | interestTitle |
      | proposalTitle  | proposalTitle     |
      | username    | user  |
      | status      |pending|
      | date        | 2024-03-17T08:00:00+01:00 |
    And I try to reject an interest with title "interestTitle"
    Then The response code is 200
    And There is only 1 interest with the details:
      | interestTitle | interestTitle |
      | proposalTitle  | proposalTitle     |
      | username    | user  |
      | status      |rejected|
      | date        | 2024-03-17T08:00:00+01:00 |


Scenario: Try to reject a new interest
    Given I can login with username "user" and password "password"
    And There is a proposal created
    When I try to reject an interest with title "interestTitle"
    Then The response code is 404