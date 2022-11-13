package testCase.rpc.userCenter.adminService;

import org.testng.annotations.Test;
import testCase.rpc.userCenter.UserCenterTestBase;


public class RemoveUserTest extends UserCenterTestBase {

//    private final String serviceURL = config.Config.UC_URL + "com.luoys.upgrade.uc.share.service.AdminService";
//    private final AdminService adminService = auto.rpc.getService(serviceURL, AdminService.class);
//
//    @BeforeClass
//    void resetData() {
//        auto.sql.uc("update user set is_delete=0 where user_id='416147574006268'");
//    }
//
//    @Test(description = "删除一个用户")
//    void test1() {
//        Reporter.log("调用removeUser方法");
//        adminService.removeUser("416147574006268");
//
//        Reporter.log("验证结果");
//        String isUserDelete = auto.ucDB.selectOneCell("select is_delete from user where user_id='416147574006268'");
//        //数据库tinyint(1)会被jdbcTemplate转换成Boolean类型
//        Assert.assertEquals(isUserDelete, "true", "校验是否删除");
//    }

    @Test(description = "删除一个用户")
    public void test() {
        // 数据还原
        auto.sql.uc("update user set is_delete=0 where user_id='416147574006268'");

        // 调接口删除
        auto.rpc.invoke("com.luoys.upgrade.uc.share.service.AdminService#removeUser", "java.lang.String", "416147574006268");

        // 通过数据库检查删除结果
        String isUserDelete = auto.sql.uc("select is_delete from user where user_id='416147574006268';");
        auto.assertion.isDeleted(isUserDelete);

    }

}
