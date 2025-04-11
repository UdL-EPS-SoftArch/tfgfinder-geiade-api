Feature: Agree Proposal
  
  Background:
    Given There is no registered user with username "user"
    And I'm not logged in
    When I register a new user with username "user", email "user@sample.app" and password "password"
    Then The response code is 201
    And It has been created a user with username "user" and email "user@sample.app", the password is not returned
    And I can login with username "user" and password "password"

  Scenario: Agree to an already created agree
    Given I can login with username "user" and password "password"
    And There already is a proposal
    When There already is an agree with the following details:
      | proposalTitle  | Gestión Propuestas     |
      | username    | user  |
      | status      |pending|
      | date        | 2024-03-17T08:00:00+01:00 |
    And I agree to an agree with the following details:
      | proposalTitle  | Gestión Propuestas     |
      | username    | user  |
      | status      | accepted|
      | date        | 2024-03-17T08:00:00+01:00 |
    Then The response code is 200
    And There is only 1 agree with the details:
      | proposalTitle  | Gestión Propuestas     |
      | username    | user  |
      | status      | accepted  |
      | date        | 2024-03-17T08:00:00+01:00 |

Scenario: Agree to a new agree with a created proposal
    Given I can login with username "user" and password "password"
    And There already is a proposal
    When I agree to an agree with the following details:
      | proposalTitle  | Gestión Propuestas     |
      | username    | user  |
      | status      | accepted  |
      | date        | 2024-03-17T08:00:00+01:00 |
    Then The response code is 201
    And There is only 1 agree with the details:
      | proposalTitle  | Gestión Propuestas     |
      | username    | user  |
      | status      | accepted  |
      | date        | 2024-03-17T08:00:00+01:00 |

