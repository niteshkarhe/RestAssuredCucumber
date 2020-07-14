@EnvironmentSetup
Feature: This feature file has all Header Authorization

@SmokeAuth1
Scenario: Verify request with header authorization
Given user runs "request.GetRequestWithHeaderAuthorization"
Then response should have status code '200'
Then response should have status "OK"

@SmokeAuth
Scenario: Verify request with restassured basic authorization
Given user runs "request.GetRequestWithBasicAuthorization"
Then response should have status code '200'
Then response should have status "OK"