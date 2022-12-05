package testCase.http.flag.templateService;

import org.testng.Reporter;
import org.testng.annotations.Test;
import testCase.http.flag.FlagTestBase;

public class UseFlagTemplateTest extends FlagTestBase {

//    private final String FullURL = URL + "template/useFlagTemplate";

//    @BeforeClass
//    void resetData() {
//        auto.flagDB.delete("delete from flag_bind where flag_id in (select flag_id from flag where flag_name = '自动化脚本转换模板001')");
//        auto.flagDB.delete("delete from flag where flag_name = '自动化脚本转换模板001'");
//    }

    @Test
    void test1() {
        // 数据还原
        auto.sql.flag("delete from flag_bind where flag_id in (select flag_id from flag where flag_name = '自动化脚本转换模板001')");
        auto.sql.flag("delete from flag where flag_name = '自动化脚本转换模板001'");

        Reporter.log("准备入参");
//        FlagTemplateBO flagTemplateBO = new FlagTemplateBO();
//        flagTemplateBO.setFlagName("自动化脚本转换模板001");
//        flagTemplateBO.setOwnerId("416176799148282");

        Reporter.log("调用接口");
        auto.http.post("template/useFlagTemplate", "{\"flagName\":\"自动化脚本转换模板001\",\"ownerId\":\"416176799148282\"}");

        Reporter.log("验证结果");
        String status = auto.sql.flag("select status from flag where flag_name='自动化脚本转换模板001'");
        auto.assertion.isEquals(status, "");
//        Assert.assertEquals(status, FlagStatusEnum.NOT_START.getCode().toString(), "校验默认状态");
    }
}
