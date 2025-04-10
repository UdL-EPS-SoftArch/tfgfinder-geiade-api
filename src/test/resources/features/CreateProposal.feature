Feature: Create a Proposal

  Scenario: Successfully creating a new proposal
    Given There is a registered user with username "user" and password "password"
    And I login as "user" with password "password"
    And a category "Software Engineering" with description "Software Engineering involves the design, development, and maintenance of software systems through systematic and structured approaches, focusing on quality, efficiency, and scalability." exists
    When the user creates a proposal with title "AI for Robotics"
    And proposal description "Study of Artificial Intelligence (AI) applied to robotics involves developing intelligent systems that enable robots to perform tasks autonomously, improve decision-making, and enhance interaction with their environment, combining machine learning, computer vision, and advanced algorithms."
    And timing "6 months"
    And speciality "AI & Robotics"
    And kind "Research"
    And category with name "Software Engineering"
    And keywords "AI, Robotics"
    Then the proposal should be created successfully


  Scenario: Failing to create a proposal with a description less than 50 characters
    Given There is a registered user with username "user" and password "password"
    And I login as "user" with password "password"
    And a category "Software Engineering" with description "Software Engineering involves the design, development, and maintenance of software systems through systematic and structured approaches, focusing on quality, efficiency, and scalability." exists
    When the user creates a proposal with title "AI for Robotics"
    And proposal description "AI research"
    And timing "6 months"
    And speciality "AI & Robotics"
    And kind "Research"
    And keywords "AI, Robotics"
    Then the proposal should not be created
    And The error message is "size must be between 50 and 500"

  Scenario: Failing to create a proposal without logging in
    Given I'm not logged in
    And a category "Software Engineering" with description "Software Engineering involves the design, development, and maintenance of software systems through systematic and structured approaches, focusing on quality, efficiency, and scalability." exists
    When the user creates a proposal with title "AI for Robotics"
    And proposal description "Study of Artificial Intelligence (AI) applied to robotics involves developing intelligent systems that enable robots to perform tasks autonomously, improve decision-making, and enhance interaction with their environment, combining machine learning, computer vision, and advanced algorithms."
    And timing "6 months"
    And speciality "AI & Robotics"
    And kind "Research"
    And category with name "Software Engineering"
    And keywords "AI, Robotics"
    Then the proposal should not be created
    And The error message is "must not be null"
