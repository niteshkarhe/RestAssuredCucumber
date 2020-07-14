@EnvironmentSetup
Feature: This feature file has all Get requests

@SmokePut
Scenario: Verify simple put request with body content set in method
Given user runs "request.putWithBodyContent"
Then response should have status code '200'
Then response should have status "OK"
Then get the response body

@SmokePost1
Scenario Outline: Verify post request with multiple test data and with body content set in method
Given user runs "request.postWithBodyContent" with data "<row_index>"
Then response should have status code '200'
Then response should have status "OK"
Then get the response body
Then verify the brandname of requested id
Then verify "Feature" at jsonpath "Features.Feature"
	Examples:
	|row_index|
	|0|
	|1|

@wSmoke
Scenario: Verify get request to find brandname with given id
Given user runs "request.findXMLContentType"
Then response should have status code '200'
Then response should have status "OK"
Then get the response body
Then verify the brandname of requested id
Then verify "Feature" at xmlpath "Laptop.Features.Feature"

@Smoke2
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

@Smoke1
Scenario: Verify Ping request is run successfully
Given user runs "request.ping"
#Then verify response has '200'
#Then verify response has "OK"