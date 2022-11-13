package testCase.rpc.userCenter.userService;

import testCase.rpc.userCenter.UserCenterTestBase;
import org.testng.annotations.Test;

public class Register2Test extends UserCenterTestBase {

    @Test(description = "注册登录名重复")
    public void test() {
        // 调用接口注册
        String result = auto.rpc.invoke("com.luoys.upgrade.uc.share.service.UserService#register", "com.luoys.upgrade.uc.share.dto.UserDTO", "{\"userName\":\"接口自动化注册的用户名\",\"loginName\":\"autoTest01\",\"password\":\"123456\"}");

        // 校验结果
        String message = auto.util.getJsonValue("message", result);
        auto.assertion.isEquals(message, "登录名已被注册");
    }

}
