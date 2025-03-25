Feature: Invite as Student
  In order to collaborate in the proposal with professors and externals
  As a student
  I want to invite them to join my proposal

  Scenario: Successfully invite a professor
    Given I am logged in as a student with username "student1"
    And There is a registered professor with username "professor1"
    When I send an invite to "professor1" for the proposal "ProposalX"
    Then The response code is 201
    And The user "professor1" has a pending invitation for "ProposalX"

  Scenario: Successfully invite an external
    Given I am logged in as a student with username "student1"
    And There is a registered external user with username "external1"
    When I send an invite to "external1" for the proposal "ProposalX"
    Then The response code is 201
    And The user "external1" has a pending invitation for "ProposalX"

  Scenario: Fail to invite another student
    Given I am logged in as a student with username "student1"
    And There is a registered student with username "student2"
    When I send an invite to "student2" for the proposal "ProposalX"
    Then The response code is 403
    And The error message is "Students cannot invite other students"