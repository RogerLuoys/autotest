package testCaseFlag.pointService;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import testBase.flag.FlagTestBase;

public class QueryPointSummaryTest extends FlagTestBase {

    @Test
    void Test1() {
        Reporter.log("调用接口");
        String result = auto.http.doGet(URL + "point/queryPointSummary?ownerId=416176799148282");

        Reporter.log("验证数据");
        String pointId = auto.jsonUtil.getData(result, "pointId");
        Assert.assertEquals(pointId, "516176799148478", "验证积分总览返回结果");
    }
}
