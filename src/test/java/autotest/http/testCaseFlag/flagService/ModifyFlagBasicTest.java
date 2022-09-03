package autotest.http.testCaseFlag.flagService;

import org.testng.annotations.Test;
import autotest.http.testCaseFlag.FlagTestBase;

public class ModifyFlagBasicTest extends FlagTestBase {

//    private final String FullURL = auto.config.URL + "flag/modifyFlagBasic";
//
//    @BeforeClass
//    void resetData() {
//        auto.flagDB.update("update flag set description=null where flag_id='116181360305483'");
//    }
//    @Test
//    void test1() {
//        Reporter.log("准备入参");
//        FlagBO flagBO = new FlagBO();
//        flagBO.setFlagId("116181360305483");
//        flagBO.setDescription("这是修改后的描述");
//
//        Reporter.log("调用接口");
//        auto.http.put(FullURL, flagBO);
//
//        Reporter.log("验证结果");
//        String description = auto.flagDB.selectOneCell("select description from flag where flag_id='116181360305483'");
//        Assert.assertEquals(description, "这是修改后的描述", "校验基本信息修改结果");
//    }

    @Test(description = "修改基本信息")
    void test1() {
        // 调用接口
        auto.http.put("flag/modifyFlagBasic", "{\"flagId\":\"116181360305483\",\"description\":\"这是修改后的描述\"}");

        // 验证结果
        String description = auto.sql.flag("select description from flag where flag_id='116181360305483'");
        auto.assertion.isEquals(description, "这是修改后的描述");
    }

}
