Feature: Proposal creation

  Scenario: Successfully create a new proposal
    Given I have a valid proposal with the following details:
      | title                | Research on AI Ethics                                                                                      |
      | description          | This study focuses on the ethical implications of AI in modern society and how it affects decision-making. |
      | timing               | 6 months                                                                                                   |
      | speciality           | Artificial Intelligence                                                                                    |
      | kind                 | Thesis                                                                                                     |
      | category             | AI                                                                                                         |
      | category_description | AI Description                                                                                             |
    When I save the proposal
    Then The proposal should be saved successfully
    And I can retrieve the proposal by its ID
