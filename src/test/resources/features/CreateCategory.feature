Feature: Category Management

  Scenario: Create and retrieve a category
    Given a category with name "Technology" and description "All about technology and the latest AI innovations"
    When I save the category
    And I retrieve the category by name
    Then the category should exist with name "Technology" and description "All about technology and the latest AI innovations"


  Scenario: Error creating category less than 50 char
    Given a category with name "Technology" and description "All about technology"
    Then the system should throw a constraint error
    And the proposal should not be created