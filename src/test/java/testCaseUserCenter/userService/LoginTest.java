package testCaseUserCenter.userService;

import com.luoys.upgrade.uc.share.service.UserService;
import org.testng.Assert;
import org.testng.annotations.Test;
import testBase.userCenter.UserCenterTestBase;

public class LoginTest extends UserCenterTestBase {

    private final String serviceURL = URL + "com.luoys.upgrade.uc.share.service.UserService";
    private final UserService userService = auto.rpc.getService(serviceURL, UserService.class);

    @Test(description = "正常登录")
    void test1() {
        String result = auto.jsonUtil.toString(userService.login("autoTester", null, "123456"));
        String userId = auto.jsonUtil.getData(result,"userId");
        Assert.assertEquals(userId, "416160586979148", "验证正常登录结果");
    }

    @Test(description = "密码不对时登录")
    void test2() {
        String result = auto.jsonUtil.toString(userService.login("autoTester", null, "1234567"));
        String message = auto.jsonUtil.getData(result,"message");
        Assert.assertEquals(message, "业务异常", "验证密码不对登录结果");
    }

}
