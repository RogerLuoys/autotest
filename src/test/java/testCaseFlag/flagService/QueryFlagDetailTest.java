package testCaseFlag.flagService;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import testBase.flag.FlagTestBase;

public class QueryFlagDetailTest extends FlagTestBase {

    private final String FullURL = URL + "flag/queryFlagDetail";

    @Test(description = "查询包含周期任务的正常flag详情")
    void test1() {
        Reporter.log("调用查询接口");
        String result = auto.http.get(FullURL + "?flagId=116176887323460");

        Reporter.log("验证返回值");
        String flagName = auto.jsonUtil.getData(result, "flagName");
        String taskName = auto.jsonUtil.getData(result, "taskName");
        Assert.assertEquals(flagName, "自动化queryFlagDetail测试", "验证基本信息");
        Assert.assertEquals(taskName, "queryFlagDetail测试任务", "验证周期任务信息");
    }


    @Test(description = "查询不存在的flag详情")
    void test2() {
        Reporter.log("调用查询接口");
        String result = auto.http.get(FullURL + "?flagId=123123");

        Reporter.log("验证返回值");
        String message = auto.jsonUtil.getData(result, "message");
        Assert.assertEquals(message, "未查询到此flag", "验证查询结果");
    }
}
