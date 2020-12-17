Feature: Perform functional testing around the actual contents of the reports that the service generates.

  @integration-tests
  Scenario Outline: Submitting get request for <report_id> json formatted end point and validating expected output data is matching with actual data for a scenario:<scenarioName>
    Given I select the environment on which the execution is to be processed
    And I submit a get request with the required request parameters such as <customer_id>,<requestor_id>,<begin_date>,<end_date>,<platform> and <report_format>
    And I provide the service url <report_id> and wait until response is 200
    Then I compare the actual output sent by service with expected output file present in expected-content directory "src/test/expected-content/<scenarioName>".
    Examples:
      | report_id | scenarioName       | customer_id | requestor_id | begin_date | end_date   | platform | report_format |
      | dr_d1     | dr_d1_ev_json.json | C000036298  | counter      | 2017-01-01 | 2017-12-31 | ev       | json          |
      | dr_d1     | dr_d1_sc_json.json | C000036298  | counter      | 2016-01-01 | 2016-12-31 | sc       | json          |
      | pr_p1     | pr_p1_sc_json.json | C000036298  | counter      | 2016-01-01 | 2016-12-31 | sc       | json          |
      | pr_p1     | pr_p1_ev_json.json | C000036298  | counter      | 2016-01-01 | 2016-12-31 | ev       | json          |
      | pr_p1     | pr_p1_sd_json.json | C000036298  | counter      | 2016-01-01 | 2016-12-31 | sd       | json          |

