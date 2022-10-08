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
}
