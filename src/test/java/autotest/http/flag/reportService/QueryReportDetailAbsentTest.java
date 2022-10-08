package autotest.http.flag.reportService;

import autotest.http.flag.FlagTestBase;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class QueryReportDetailAbsentTest extends FlagTestBase {

    @Test(description = "查询不存在的flag")
    void test2() {
        Reporter.log("调用接口");
        String result = auto.http.get("report/queryReportDetail?flagId=123123");

        Reporter.log("验证结果");
        String message = auto.util.getFirstValue("message", result);
        auto.assertion.isEquals(message, "flag不存在");
//        Assert.assertEquals(message, "flag不存在", "验证异常信息");
    }
}
