Feature: Category Management

  Scenario: Create and retrieve a category
    Given There is a registered user with username "user" and password "existing"
    And I login as "user" with password "existing"
    When the user creates a category with name "Technology"
    And description "All about technology and the latest AI innovations"
    Then the category should be created successfully


  Scenario: Error creating category less than 50 char
    Given There is a registered user with username "user" and password "existing"
    And I login as "user" with password "existing"
    When the user creates a category with name "Technology"
    And description "All about technology"
    Then the category should not be created
    And The error message is "size must be between 50 and 500"

