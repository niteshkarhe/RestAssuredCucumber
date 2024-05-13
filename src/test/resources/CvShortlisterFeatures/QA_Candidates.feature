@SuiteTearDown
Feature: This feature file has all Candidates api request

@Candidates
Scenario Outline: Verify that the candidate is created in the database
Given candidate endpoint is run to create candidate "<request_num>"
Examples:
	|request_num|
	|0|