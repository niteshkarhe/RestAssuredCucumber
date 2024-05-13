@SuiteTearDown
Feature: This feature file has all Users api request

@SaveUser
Scenario Outline: Verify that the candidate questionaeer data is saved in the database
Given user endpoint is run to store candidate questioneer data "<request_num>"
Then verify that the response is received successfully
Examples:
	|request_num|
	|0|

@GetUser
Scenario: Verify that all user questionaeers are received successfully
Given user endpoint is run to get the details of all user question responses
Then verify that the received user details are correct
