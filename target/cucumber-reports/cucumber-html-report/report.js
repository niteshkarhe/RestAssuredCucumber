$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/RequestAndUIFeatures/GetRequestAndUIScenario.feature");
formatter.feature({
  "name": "This feature file has all Get requests",
  "description": "",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@EnvironmentSetup"
    },
    {
      "name": "@Teardown"
    }
  ]
});
formatter.scenario({
  "name": "Verify simple get request without body",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@EnvironmentSetup"
    },
    {
      "name": "@Teardown"
    },
    {
      "name": "@UI"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "user runs \"request.ping\"",
  "keyword": "Given "
});
formatter.match({
  "location": "CommonSteps.user_runs(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "user launches browser with url",
  "keyword": "When "
});
formatter.match({
  "location": "CommonSteps.user_launches_browser_with_url()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "user logs into application",
  "keyword": "Then "
});
formatter.match({
  "location": "CommonSteps.user_logs_into_application()"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
formatter.scenario({
  "name": "Verify simple get request without body",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@EnvironmentSetup"
    },
    {
      "name": "@Teardown"
    },
    {
      "name": "@UI"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "user runs \"request.ping\"",
  "keyword": "Given "
});
formatter.match({
  "location": "CommonSteps.user_runs(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "user launches browser with url",
  "keyword": "When "
});
formatter.match({
  "location": "CommonSteps.user_launches_browser_with_url()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "user logs into application",
  "keyword": "Then "
});
formatter.match({
  "location": "CommonSteps.user_logs_into_application()"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});