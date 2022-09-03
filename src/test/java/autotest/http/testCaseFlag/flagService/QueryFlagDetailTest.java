package autotest.http.testCaseFlag.flagService;

import org.testng.annotations.Test;
import autotest.http.testCaseFlag.FlagTestBase;

public class QueryFlagDetailTest extends FlagTestBase {

//    private final String FullURL = auto.config.URL + "flag/queryFlagDetail";
//
//    @Test(description = "查询包含周期任务的正常flag详情")
//    void test1() {
//        Reporter.log("调用查询接口");
//        String result = auto.http.get(FullURL + "?flagId=116176887323460");
//
//        Reporter.log("验证返回值");
//        String flagName = auto.jsonUtil.getData(result, "flagName");
//        String taskName = auto.jsonUtil.getData(result, "taskName");
//        Assert.assertEquals(flagName, "自动化queryFlagDetail测试", "验证基本信息");
//        Assert.assertEquals(taskName, "queryFlagDetail测试任务", "验证周期任务信息");
//    }

    @Test(description = "查询包含周期任务的正常flag详情")
    void test() {
        // 调用查询接口
        String result = auto.http.get("flag/queryFlagDetail?flagId=116176887323460");

        // 验证返回值
        String flagName = auto.util.getFirstValue("flagName", result);
        String taskName = auto.util.getFirstValue("taskName", result);
        auto.assertion.isEquals(flagName, "自动化queryFlagDetail测试");
        auto.assertion.isEquals(taskName, "queryFlagDetail测试任务");

    }


}
