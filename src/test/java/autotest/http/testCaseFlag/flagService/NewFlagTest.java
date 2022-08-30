package autotest.http.testCaseFlag.flagService;

import com.luoys.upgrade.flag.api.bo.FlagBO;
import com.luoys.upgrade.flag.api.enums.FlagStatusEnum;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import autotest.http.testCaseFlag.FlagTestBase;

public class NewFlagTest extends FlagTestBase {

    private final String FullURL = auto.config.URL + "flag/newFlag";

    @BeforeClass
    void resetData() {
        auto.flagDB.delete("delete from flag_bind where flag_id in (select flag_id from flag where flag_name = 'newFlag自动化');");
        auto.flagDB.delete("delete from flag where flag_name = 'newFlag自动化';");
    }

    @Test(description = "正常创建flag")
    void test1() {
        Reporter.log("准备入参");
        FlagBO flagBO = new FlagBO();
        flagBO.setFlagName("newFlag自动化");
        flagBO.setOwnerId("416176799148282");

        Reporter.log("调用接口");
        auto.http.post(FullURL, flagBO);

        Reporter.log("验证结果");
        String status = auto.flagDB.selectOneCell("select status from flag where flag_name='newFlag自动化'");
        Assert.assertEquals(status, FlagStatusEnum.NOT_START.getCode().toString(), "校验默认状态");
    }
}
