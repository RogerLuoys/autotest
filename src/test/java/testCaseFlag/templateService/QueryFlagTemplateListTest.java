package testCaseFlag.templateService;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import testBase.flag.FlagTestBase;

public class QueryFlagTemplateListTest extends FlagTestBase {

    private final String FullURL = URL + "template/queryFlagTemplateList";

    @Test
    void test1() {
        Reporter.log("调用接口");
        String result = auto.http.get(FullURL + "?ownerId=1");

        Reporter.log("验证数据");
        String data = auto.jsonUtil.getBaseData(result, "data");
        Assert.assertTrue(data.contains("只要走出去就算收获"), "校验模板任意信息");
    }
}
