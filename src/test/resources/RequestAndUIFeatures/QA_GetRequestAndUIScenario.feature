@UIOneTimeSetup
Feature: This feature file has all Get requests

@SmokeUI @UISetup @UITeardown
Scenario: Verify simple get request without body
#Given user runs "request.ping"
#Then response should have status code '200'
#Then response should have status "OK"
#Then get the response body
When user launches browser with url
Then user logs into application

@UI @UISetup @UITeardown
Scenario: Verify simple get request without body
Given user runs "request.ping"
#Then response should have status code '200'
#Then response should have status "OK"
#Then get the response body
When user launches browser with url
Then user logs into application

@UI @UISetup @UITeardown
Scenario Outline: Verify Ping request is run successfully
Given user runs "request.ping" with data "<row_index>"
Then response should have status code '200'
Then response should have status "OK"
Then get the response body
	Examples:
	|row_index|
	|0|
	|1|
	|2|
	|3|
	|4|
	|5|
	|6|