@EnvironmentSetup
Feature: This feature file has all Get requests

@SmokePost
Scenario: Verify simple post request with body content set in method
Given user runs "request.postWithBodyContent"
Then response should have status code '200'
Then response should have status "OK"
Then get the response body

@Smoke
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