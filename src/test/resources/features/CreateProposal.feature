Feature: Create a Proposal

  Scenario: Successfully creating a new proposal
    Given There is a registered user with username "user" and password "existing" and email "user@sample.app"
    And I login as "user" with password "existing"
    And a category "Software Engineering" with description "Software Engineering involves the design, development, and maintenance of software systems through systematic and structured approaches, focusing on quality, efficiency, and scalability." exists
    When the user creates a proposal with title "AI for Robotics"
    And description "Study of Artificial Intelligence (AI) applied to robotics involves developing intelligent systems that enable robots to perform tasks autonomously, improve decision-making, and enhance interaction with their environment, combining machine learning, computer vision, and advanced algorithms."
    And timing "6 months"
    And speciality "AI & Robotics"
    And kind "Research"
    And category with name "Software Engineering"
    And keywords "AI, Robotics"
    Then the proposal should be created successfully


  Scenario: Failing to create a proposal with a description less than 50 characters
    Given There is a registered user with username "user" and password "existing" and email "user@sample.app"
    And I login as "user" with password "existing"
    And a category "Software Engineering" with description "Software Engineering involves the design, development, and maintenance of software systems through systematic and structured approaches, focusing on quality, efficiency, and scalability." exists
    When the user creates a proposal with title "AI for Robotics"
    And description "AI research"
    And timing "6 months"
    And speciality "AI & Robotics"
    And kind "Research"
    And keywords "AI, Robotics"
    Then the system should throw a constraint error
    And the proposal should not be created

