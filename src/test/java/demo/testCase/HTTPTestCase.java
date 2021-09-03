package demo.testCase;

import demo.testBase.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class HTTPTestCase extends TestBase {

    private final String FullURL = auto.config.HTTP_URL + "***/query";

    @BeforeClass
    void beforeClass() {
        auto.db.delete("sql");
    }

    @Test
    void test1(){
        String result = auto.http.get(FullURL + "/116149450801523");
        Assert.assertTrue(result.contains("***"), "******");
    }

    @Test
    void test2(){
        Map<String, Object> params = new HashMap<>();
        params.put("pageIndex", 1);
        params.put("type", 1);
        String result = auto.http.post(FullURL, params);
        Assert.assertTrue(result.contains("***"), "******");
    }

    @AfterClass
    void afterClass() {
        auto.db.delete("sql");
    }

}
