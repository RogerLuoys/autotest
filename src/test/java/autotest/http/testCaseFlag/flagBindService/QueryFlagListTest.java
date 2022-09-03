package autotest.http.testCaseFlag.flagBindService;

import org.testng.Reporter;
import org.testng.annotations.Test;
import autotest.http.testCaseFlag.FlagTestBase;

public class QueryFlagListTest extends FlagTestBase {

//    private final String FullURL = auto.config.URL + "flagBind/queryFlagList";
//
//    @Test(description = "正常查看列表")
//    void test1() {
//        Reporter.log("准备入参");
//        FlagQueryBO flagQueryBO = new FlagQueryBO();
//        flagQueryBO.setPageIndex(1);
//        flagQueryBO.setOwnerId("416176799148282");
//
//        Reporter.log("调用接口");
//        String result = auto.http.post(FullURL, flagQueryBO);
//
//        Reporter.log("验证结果");
//        int total = Integer.parseInt(auto.jsonUtil.getData(result, "total"));
//        Assert.assertTrue(total >= 1, "验证列表总数据量");
//    }

    @Test(description = "正常查看列表")
    void test() {
        Reporter.log("调用接口");
        String result = auto.http.post("flagBind/queryFlagList", "{\"pageIndex\":1,\"ownerId\":\"416176799148282\"}");

        Reporter.log("验证结果");
        String total = auto.util.getFirstValue("total", result);
        auto.assertion.isGreater(total, "0");
    }

}
