package testCase.http.flag.templateService;

import org.testng.Reporter;
import org.testng.annotations.Test;
import testCase.http.flag.FlagTestBase;

public class QueryFlagTemplateDetailTest extends FlagTestBase {

//    private final String FullURL = config.Config.URL + "template/queryFlagTemplateDetail";

    @Test
    void test1() {
        Reporter.log("调用接口");
        String result = auto.http.get("template/queryFlagTemplateDetail?flagTemplateId=1001");

        Reporter.log("验证数据");
        String value1 = auto.util.getJsonAny("expected", result);
        String value2 = auto.util.getJsonAny("taskName", result);
        auto.assertion.isEquals(value1, "只要走出去就算收获");
        auto.assertion.isEquals(value2, "不睡懒觉");

    }
}
