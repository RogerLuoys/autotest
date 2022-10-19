package autotest.http.express.query;

import autotest.http.express.ExpressTestBase;
import org.testng.annotations.Test;

public class QueryTest extends ExpressTestBase {

    @Test(description = "查询不存在的快递")
    public void test() {
        String result = auto.http.get("/query?type=yuantong&postid=11111111111");
        String nu = auto.util.getFirstValue("nu", result);
        auto.assertion.isEquals(nu, "11111111111");
    }

//    @Test
    public void test2() {
        String result = auto.http.get("http://openyqdz.manage-test.sit.91lyd.com/openyqdz/finance/sheetController/selectSystemAccountId?customerId=123", "{\"appKey\":12345}");
        System.out.println(result);
    }
}
