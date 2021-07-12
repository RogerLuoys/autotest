package testCaseFlag.flagBindService;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import testBase.flag.FlagTestBase;

public class QueryReportListTest extends FlagTestBase {

    private final String FullURL = auto.config.URL + "flagBind/queryReportList";

    @Test(description = "正常查询成长日记列表")
    void test1() {
        Reporter.log("调用接口");
        String result = auto.http.get(FullURL + "?ownerId=416176799148282&status=3");

        Reporter.log("验证结果");
        String data = auto.jsonUtil.getBaseData(result, "data");
        Assert.assertTrue(data.contains("自动化queryReportList测试"), "校验对应的flag是否存在");
    }
}
