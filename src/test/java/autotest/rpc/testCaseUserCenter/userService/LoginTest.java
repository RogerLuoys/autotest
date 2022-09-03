package autotest.rpc.testCaseUserCenter.userService;

import com.luoys.upgrade.uc.share.service.UserService;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import autotest.rpc.testCaseUserCenter.UserCenterTestBase;

public class LoginTest extends UserCenterTestBase {

//    private final String serviceURL = auto.config.URL + "com.luoys.upgrade.uc.share.service.UserService";
//    private final UserService userService = auto.rpc.getService(serviceURL, UserService.class);
//
//    @Test(description = "正常登录")
//    void test1() {
//        Reporter.log("调用login方法");
//        String result = auto.jsonUtil.toString(userService.login("APITester", null, "123456"));
//
//        Reporter.log("验证结果");
//        String userId = auto.jsonUtil.getData(result,"userId");
//        Assert.assertEquals(userId, "416176799148282", "验证正常登录结果");
//    }
//
//
//    @Test(description = "密码不对时登录")
//    void test2() {
//        Reporter.log("调用login方法");
//        String result = auto.jsonUtil.toString(userService.login("autoTester", null, "1234567"));
//
//        Reporter.log("验证结果");
//        String message = auto.jsonUtil.getData(result,"message");
//        Assert.assertEquals(message, "登录名不存在", "验证密码不对登录结果");
//    }

}
