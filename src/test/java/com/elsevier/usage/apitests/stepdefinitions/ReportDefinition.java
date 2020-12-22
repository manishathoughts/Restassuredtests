package com.elsevier.usage.apitests.stepdefinitions;

import com.elsevier.usage.apitests.exception.UnexpectedStatusCodeException;
import com.elsevier.usage.apitests.utils.CounterDataServiceUtils;
import com.elsevier.usage.apitests.utils.CsvAndTsvReaderUtils;
import com.elsevier.usage.apitests.utils.JsonReaderUtils;
import com.elsevier.usage.apitests.utils.PropertyResolver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.junit.Assert;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.awaitility.Awaitility.with;

public class ReportDefinition {

    private PropertyResolver pr = new PropertyResolver();
    private RequestSpecification request;
    ConcurrentHashMap<String, Response> responseMap = new ConcurrentHashMap<>();

    @Given("I select the environment on which the execution is to be processed")
    public void selectEnvironment() throws IOException {

        CounterDataServiceUtils.loadProps(PropertyResolver.envResolve());
        RestAssured.baseURI = CounterDataServiceUtils.getProps().getProperty("base.url");
    }

    @And("^I submit a get request with the required request parameters such as (.+),(.+),(.+),(.+),(.+) and (.+)$")
    public void submitRequest(String customerID, String requesterID, String beginDate, String endDate, String platform, String reportFormat) {
        request = given().log().all().queryParam("platform", platform)
                .queryParam("report_format", reportFormat)
                .queryParam("begin_date", beginDate)
                .queryParam("end_date", endDate)
                .queryParam("customer_id", customerID)
                .queryParam("requestor_id", requesterID);

    }

    @And("^I provide the service url (.+) and wait until response is 200$")
    public void serviceURLandLimit(String reportID) throws InterruptedException {
        if (!isExpectedResponseCode(200, reportID)) {
            with().pollInterval(15, TimeUnit.SECONDS).await().atMost(5, TimeUnit.MINUTES).
                    until(() -> isExpectedResponseCode(200, reportID));
        }
    }

    private Boolean isExpectedResponseCode(int expectedCode, String reportID) {
        Response response = request.when().get("/reports/" + reportID + "");
        System.out.println("------------->" + response.getStatusCode());
        responseMap.put("response", response);

        if (response.getStatusCode() != 200 && response.getStatusCode() != 202) {
            throw new UnexpectedStatusCodeException("Bad response code: " + response.getStatusCode());
        }
        return response.getStatusCode() == expectedCode;
    }

    @Then("^I compare the actual json output sent by the service with expected json output file present in expected-content directory \"([^\"]*)\".$")
    public void iCompareJsonFiles(String path) throws IOException, JSONException {

        JSONAssert.assertEquals(JsonReaderUtils.readJson(path), responseMap.get("response").asString(),
                new CustomComparator(JSONCompareMode.LENIENT,
                        new Customization("Report_Header.Created", (o1, o2) -> true)));
        System.out.println("Expected content is matching with Actual content");
    }


    @Then("I compare the actual CSV output sent by the service with expected CSV output file present in expected-content directory {string}.")
    public void iCompareCSVFiles(String path) throws IOException {
        CsvAndTsvReaderUtils.csvAndTsvReader(responseMap.get("response").asString(), CsvAndTsvReaderUtils.CSV_DELIMITER,path);
        System.out.println("-------------------------------------------------------------*******************************");
        assertResponses(CsvAndTsvReaderUtils.CSV_DELIMITER, path);
        System.out.println("Expected content is matching with Actual content");
    }

    private void assertResponses(String delimiter, String path) throws IOException {
        List<List<String>> actual = CsvAndTsvReaderUtils.csvAndTsvReader(responseMap.get("response").asString(), delimiter,path);
        List<List<String>> expected = CsvAndTsvReaderUtils.csvAndTsvReader(null, delimiter,path);

        expected.forEach((expRow) -> {
            Assert.assertTrue("Expected row: "+expRow+" is not present in actual resopnse.",actual.contains(expRow));
        });
    }

    @Then("I compare the actual TSV output sent by the service with expected TSV output file present in expected-content directory {string}.")
    public void iCompareTSVFiles(String path) throws IOException {
        CsvAndTsvReaderUtils.csvAndTsvReader(responseMap.get("response").asString(), CsvAndTsvReaderUtils.TSV_DELIMITER,path);
        System.out.println("-------------------------------------------------------------*******************************");
        assertResponses(CsvAndTsvReaderUtils.TSV_DELIMITER, path);
        System.out.println("Expected content is matching with Actual content");
    }
}
