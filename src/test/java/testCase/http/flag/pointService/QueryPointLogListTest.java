package testCase.http.flag.pointService;

import org.testng.Reporter;
import org.testng.annotations.Test;
import testCase.http.flag.FlagTestBase;

public class QueryPointLogListTest extends FlagTestBase {

//    private final String FullURL = URL + "point/queryPointLogList";

    @Test(description = "查询积分使用记录")
    void test1() {
        Reporter.log("调用接口");
        String result = auto.http.get("point/queryPointLogList?pointId=516176799148478&type=2");

        Reporter.log("验证结果");
        String data = auto.util.getJson("data", result);
        auto.assertion.isContains(data, "自动化queryPointLogList测试");
//        Assert.assertTrue(data.contains("自动化queryPointLogList测试"), "校验使用记录是否存在");
    }
}
