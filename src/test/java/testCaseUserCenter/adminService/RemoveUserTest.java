package testCaseUserCenter.adminService;

import com.luoys.upgrade.uc.share.service.AdminService;
import com.luoys.upgrade.uc.share.service.UserService;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testBase.userCenter.UserCenterTestBase;

public class RemoveUserTest extends UserCenterTestBase {

    private final String serviceURL = URL + "com.luoys.upgrade.uc.share.service.AdminService";
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
        Assert.assertEquals(isUserDelete, "1", "校验是否删除");
    }

}
