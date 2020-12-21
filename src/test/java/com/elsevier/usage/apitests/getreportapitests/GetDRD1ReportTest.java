package com.elsevier.usage.apitests.getreportapitests;


import com.elsevier.usage.apitests.exception.UnexpectedStatusCodeException;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.junit.Assert;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetDRD1ReportTest {

    public static void main(String[] args) throws IOException, InterruptedException, JSONException {

       /* if (args.length == 0) {
            CounterDataServiceUtils.loadProps("nonprod");
        } else {
            CounterDataServiceUtils.loadProps(args[0]);
        }*/

        RestAssured.baseURI = "https://counterdatasvc.usage.elsst.com";
        Response response = submitReportRequest();
        System.out.println("------------------------>"+response.asString());
        String path="/Users/sistum/usage-projects/restassuredpractice/src/test/expected-content/dr_d1_ev_tsv.tsv";
       CsvAndTsvReaderUtils.csvAndTsvReader(response.asString(), CsvAndTsvReaderUtils.TSV_DELIMITER,path);

      /*  String path="/Users/sistum/usage-projects/restassuredpractice/src/test/expected-content/dr_d1_ev_csv.csv";

        JSONAssert.assertEquals(JsonReaderUtils.readJson(path), response.asString(),
                new CustomComparator(JSONCompareMode.LENIENT,
                        new Customization("Report_Header.Created", (o1, o2) -> true)));
        System.out.println("Expected content is matching with Actual content");*/

        System.out.println("-------------------------------------------------------------*******************************");
        List<List<String>> actual = CsvAndTsvReaderUtils.csvAndTsvReader(response.asString(), CsvAndTsvReaderUtils.TSV_DELIMITER,path);
        List<List<String>> expected = CsvAndTsvReaderUtils.csvAndTsvReader(null, CsvAndTsvReaderUtils.TSV_DELIMITER,path);

        actual.forEach((row) -> expected.forEach((expRow) -> {
            if(!row.isEmpty() && !expRow.isEmpty()) {
                if(row.get(0).equals(expRow.get(0))) {
                    Assert.assertEquals(row, expRow);
                }
            }
        }));
        Assert.assertEquals(expected, actual);
    }

    private static Response submitReportRequest() throws InterruptedException {
        RequestSpecification request=given().log().all().queryParam("platform", "ev")
                .queryParam("report_format", "tsv")
                .queryParam("begin_date", "2016-01-01")
                .queryParam("end_date", "2016-12-31")
                .queryParam("customer_id", "C000036298")
                .queryParam("requestor_id", "counter");


           Response response=request.when().get("/reports/dr_d1");//.then().statusCode(200);
            if (response.getStatusCode() == 200) {
                System.out.println("status code is 200 so returning back the response.\n" + response.asString());
                return response;
            } else if (response.getStatusCode() == 202) {
                System.out.println("status code: " + response.getStatusCode() + " so sleeping for 60 sec.\n" + response.asString());
               // Thread.sleep(60000);
                return submitReportRequest();
            } else {
                throw new UnexpectedStatusCodeException("Exception while executing the get request");
            }
        }
}
