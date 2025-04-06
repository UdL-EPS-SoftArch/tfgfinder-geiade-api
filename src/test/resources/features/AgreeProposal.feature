Feature: Agree Proposal
  
  Scenario: Agree a proposal as student
    Given There is an authenticated student with id "studentID"
    When he agrees to a proposal with id "proposalID"
    Then the agree with id "agreeID" is created with status ACCEPTED
    And the student with id "studentID" and the proposal with id "proposalID" are linked to the agree with id "agreeID"

