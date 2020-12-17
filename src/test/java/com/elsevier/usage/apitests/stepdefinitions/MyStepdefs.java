package com.elsevier.usage.apitests.stepdefinitions;
import com.elsevier.usage.apitests.exception.UnexpectedStatusCodeException;
import com.elsevier.usage.apitests.utils.CounterDataServiceUtils;
import com.elsevier.usage.apitests.utils.JsonReaderUtils;
import com.elsevier.usage.apitests.utils.PropertyResolver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import static io.restassured.RestAssured.given;
import static org.awaitility.Awaitility.with;

public class MyStepdefs {

    private PropertyResolver pr = new PropertyResolver();
    private RequestSpecification request;
    ConcurrentHashMap<String, Response> responseMap = new ConcurrentHashMap<>();

    @Given("I select the environment on which the execution is to be processed")
    public void selectEnvironment() throws IOException {
        CounterDataServiceUtils.loadProps(pr.envResolve());
        RestAssured.baseURI = CounterDataServiceUtils.getProps().getProperty("base.url");
    }

    @And("^I submit a get request with the required request parameters such as (.+),(.+),(.+),(.+),(.+) and (.+)$")
    public void submitRequest(String customerid, String requestorid, String begindate, String enddate, String platform, String reportformat) {
        request=given().log().all().queryParam("platform", platform)
                .queryParam("report_format", reportformat)
                .queryParam("begin_date", begindate)
                .queryParam("end_date", enddate)
                .queryParam("customer_id", customerid)
                .queryParam("requestor_id", requestorid);

    }

    @And("^I provide the service url (.+) and wait until response is 200 with \"([^\"]*)\"$")
    public void serviceURLandLimit(String reportid, String strArg1) throws InterruptedException {
        if(!isExpectedResponseCode(200, reportid)) {
            with().pollInterval(15, TimeUnit.SECONDS).await().atMost(5, TimeUnit.MINUTES).
                    until(() -> isExpectedResponseCode(200, reportid));
        }
    }

    private Boolean isExpectedResponseCode(int expectedCode, String reportid) {
        Response response=request.when().get("/reports/"+reportid+"");
        System.out.println("------------->"+response.getStatusCode());

        if(response.getStatusCode() != 200 && response.getStatusCode() != 202) {
            throw new UnexpectedStatusCodeException("Bad response code: "+response.getStatusCode());
        }
        return response.getStatusCode() == expectedCode;
    }

    @Then("I compare the actual output sent by service with expected output file present in expected-content directory {string}.")
    public void compareJsonFiles(String path) {
        try {
            JSONAssert.assertEquals(JsonReaderUtils.readJson(path), responseMap.get("response").asString(),
                    new CustomComparator(JSONCompareMode.LENIENT,
                            new Customization("Report_Header.Created", (o1, o2) -> true)));
            System.out.println("Expected content is matching with Actual content");
        } catch (Exception e) {

            System.out.println("*****************************"+e.fillInStackTrace());

        }


    }


}
