Feature: Invite as Student
  In order to collaborate with professors and/or externals
  As a student
  I want to invite them to join my proposal

  Background:
    Given There is a registered student with username "student1" and password "password" and email "student1@sample.app"
    Given There is a registered student with username "student2" and password "password" and email "student2@sample.app"
    Given There is a registered professor with username "professor1" and password "password" and email "professor1@sample.app"
    Given There is a registered external with username "external1" and password "password" and email "external1@sample.app"
    Given There is a proposal "proposal1"

  Scenario: Successfully invite a professor
    Given I login as "student1" with password "password"
    And "student1" is a student
    And "professor1" is a registered user
    And "professor1" is a professor
    #When I create the invitation for user "professor1" to participate in proposal "proposal"
    #When "student1" send an invite to "professor1"
    #Then The response code is 201
    #And The user "professor1" has a pending invitation for "ProposalX"

  Scenario: Successfully invite an external
    Given I login as "student1" with password "password"
    And "student1" is a student
    And "external1" is a registered user
    And "external1" is an external
    #When I send an invite to "external1" for the proposal "ProposalX"
    #Then The response code is 201
    #And The user "external1" has a pending invitation for "ProposalX"

  Scenario: Fail to invite another student
    Given I login as "student1" with password "password"
    And "student1" is a student
    And "student2" is a registered user
    And "student2" is a student
    #When I send an invite to "student2" for the proposal "ProposalX"
    #Then The response code is 403
    #And The error message is "Students cannot invite other students"