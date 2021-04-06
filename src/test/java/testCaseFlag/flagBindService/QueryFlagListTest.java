package testCaseFlag.flagBindService;

import com.luoys.upgrade.flag.api.bo.FlagQueryBO;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import testBase.flag.FlagTestBase;

public class QueryFlagListTest extends FlagTestBase {

    @Test(description = "正常查看列表")
    void Test1() {
        Reporter.log("准备入参");
        FlagQueryBO flagQueryBO = new FlagQueryBO();
        flagQueryBO.setPageIndex(1);
        flagQueryBO.setOwnerId("416176799148282");

        Reporter.log("调用接口");
        String result = auto.http.doPost(URL + "flagBind/queryFlagList", flagQueryBO);

        Reporter.log("验证结果");
        int total = Integer.parseInt(auto.jsonUtil.getData(result, "total"));
        Assert.assertTrue(total >= 1, "验证列表总数据量");
    }

    @Test(description = "查看尾页")
    void Test2() {
        Reporter.log("准备入参");
        FlagQueryBO flagQueryBO = new FlagQueryBO();
        flagQueryBO.setPageIndex(100);
        flagQueryBO.setOwnerId("416176799148282");

        Reporter.log("调用接口");
        String result = auto.http.doPost(URL + "flagBind/queryFlagList", flagQueryBO);

        Reporter.log("验证结果");
        int total = Integer.parseInt(auto.jsonUtil.getData(result, "total"));
        Assert.assertTrue(total == 0, "验证列表总数据量");
    }
}
