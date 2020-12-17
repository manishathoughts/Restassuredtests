package com.elsevier.usage.apitests.getreportapitests;

import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

public class JsonPractice {
@Test
public  void leniantAndStrictcheck() throws JSONException {

       /* String actual = "{id:123,name:\"John\"}";
        JSONAssert.assertNotEquals(
                "{name:\"John\"}", actual, JSONCompareMode.STRICT);*/

    String actual = "{id:123,name:\"John\"}";
    JSONAssert.assertEquals(
            "{name:\"John\"}", actual, JSONCompareMode.LENIENT);

    }

    @Test
    public void maxium(){

        int maximum=3;
        int count =0;
        for(int i=0;i<maximum;i++)
        {
            count= count+1;

            if(maximum<=count){
                System.out.println("Exceeded maximum retry count");
                System.out.println(1);
                break;
            }
            System.out.println("------"+count);
        }
    }

   /* @Test
    public void updatesCustomerStatus() {
        // Publish an asynchronous message to a broker (e.g. RabbitMQ):

        // Awaitility lets you wait until the asynchronous operation completes:
        await().atMost(5, SECONDS).until(customerStatusIsUpdated());
    ...
    }*/
}
