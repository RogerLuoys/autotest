package testCaseUserCenter.adminService;

import com.luoys.upgrade.uc.share.service.AdminService;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testCaseUserCenter.UserCenterTestBase;


public class RemoveUserTest extends UserCenterTestBase {

    private final String serviceURL = auto.config.URL + "com.luoys.upgrade.uc.share.service.AdminService";
    private final AdminService adminService = auto.rpc.getService(serviceURL, AdminService.class);

    @BeforeClass
    void resetData() {
        auto.ucDB.update("update user set is_delete=0 where user_id='416147574006268'");
    }

    @Test(description = "删除一个用户")
    void test1() {
        Reporter.log("调用removeUser方法");
        adminService.removeUser("416147574006268");

        Reporter.log("验证结果");
        String isUserDelete = auto.ucDB.selectOneCell("select is_delete from user where user_id='416147574006268'");
        //数据库tinyint(1)会被jdbcTemplate转换成Boolean类型
        Assert.assertEquals(isUserDelete, "true", "校验是否删除");
    }

}
