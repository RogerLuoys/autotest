package autotest.http.flag.templateService;

import org.testng.Reporter;
import org.testng.annotations.Test;
import autotest.http.flag.FlagTestBase;

public class QueryFlagTemplateListTest extends FlagTestBase {

//    private final String FullURL = config.Config.URL + "template/queryFlagTemplateList";

    @Test
    void test1() {
        Reporter.log("调用接口");
        String result = auto.http.get("template/queryFlagTemplateList?ownerId=1");

        Reporter.log("验证数据");
        String data = auto.util.getJsonValue("data", result);
        auto.assertion.isContains(data, "只要走出去就算收获");
    }
}
