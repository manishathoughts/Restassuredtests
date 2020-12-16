Feature: Perform functional testing around the actual contents of the reports that the service generates.

@integration-tests
  Scenario Outline: Submitting get request for <report_id> json formatted end point and validating expected output data is matching with actual data for a scenario:<scenarioName>
    Given I select the environment on which the execution is to be processed
    When I provide all the request parameter such as <customer_id>,<requestor_id>,<begin_date>,<end_date>,<platform> and <report_format>
    And I submit a get request for the <report_id> and wait till the response code is 200 and I compare the actual output file created in application with expected output file present in expected-content directory "src/test/expected-content/<scenarioName>".
    Examples:
      | report_id | scenarioName       |customer_id|requestor_id|begin_date|end_date  |platform|report_format|
      | dr_d1     | response.json      |s000000002 |counter     |2019-06-01|2019-12-31|sc      |json         |
