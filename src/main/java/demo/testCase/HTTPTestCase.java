package demo.testCase;

import demo.testBase.TestBase;
//import org.testng.Assert;
//import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class HTTPTestCase extends TestBase {

//    @Test
    void test1(){
        String result = auto.http.get(HTTP_URL + "api/flag/queryFlagDetail/116149450801523");
//        Assert.assertTrue(result.contains("***"), "******");
    }

//    @Test
    void test2(){
        Map<String, Object> params = new HashMap<>();
        params.put("pageIndex", 1);
        params.put("type", 1);
        String result = auto.http.post(HTTP_URL + "api/flag/queryFlagList", params);
//        Assert.assertTrue(result.contains("***"), "******");
    }

}
