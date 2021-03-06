package demo.testCase;

import demo.testBase.TestBase;
import org.testng.annotations.Test;

public class APITestCase extends TestBase {

    @Test
    void testCase1(){
        auto.http.doGet("");
    }
}
