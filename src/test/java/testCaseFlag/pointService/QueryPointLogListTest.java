package testCaseFlag.pointService;

import com.luoys.upgrade.flag.api.enums.PointLogTypeEnum;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import testBase.flag.FlagTestBase;

public class QueryPointLogListTest extends FlagTestBase {

    private final String FullURL = URL + "point/queryPointLogList";

    @Test(description = "查询积分使用记录")
    void test1() {
        Reporter.log("调用接口");
        String result = auto.http.get(FullURL + "?pointId=516176799148478&type=" + PointLogTypeEnum.DECREASE.getCode());

        Reporter.log("验证结果");
        String data = auto.jsonUtil.getBaseData(result, "data");
        Assert.assertTrue(data.contains("自动化queryPointLogList测试"), "校验使用记录是否存在");
    }
}
