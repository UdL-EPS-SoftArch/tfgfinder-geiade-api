Feature: Invite creation
  In order to collaborate with other users (student, professor or external)
  As a user (student, professor or external)
  I want to invite others to join my proposal

  Background:

    Given "student1" is a Student with name "Diego", surname "Colás", DNI "12345678A", address "123 Street", municipality "City", postalCode "12345", phoneNumber "600123456" and degree "Computer Science" and email "s1@gmail.com" and password "password"
    Given "student2" is a Student with name "Claudia", surname "Giribet", DNI "23456789B", address "456 Street", municipality "City", postalCode "12345", phoneNumber "600987654" and degree "Mathematics" and email "s2@gmail.com" and password "password"

    Given "professor1" is a Professor with name "Roberto" and surname "García" of faculty "EPS" and department "Computer Engineering" and email "p1@gmail.com" and password "password"
    Given "professor2" is a Professor with name "Àngela" and surname "Vázquez" of faculty "EPS" and department "Mathematics" and email "p2@gmail.com" and password "password"

    Given "external1" is an External with name "Ringo", surname "Giné", position "Consultant", organization "Tech Corp", address "789 Corporate Blvd", municipality "Tech City", postalCode "54321", phoneNumber "600123789" and email "e1@gmail.com" and password "password"
    Given "external2" is an External with name "Francesc", surname "Tena", position "Senior Engineer", organization "Innovative Solutions", address "101 Innovation Rd", municipality "Innovate City", postalCode "98765", phoneNumber "600987321" and email "e2@gmail.com" and password "password"

    Given There is a proposal by "student1" titled "TREBALL FINAL DE GRAU" with description "Detailed description of the project for a final degree proposal" and timing "timing" and specialty "specialty" and kind "kindd"

# Valid Invites

  Scenario: Create a valid invite from student to professor
    Given I login with username "student1" and password "password"
    When "student1" creates an invite to user "professor1" for proposal "TREBALL FINAL DE GRAU" with status "PENDING" and date "2025-04-06T12:00:00Z"
    Then The invite to user "professor1" of proposal "TREBALL FINAL DE GRAU" is created with status "PENDING"
    Then The response code is 200

  Scenario: Create a valid invite from student to external
    Given I login with username "student1" and password "password"
    When "student1" creates an invite to user "external1" for proposal "TREBALL FINAL DE GRAU" with status "PENDING" and date "2025-04-06T12:00:00Z"
    Then The invite to user "external1" of proposal "TREBALL FINAL DE GRAU" is created with status "PENDING"
    Then The response code is 200

  Scenario: Create a valid invite from professor to student
    Given I login with username "professor1" and password "password"
    When "professor1" creates an invite to user "student1" for proposal "TREBALL FINAL DE GRAU" with status "PENDING" and date "2025-04-06T12:00:00Z"
    Then The invite to user "student1" of proposal "TREBALL FINAL DE GRAU" is created with status "PENDING"
    Then The response code is 200

  Scenario: Create a valid invite from professor to external
    Given I login with username "professor1" and password "password"
    When "professor1" creates an invite to user "external1" for proposal "TREBALL FINAL DE GRAU" with status "PENDING" and date "2025-04-06T12:00:00Z"
    Then The invite to user "external1" of proposal "TREBALL FINAL DE GRAU" is created with status "PENDING"
    Then The response code is 200

  Scenario: Create a valid invite from external to student
    Given I login with username "external1" and password "password"
    When "external1" creates an invite to user "student1" for proposal "TREBALL FINAL DE GRAU" with status "PENDING" and date "2025-04-06T12:00:00Z"
    Then The invite to user "student1" of proposal "TREBALL FINAL DE GRAU" is created with status "PENDING"
    Then The response code is 200

  Scenario: Create a valid invite from external to professor
    Given I login with username "external1" and password "password"
    When "external1" creates an invite to user "professor1" for proposal "TREBALL FINAL DE GRAU" with status "PENDING" and date "2025-04-06T12:00:00Z"
    Then The invite to user "professor1" of proposal "TREBALL FINAL DE GRAU" is created with status "PENDING"
    Then The response code is 200

# Invalid Invites

  Scenario: Attempt to create an invite from student to student
    Given I login with username "student1" and password "password"
    When "student1" creates an invite to user "student2" for proposal "TREBALL FINAL DE GRAU" with status "PENDING" and date "2025-04-06T12:00:00Z"
    Then The invite to "student2" for "TREBALL FINAL DE GRAU" will not be created
    Then The response code is 500

  Scenario: Attempt to create an invite from professor to professor
    Given I login with username "professor1" and password "password"
    When "professor1" creates an invite to user "professor2" for proposal "TREBALL FINAL DE GRAU" with status "PENDING" and date "2025-04-06T12:00:00Z"
    Then The invite to "professor2" for "TREBALL FINAL DE GRAU" will not be created
    Then The response code is 500

  Scenario: Attempt to create an invite from external to external
    Given I login with username "external1" and password "password"
    When "external1" creates an invite to user "external2" for proposal "TREBALL FINAL DE GRAU" with status "PENDING" and date "2025-04-06T12:00:00Z"
    Then The invite to "external2" for "TREBALL FINAL DE GRAU" will not be created
    Then The response code is 500

# Missing Data

  Scenario: Attempt to create an invite without specifying the who
    Given I login with username "student1" and password "password"
    When "student1" creates an invite for proposal "TREBALL FINAL DE GRAU" with status "PENDING" and date "2025-04-06T12:00:00Z"
    Then The response code is 500

  Scenario: Attempt to create an invite with empty who
    Given I login with username "student1" and password "password"
    When "student1" creates an invite to user "" for proposal "TREBALL FINAL DE GRAU" with status "PENDING" and date "2025-04-06T12:00:00Z"
    Then The response code is 500

  Scenario: Attempt to create an invite without specifying the what
    Given I login with username "student1" and password "password"
    When "student1"creates an invite to user "professor1" with status "PENDING" and date "2025-04-06T12:00:00Z"
    Then The response code is 400

  Scenario: Attempt to create an invite with empty what
    Given I login with username "student1" and password "password"
    When "student1" creates an invite to user "professor1" for proposal "" with status "PENDING" and date "2025-04-06T12:00:00Z"
    Then The response code is 400

  Scenario: Attempt to create an invite without specifying the status
    Given I login with username "student1" and password "password"
    When "student1" creates an invite to user "professor1" for proposal "TREBALL FINAL DE GRAU" with date "2025-04-06T12:00:00Z"
    Then The response code is 400

  Scenario: Attempt to create an invite with empty status
    Given I login with username "student1" and password "password"
    When "student1" creates an invite to user "professor1" for proposal "TREBALL FINAL DE GRAU" with status "" and date "2025-04-06T12:00:00Z"
    Then The response code is 400

  Scenario: Attempt to create an invite without specifying the date
    Given I login with username "student1" and password "password"
    When "student1" creates an invite to user "professor1" for proposal "TREBALL FINAL DE GRAU" with status "PENDING"
    Then The response code is 400

  Scenario: Attempt to create an invite with all fields missing
    Given I login with username "student1" and password "password"
    When "student1" creates an invite without specifying who, what, status or date
    Then The response code is 500

# Other Scenarios

  Scenario: Attempt to create an invite without being logged in
    Given I'm not logged in
    When "claudia" creates an invite to user "student1" for proposal "TREBALL FINAL DE GRAU" with status "PENDING" and date "2025-04-06T12:00:00Z"
    Then The response code is 500

  Scenario: Create a valid invite to a user that is not logged in
    Given I login with username "student1" and password "password"
    When "student1" creates an invite to user "claudia" for proposal "TREBALL FINAL DE GRAU" with status "PENDING" and date "2025-04-06T12:00:00Z"
    Then The response code is 500
