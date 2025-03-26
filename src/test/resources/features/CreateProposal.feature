Feature: Create a Proposal

  Scenario: Successfully creating a new proposal
    Given a user "JohnDoe" exists
    And a category "Software Engineering" with description "Software Engineering involves the design, development, and maintenance of software systems through systematic and structured approaches, focusing on quality, efficiency, and scalability." exists
    When the user creates a proposal with title "AI for Robotics"
    And description "Study of Artificial Intelligence (AI) applied to robotics involves developing intelligent systems that enable robots to perform tasks autonomously, improve decision-making, and enhance interaction with their environment, combining machine learning, computer vision, and advanced algorithms."
    And timing "6 months"
    And speciality "AI & Robotics"
    And kind "Research"
    And keywords "AI, Robotics"
    Then the proposal should be created successfully


  Scenario: Failing to create a proposal with a description less than 50 characters
    Given a user "JohnDoe" exists
    And a category "Software Engineering" with description "Software Engineering involves the design, development, and maintenance of software systems through systematic and structured approaches, focusing on quality, efficiency, and scalability." exists
    When the user creates a proposal with title "AI for Robotics"
    And description "AI research"
    And timing "6 months"
    And speciality "AI & Robotics"
    And kind "Research"
    And keywords "AI, Robotics"
    Then the system should display an error message "el tamaño debe estar entre 50 y 500"
    And the proposal should not be created

