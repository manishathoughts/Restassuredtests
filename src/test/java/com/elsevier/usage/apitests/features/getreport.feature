Feature: Perform functional testing around the actual contents of the reports that the service generates.


  Scenario Outline: Submitting get request to the counter data service REST API
    Given I select the environment on which the execution is to be processed
    And I submit a get request with the required request parameters such as <customer_id>,<requestor_id>,<begin_date>,<end_date>,<platform> and <report_format>
    And I provide the service url <report_id> and wait until response is 200
    Examples:
      | report_id | customer_id | requestor_id | begin_date | end_date   | platform | report_format |
      | dr_d1     | C000036298  | counter      | 2017-01-01 | 2017-12-31 | ev       | json          |
      | dr_d1     | C000036298  | counter      | 2016-01-01 | 2016-12-31 | sc       | json          |
      | pr_p1     | C000036298  | counter      | 2016-01-01 | 2016-12-31 | sc       | json          |
      | pr_p1     | C000036298  | counter      | 2016-01-01 | 2016-12-31 | ev       | json          |
      | pr_p1     | C000036298  | counter      | 2016-01-01 | 2016-12-31 | sd       | json          |


  Scenario Outline: Comparing actual and expected contents of  Json formatted files

    Then I compare the actual json output sent by service with expected json output file present in expected-content directory "src/test/expected-content/<scenarioName>".
    Examples:
      | scenarioName       |
      | dr_d1_ev_json.json |

  Scenario Outline: Comparing actual and expected contents of CSV formatted files

    Then I compare the actual CSV output sent by service with expected CSV output file present in expected-csv-content directory "src/test/expected-content/<scenarioName>".
    Examples:
      | scenarioName       |
      | dr_d1_ev_json.json |

  Scenario Outline: Comparing actual and expected contents of TSV formatted files

    Then I compare the actual TSV output sent by service with expected TSV output file present in expected-tsv-content directory "src/test/expected-content/<scenarioName>".
    Examples:
      | scenarioName       |
      | dr_d1_ev_json.json |
