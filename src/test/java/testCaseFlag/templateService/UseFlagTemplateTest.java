package testCaseFlag.templateService;

import com.luoys.upgrade.flag.api.bo.FlagTemplateBO;
import com.luoys.upgrade.flag.api.enums.FlagStatusEnum;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testBase.flag.FlagTestBase;

public class UseFlagTemplateTest extends FlagTestBase {

    private final String FullURL = auto.config.URL + "template/useFlagTemplate";

    @BeforeClass
    void resetData() {
        auto.flagDB.delete("delete from flag_bind where flag_id in (select flag_id from flag where flag_name = '自动化脚本转换模板001')");
        auto.flagDB.delete("delete from flag where flag_name = '自动化脚本转换模板001'");
    }

    @Test
    void test1() {
        Reporter.log("准备入参");
        FlagTemplateBO flagTemplateBO = new FlagTemplateBO();
        flagTemplateBO.setFlagName("自动化脚本转换模板001");
        flagTemplateBO.setOwnerId("416176799148282");

        Reporter.log("调用接口");
        auto.http.post(FullURL, flagTemplateBO);

        Reporter.log("验证结果");
        String status = auto.flagDB.selectOneCell("select status from flag where flag_name='自动化脚本转换模板001'");
        Assert.assertEquals(status, FlagStatusEnum.NOT_START.getCode().toString(), "校验默认状态");
    }
}
