package testCaseFlag.pointService;

import com.luoys.upgrade.flag.api.bo.PointLogBO;
import com.luoys.upgrade.flag.api.enums.PointLogTypeEnum;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testBase.flag.FlagTestBase;

public class NewPointLogTest extends FlagTestBase {

    private final String FullURL = auto.config.URL + "point/newPointLog";

    @BeforeClass
    void resetData() {
        auto.flagDB.delete("delete from point_log where description='newPointLog自动化使用'");
    }

    @Test(description = "正常使用积分")
    void test1() {
        Reporter.log("准备入参");
        PointLogBO pointLogBO = new PointLogBO();
        pointLogBO.setPointId("516176799148478");
        pointLogBO.setDescription("newPointLog自动化使用");
        pointLogBO.setPoint(1);
        pointLogBO.setType(PointLogTypeEnum.DECREASE.getCode());

        Reporter.log("调用接口");
        auto.http.post(FullURL, pointLogBO);

        Reporter.log("验证结果");
        String point = auto.flagDB.selectOneCell("select point from point_log where description='newPointLog自动化使用'");
        Assert.assertEquals(point, "1", "校验使用的额度是否正确");
    }
}
