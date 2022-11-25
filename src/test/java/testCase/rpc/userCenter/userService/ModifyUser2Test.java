package testCase.rpc.userCenter.userService;

import testCase.rpc.userCenter.UserCenterTestBase;
import org.testng.annotations.Test;

public class ModifyUser2Test extends UserCenterTestBase {

    @Test(description = "不传userId")
    public void test() {

        // 数据还原
        auto.sql.uc("update user set user_name='干旗人' where user_id='416170902167365';");

        // 调rpc接口修改信息
        String result = auto.rpc.invoke("com.luoys.upgrade.uc.share.service.UserService#modifyUser", "com.luoys.upgrade.uc.share.dto.UserDTO", "{\"userName\":\"这是修改后的名字\"}");

        // 验证修改结果
        String message = auto.util.getJson("message", result);
        auto.assertion.isEquals(message, "业务异常");
    }

}
