package testCaseFlag.templateService;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import testBase.flag.FlagTestBase;

public class QueryFlagTemplateDetailTest extends FlagTestBase {

    private final String FullURL = auto.config.URL + "template/queryFlagTemplateDetail";

    @Test
    void test1() {
        Reporter.log("调用接口");
        String result = auto.http.get(FullURL + "?flagTemplateId=1001");

        Reporter.log("验证数据");
        String expected = auto.jsonUtil.getData(result, "expected");
        String taskName = auto.jsonUtil.getData(result, "taskName");
        Assert.assertEquals(expected, "只要走出去就算收获", "校验模板任一基本信息");
        Assert.assertEquals(taskName, "不睡懒觉", "校验模板任一周期任务信息");
    }
}
