package testCaseFlag.flagBindService;

import com.luoys.upgrade.flag.api.bo.FlagQueryBO;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import testBase.flag.FlagTestBase;

public class QueryFlagListTest extends FlagTestBase {

    private final String FullURL = URL + "flagBind/queryFlagList";

    @Test(description = "正常查看列表")
    void test1() {
        Reporter.log("准备入参");
        FlagQueryBO flagQueryBO = new FlagQueryBO();
        flagQueryBO.setPageIndex(1);
        flagQueryBO.setOwnerId("416176799148282");

        Reporter.log("调用接口");
        String result = auto.http.post(FullURL, flagQueryBO);

        Reporter.log("验证结果");
        int total = Integer.parseInt(auto.jsonUtil.getData(result, "total"));
        Assert.assertTrue(total >= 1, "验证列表总数据量");
    }

    @Test(description = "查看尾页")
    void test2() {
        Reporter.log("准备入参");
        FlagQueryBO flagQueryBO = new FlagQueryBO();
        flagQueryBO.setPageIndex(100);
        flagQueryBO.setOwnerId("416176799148282");

        Reporter.log("调用接口");
        String result = auto.http.post(FullURL, flagQueryBO);

        Reporter.log("验证结果");
        int total = Integer.parseInt(auto.jsonUtil.getData(result, "total"));
        Assert.assertEquals(total, 0, "验证列表总数据量");
    }
}
