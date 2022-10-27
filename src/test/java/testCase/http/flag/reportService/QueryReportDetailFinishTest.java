package testCase.http.flag.reportService;

import testCase.http.flag.FlagTestBase;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class QueryReportDetailFinishTest extends FlagTestBase {

    @Test(description = "查询正常已结束的flag")
    void test1() {
        Reporter.log("调用接口");
        String result = auto.http.get("report/queryReportDetail?flagId=116177766224670");

        Reporter.log("验证结果");
        String flagName = auto.util.getFirstValue("flagName", result);
        String taskDailyId = auto.util.getFirstValue("taskDailyId", result);
        auto.assertion.isEquals(flagName, "自动化queryReportDetail测试");
        auto.assertion.isEquals(taskDailyId, "316177767278125");
//        Assert.assertEquals(flagName, "自动化queryReportDetail测试", "验证基本信息");
//        Assert.assertEquals(taskDailyId, "316177767278125", "验证已完成的任务");
    }

}
