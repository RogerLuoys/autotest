package demo.testCase;

import demo.testBase.TestBase;
import org.testng.annotations.Test;

public class APITestCase extends TestBase {

    @Test
    void testCase1(){
        String result = auto.http.doGet("http://118.24.117.181:9001/api/flag/queryFlagDetail/116149450801523");
        System.out.println(result);
    }

}
