/*package com.elsevier.usage.apitests.stepdefinitions;


import com.elsevier.usage.apitests.utils.CounterDataServiceUtils;
import com.elsevier.usage.apitests.utils.PropertyResolver;

import com.elsevier.usage.apitests.utils.JsonReaderUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;

import java.io.IOException;

import static io.restassured.RestAssured.given;


public class ReportDefinition {

    PropertyResolver pr = new PropertyResolver();
    RequestSpecification request;

    @Given("^I select the environment on which the execution is to be processed$")
    public void i_select_the_environment_on_which_the_execution_is_to_be_processed() throws IOException {
        CounterDataServiceUtils.loadProps(pr.envResolve());
        RestAssured.baseURI = CounterDataServiceUtils.getProps().getProperty("base.url");
    }

    @And("^I submit a get request with the required request parameters such as (.+),(.+),(.+),(.+),(.+) and (.+)$")
    public void i_provide_all_the_request_parameter_such_as_and(String customerid, String requestorid, String begindate, String enddate, String platform, String reportformat) throws Throwable {
         request=given().log().all().queryParam("platform", platform)
                .queryParam("report_format", reportformat)
                .queryParam("begin_date", begindate)
                .queryParam("end_date", enddate)
                .queryParam("customer_id", customerid)
                .queryParam("requestor_id", requestorid);
    }*//*






*/
/*
    @And("^I submit a get request for the (.+) and wait till the response code is 200 and I compare the actual output file created in application with expected output file present in expected-content directory \"([^\"]*)\".$")
    public Response i_submit_a_get_request(String reportid, String strArg1) throws Throwable {
        Response response=request.when().get("/reports/"+reportid+"");
        if (response.getStatusCode() == 200) {
            System.out.println("status code is 200 so returning back the response.\n" + response.asString());
            JSONAssert.assertEquals(JsonReaderUtils.readJson(strArg1), response.asString(),
                    new CustomComparator(JSONCompareMode.LENIENT,
                            new Customization("Report_Header.Created", (o1, o2) -> true)));

            System.out.println("Expected is matching with actual content");
            return response;
        } else if (response.getStatusCode() == 202) {
            //System.out.println("status code: " + response.getStatusCode() + " so sleeping for 50 sec.\n" + response.asString());
            //Thread.sleep(50000);
            int maximum=2;

                    for(int i=0;i<maximum;i++)
                    {

                        count++;
                       i=i+1;

                        if(maximum<=i){
                            System.out.println("Exceeded maximum retry count");
                            System.out.println(1);
                            break;
                        }
                        System.out.println("---------"+i);
                        System.out.println("status code: " + response.getStatusCode() + " so sleeping for 50 sec.\n" + response.asString());

                        return i_submit_a_get_request(reportid,strArg1);
                    }


        } else {
            System.out.println("request has been failed: " + response.getStatusCode());
        }
        return response;
    }


 @And("I submit a get request for the <report_id> and wait till the response code is {int} and I compare the actual output file created in application with expected output file present in expected-content directory {string}.")
    public void iSubmitAGetRequestForTheReport_idAndWaitTillTheResponseCodeIsAndICompareTheActualOutputFileCreatedInApplicationWithExpectedOutputFilePresentInExpectedContentDirectory(int arg0, String arg1) {
    }*//*


}



*/
