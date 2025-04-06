Feature: Invite creation
  In order to collaborate with other users (student, professor or external)
  As a user (student, professor or external)
  I want to invite others to join my proposal

  Background:
    Given There is a registered user with username "student1" and password "password"
    And "student1" is a Student with name "Diego", surname "Colás", DNI "12345678A", address "123 Street", municipality "City", postalCode "12345", phoneNumber "600123456" and degree "Computer Science"
    Given There is a registered user with username "student2" and password "password"
    And "student2" is a Student with name "Claudia", surname "Giribet", DNI "23456789B", address "456 Street", municipality "City", postalCode "12345", phoneNumber "600987654" and degree "Mathematics"

    Given There is a registered user with username "professor1" and password "password"
    And "professor1" is a Professor with name "Roberto" and surname "García" of faculty "EPS" and department "Computer Engineering"
    Given There is a registered user with username "professor2" and password "password"
    And "professor2" is a Professor with name "Àngela" and surname "Vázquez" of faculty "EPS" and department "Mathematics"

    Given There is a registered user with username "external1" and password "password"
    And "external1" is an External with name "Ringo", surname "Giné", position "Consultant", organization "Tech Corp", address "789 Corporate Blvd", municipality "Tech City", postalCode "54321", phoneNumber "600123789"
    Given There is a registered user with username "external2" and password "password"
    And "external2" is an External with name "Francesc", surname "Tena", position "Senior Engineer", organization "Innovative Solutions", address "101 Innovation Rd", municipality "Innovate City", postalCode "98765", phoneNumber "600987321"
    Given There is a proposal titled "TREBALL FINAL DE GRAU" with description "Detailed description of the project for a final degree proposal" and timing "timing" and specialty "specialty" and kind "kindd"

# Valid Invites
  Scenario: Create a valid invite from student to professor
    Given I login as "student1" with password "password"
    When "student1" creates an invite to user "professor1" for proposal "TREBALL FINAL DE GRAU" with status "PENDING" and date "2025-04-06T12:00:00Z"
    Then The invite to user "professor1" of proposal "TREBALL FINAL DE GRAU" is created with status "PENDING"

  Scenario: Create a valid invite from student to external
    Given I login as "student1" with password "password"
    When "student1" creates an invite to user "external1" for proposal "TREBALL FINAL DE GRAU" with status "PENDING" and date "2025-04-06T12:00:00Z"
    Then The invite to user "external1" of proposal "TREBALL FINAL DE GRAU" is created with status "PENDING"

  Scenario: Create a valid invite from professor to student
    Given I login as "professor1" with password "password"
    When "professor1" creates an invite to user "student2" for proposal "TREBALL FINAL DE GRAU" with status "PENDING" and date "2025-04-06T12:00:00Z"
    Then The invite to user "student2" of proposal "TREBALL FINAL DE GRAU" is created with status "PENDING"

  Scenario: Create a valid invite from professor to external
    Given I login as "professor2" with password "password"
    When "professor2" creates an invite to user "external2" for proposal "TREBALL FINAL DE GRAU" with status "PENDING" and date "2025-04-06T12:00:00Z"
    Then The invite to user "external2" of proposal "TREBALL FINAL DE GRAU" is created with status "PENDING"

  Scenario: Create a valid invite from external to student
    Given I login as "external1" with password "password"
    When "external1" creates an invite to user "student2" for proposal "TREBALL FINAL DE GRAU" with status "PENDING" and date "2025-04-06T12:00:00Z"
    Then The invite to user "student2" of proposal "TREBALL FINAL DE GRAU" is created with status "PENDING"

  Scenario: Create a valid invite from external to professor
    Given I login as "external2" with password "password"
    When "external2" creates an invite to user "professor2" for proposal "TREBALL FINAL DE GRAU" with status "PENDING" and date "2025-04-06T12:00:00Z"
    Then The invite to user "professor2" of proposal "TREBALL FINAL DE GRAU" is created with status "PENDING"

# Invalid Invites
  Scenario: Attempt to create an invite from student to student
    Given I login as "student1" with password "password"
    When "student1" creates an invite to user "student2" for proposal "TREBALL FINAL DE GRAU" with status "PENDING" and date "2025-04-06T12:00:00Z"
    Then The invite will not be created

  Scenario: Attempt to create an invite from professor to professor
    Given I login as "professor1" with password "password"
    When "professor1" creates an invite to user "professor2" for proposal "TREBALL FINAL DE GRAU" with status "PENDING" and date "2025-04-06T12:00:00Z"
    Then The invite will not be created

  Scenario: Attempt to create an invite from external to external
    Given I login as "external1" with password "password"
    When "external1" creates an invite to user "external2" for proposal "TREBALL FINAL DE GRAU" with status "PENDING" and date "2025-04-06T12:00:00Z"
    Then The invite will not be created