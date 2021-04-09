package testCaseFlag.reportService;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import testBase.flag.FlagTestBase;

public class QueryReportDetailTest extends FlagTestBase {

    @Test(description = "查询正常已结束的flag")
    void test1() {
        Reporter.log("调用接口");
        String result = auto.http.get(URL + "report/queryReportDetail?flagId=116177766224670");

        Reporter.log("验证结果");
        String flagName = auto.jsonUtil.getData(result, "flagName");
        String taskDailyId = auto.jsonUtil.getData(result, "taskDailyId");
        Assert.assertEquals(flagName, "自动化queryReportDetail测试", "验证基本信息");
        Assert.assertEquals(taskDailyId, "316177767278125", "验证已完成的任务");
    }

    @Test(description = "查询不存在的flag")
    void test2() {
        Reporter.log("调用接口");
        String result = auto.http.get(URL + "report/queryReportDetail?flagId=123123");

        Reporter.log("验证结果");
        String message = auto.jsonUtil.getData(result, "message");
        Assert.assertEquals(message, "flag不存在", "验证异常信息");
    }
}
