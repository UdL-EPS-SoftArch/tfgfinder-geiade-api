Feature: Agree Proposal

  Background:
    Given There is a registered user with username "user" and password "password"

  Scenario: Agree to an already created agree
    Given I can login with username "user" and password "password"
    And There already is a proposal by "user"
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
    And There already is a proposal by "user"
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

