package testCase.http.flag.flagBindService;

import org.testng.Reporter;
import org.testng.annotations.Test;
import testCase.http.flag.FlagTestBase;

public class QueryReportListTest extends FlagTestBase {

    @Test(description = "正常查询成长日记列表")
    void test1() {
        Reporter.log("调用接口");
        String result = auto.http.get("flagBind/queryReportList?ownerId=416176799148282&status=3");

        Reporter.log("验证结果");
        String data = auto.util.getJson("data", result);
        auto.assertion.isContains(data, "自动化queryReportList测试");
//        Assert.assertTrue(data.contains("自动化queryReportList测试"), "校验对应的flag是否存在");
    }
}
