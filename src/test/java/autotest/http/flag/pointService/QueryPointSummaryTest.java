package autotest.http.flag.pointService;

import org.testng.Reporter;
import org.testng.annotations.Test;
import autotest.http.flag.FlagTestBase;

public class QueryPointSummaryTest extends FlagTestBase {

//    private final String FullURL = config.Config.URL + "point/queryPointSummary";

    @Test
    void test1() {
        Reporter.log("调用接口");
        String result = auto.http.get("point/queryPointSummary?ownerId=416176799148282");

        Reporter.log("验证数据");
        String pointId = auto.util.getJsonValue("pointId", result);
        auto.assertion.isEquals(pointId, "516176799148478");
//        Assert.assertEquals(pointId, "516176799148478", "验证积分总览返回结果");
    }
}
