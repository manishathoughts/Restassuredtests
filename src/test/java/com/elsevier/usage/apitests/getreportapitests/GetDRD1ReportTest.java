package com.elsevier.usage.apitests.getreportapitests;

import com.elsevier.usage.apitests.utils.CounterDataServiceUtils;
import com.elsevier.usage.apitests.utils.JsonReaderUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONException;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class GetDRD1ReportTest {

    public static void main(String[] args) throws IOException, InterruptedException, JSONException {

        if (args.length == 0) {
            CounterDataServiceUtils.loadProps("nonprod");
        } else {
            CounterDataServiceUtils.loadProps(args[0]);
        }

        RestAssured.baseURI = "https://counterdatasvc.usage.elsst.com";
        Response response = submitReportRequest();
        String  projectPath1 = System.getProperty("user.dir");



       String filePath="/Users/sistum/usage-projects/restassuredpractice/src/test/expected-content/dr_d1_ev_json.json";

        JSONAssert.assertEquals(JsonReaderUtils.readJson(filePath), response.asString(),
                new CustomComparator(JSONCompareMode.LENIENT,
                        new Customization("Report_Header.Created", (o1, o2) -> true)));
    }

    private static Response submitReportRequest() throws InterruptedException {
        Response response=given().log().all().queryParam("platform", "ev")
                .queryParam("report_format", "json")
                .queryParam("begin_date", "2017-01-01")
                .queryParam("end_date", "2017-12-31")
                .queryParam("customer_id", "C000036298")
                .queryParam("requestor_id", "counter")
                .when().get("/reports/dr_d1");//.then().statusCode(200);
            if (response.getStatusCode() == 200) {
                System.out.println("status code is 200 so returning back the response.\n" + response.asString());
                return response;
            } else if (response.getStatusCode() == 202) {
                System.out.println("status code: " + response.getStatusCode() + " so sleeping for 60 sec.\n" + response.asString());
                Thread.sleep(60000);
                return submitReportRequest();
            }
            else{
                System.out.println("un expected error"+ response.getStatusCode());
            }

        return response;

    }
}
