Feature: Show Interest
    In order to match a proposal
    As a student
    I want to show interest to the professor or organisation

Scenario: Show Interest without been authenticated
    Given I'm not logged in
    When I show interest to a proposal
    Then The interest object will not be created

Scenario: Show Interest
    Given There isn't class Interest for the user "user" and the proposal "proposal"
    When I show interest to the proposal "proposal"
    Then The interest object will be created with user "user" and proposal "proposal"