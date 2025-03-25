Feature: Create Interest
    In order to show interest
    As a User
    I want to create an invite

    
    Background:
        Given There is a registered user with username "user" and password "password" and email "user@alumnes.udl.cat"
        Given There is a proposal with id 1


    Scenario: Create an interest being logged in
        Given I'm logged in
        And Don't exist Interest with user "user" and id 1
        Then The response code is 201
        And There is 1 Interest created with user "user" and proposal id 1
    

    Scenario: Create an interest without being logged in
        Given I'm not logged in
        When I create an interest with user "user" and proposal id 1
        Then The response code is 401
        And The error message is "Unauthorized"
        And There is 0 Interest created with user "user" and proposal id 1

    
    Scenario: Create an interest that already exists
        Given Exists an interest with user "user" and proposal id 1
        When I create an interest with user "user" and proposal id 1
        Then The response code is 401
        And The error message is "Unauthorized"
        And There is 1 Interest created with user "user" and proposal id 1