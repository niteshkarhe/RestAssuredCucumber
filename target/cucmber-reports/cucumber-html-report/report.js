$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/GetRequestFeatures/AllPostMethodScenarios.feature");
formatter.feature({
  "name": "This feature file has all Get requests",
  "description": "",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@EnvironmentSetup"
    }
  ]
});
formatter.scenarioOutline({
  "name": "Verify post request with multiple test data and with body content set in method",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@SmokePost"
    }
  ]
});
formatter.step({
  "name": "user runs \"request.postWithBodyContent\" with data \"\u003crow_index\u003e\"",
  "keyword": "Given "
});
formatter.step({
  "name": "response should have status code \u0027200\u0027",
  "keyword": "Then "
});
formatter.step({
  "name": "response should have status \"OK\"",
  "keyword": "Then "
});
formatter.step({
  "name": "get the response body",
  "keyword": "Then "
});
formatter.step({
  "name": "verify the brandname of requested id",
  "keyword": "Then "
});
formatter.step({
  "name": "verify \"Feature\" at jsonpath \"Features.Feature\"",
  "keyword": "Then "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "row_index"
      ]
    },
    {
      "cells": [
        "0"
      ]
    },
    {
      "cells": [
        "1"
      ]
    }
  ]
});
formatter.scenario({
  "name": "Verify post request with multiple test data and with body content set in method",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@EnvironmentSetup"
    },
    {
      "name": "@SmokePost"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.beforestep({
  "status": "passed"
});
formatter.step({
  "name": "user runs \"request.postWithBodyContent\" with data \"0\"",
  "keyword": "Given "
});
formatter.match({
  "location": "CommonSteps.user_runs_with_data(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.beforestep({
  "status": "passed"
});
formatter.step({
  "name": "response should have status code \u0027200\u0027",
  "keyword": "Then "
});
formatter.match({
  "location": "CommonSteps.response_should_have_status(int)"
});
formatter.result({
  "status": "passed"
});
formatter.beforestep({
  "status": "passed"
});
formatter.step({
  "name": "response should have status \"OK\"",
  "keyword": "Then "
});
formatter.match({
  "location": "CommonSteps.verify_response_has(String)"
});
formatter.result({
  "status": "passed"
});
formatter.beforestep({
  "status": "passed"
});
formatter.step({
  "name": "get the response body",
  "keyword": "Then "
});
formatter.match({
  "location": "CommonSteps.get_the_response_body()"
});
formatter.result({
  "status": "passed"
});
formatter.beforestep({
  "status": "passed"
});
formatter.step({
  "name": "verify the brandname of requested id",
  "keyword": "Then "
});
formatter.match({
  "location": "CommonSteps.verify_the_brandname_of_requested_id()"
});
formatter.result({
  "status": "passed"
});
formatter.beforestep({
  "status": "passed"
});
formatter.step({
  "name": "verify \"Feature\" at jsonpath \"Features.Feature\"",
  "keyword": "Then "
});
formatter.match({
  "location": "CommonSteps.verify_at_jsonpath(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Verify post request with multiple test data and with body content set in method",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@EnvironmentSetup"
    },
    {
      "name": "@SmokePost"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.beforestep({
  "status": "passed"
});
formatter.step({
  "name": "user runs \"request.postWithBodyContent\" with data \"1\"",
  "keyword": "Given "
});
formatter.match({
  "location": "CommonSteps.user_runs_with_data(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.beforestep({
  "status": "passed"
});
formatter.step({
  "name": "response should have status code \u0027200\u0027",
  "keyword": "Then "
});
formatter.match({
  "location": "CommonSteps.response_should_have_status(int)"
});
formatter.result({
  "status": "passed"
});
formatter.beforestep({
  "status": "passed"
});
formatter.step({
  "name": "response should have status \"OK\"",
  "keyword": "Then "
});
formatter.match({
  "location": "CommonSteps.verify_response_has(String)"
});
formatter.result({
  "status": "passed"
});
formatter.beforestep({
  "status": "passed"
});
formatter.step({
  "name": "get the response body",
  "keyword": "Then "
});
formatter.match({
  "location": "CommonSteps.get_the_response_body()"
});
formatter.result({
  "status": "passed"
});
formatter.beforestep({
  "status": "passed"
});
formatter.step({
  "name": "verify the brandname of requested id",
  "keyword": "Then "
});
formatter.match({
  "location": "CommonSteps.verify_the_brandname_of_requested_id()"
});
formatter.result({
  "status": "passed"
});
formatter.beforestep({
  "status": "passed"
});
formatter.step({
  "name": "verify \"Feature\" at jsonpath \"Features.Feature\"",
  "keyword": "Then "
});
formatter.match({
  "location": "CommonSteps.verify_at_jsonpath(String,String)"
});
formatter.result({
  "status": "passed"
});
});