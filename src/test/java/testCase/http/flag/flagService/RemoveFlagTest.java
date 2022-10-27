package testCase.http.flag.flagService;

import org.testng.annotations.Test;
import testCase.http.flag.FlagTestBase;

public class RemoveFlagTest extends FlagTestBase {

//    private final String FullURL = config.Config.URL + "flag/removeFlag";
//
//    @BeforeClass
//    void resetData() {
//        auto.flagDB.update("update flag set is_delete=0 where flag_id='116181337773958'");
//        auto.flagDB.update("update flag_bind set is_delete=0 where flag_id='116181337773958'");
//    }
//
//    @Test(description = "正常删除一个flag")
//    void test1() {
//        Reporter.log("调用接口");
//        auto.http.delete(FullURL + "?flagId=116181337773958");
//
//        Reporter.log("验证结果");
//        String isFlagDelete = auto.flagDB.selectOneCell("select is_delete from flag where flag_id='116181337773958'");
//        String isFlagBindDelete = auto.flagDB.selectOneCell("select is_delete from flag_bind where flag_id='116181337773958'");
//        Assert.assertEquals(isFlagDelete, "1", "校验主表是否删除");
//        Assert.assertEquals(isFlagBindDelete, "1", "校验关系表是否删除");
//    }

    @Test(description = "正常删除一个flag")
    void test1() {
        // 调用接口
        auto.http.delete("flag/removeFlag?flagId=116181337773958");

        // 验证结果
        String isFlagDelete = auto.sql.flag("select is_delete from flag where flag_id='116181337773958'");
        String isFlagBindDelete = auto.sql.flag("select is_delete from flag_bind where flag_id='116181337773958'");
        auto.assertion.isDeleted(isFlagDelete);
        auto.assertion.isDeleted(isFlagBindDelete);

    }

}
