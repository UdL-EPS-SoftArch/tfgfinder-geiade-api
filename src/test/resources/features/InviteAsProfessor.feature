Feature: Invite student and external to a proposal
  In order to be part of a proposal
  As a professor
  I want to invite students and externals to join my proposal

  Scenario: Successfully invite a student to a proposal
    Given I am logged in as a professor
    #And I have created a proposal
    #And there is a student registered in the system
    #When I send an invitation to the student
    #Then the student should receive an invitation
    #And the invitation status should be "pending"

  Scenario: Successfully invite multiple students to a proposal
    Given I am logged in as a professor
    And I have created a proposal
    And there are multiple students registered in the system
    When I send invitations to multiple students
    Then each student should receive an invitation
    And the invitations' status should be "pending"

  Scenario: Successfully invite an external to a proposal
    Given I am logged in as a professor
    And I have created a proposal
    And there is an external registered in the system
    When I send an invitation to the external
    Then the external should receive an invitation
    And the invitation status should be "pending"

  Scenario: Successfully invite multiple externals to a proposal
    Given I am logged in as a professor
    And I have created a proposal
    And there are multiple externals registered in the system
    When I send invitations to multiple externals
    Then each external should receive an invitation
    And the invitations' status should be "pending"

  Scenario: Successfully invite both students and externals to a proposal
    Given I am logged in as a professor
    And I have created a proposal
    And there are students and externals registered in the system
    When I send invitations to multiple students and externals
    Then each student and external should receive an invitation
    And the invitations' status should be "pending"

  Scenario: Invite a student who does not exist in the system
    Given I am logged in as a professor
    And I have created a proposal
    And the student is not registered in the system
    When I send an invitation
    Then I should see a message saying "Student not found"
    And the invitation should not be sent

  Scenario: Invite an external who does not exist in the system
    Given I am logged in as a professor
    And I have created a proposal
    And the external is not registered in the system
    When I send an invitation
    Then I should see a message saying "External not found"
    And the invitation should not be sent

  Scenario: A professor tries to invite a student who is already in the proposal
    Given I am logged in as a professor
    And I have created a proposal
    And I have already invited a student
    When I send another invitation to the same student
    Then I should see a message saying "Student already invited"
    And no new invitation should be sent

  Scenario: A professor tries to invite an external who is already in the proposal
    Given I am logged in as a professor
    And I have created a proposal
    And I have already invited an external
    When I send another invitation to the same external
    Then I should see a message saying "External already invited"
    And no new invitation should be sent

  Scenario: A student tries to invite another student
    Given I am logged in as a student
    And I have a proposal created by a professor
    When I try to send an invitation to another student
    Then I should see a message saying "Only the professor can invite members"
    And the invitation should not be sent

  Scenario: A professor tries to invite themselves as a student
    Given I am logged in as a professor
    And I have created a proposal
    When I send an invitation to myself as a student
    Then I should see a message saying "Invalid action"
    And the invitation should not be sent
