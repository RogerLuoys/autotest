package testCaseUserCenter.userService;

import com.luoys.upgrade.uc.share.service.UserService;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import testBase.userCenter.UserCenterTestBase;


public class QueryByUserIdTest extends UserCenterTestBase {

    private final String serviceURL = URL + "com.luoys.upgrade.uc.share.service.UserService";
    private final UserService userService = auto.rpc.getService(serviceURL, UserService.class);

    @Test(description = "查询正常用户信息")
    void Test1() {
        Reporter.log("调用queryByUserId方法");
        String result = auto.jsonUtil.toString(userService.queryByUserId("416160586979148"));

        Reporter.log("验证结果");
        String userId = auto.jsonUtil.getData(result, "userId");
        Assert.assertEquals(userId, "416160586979148", "验证用户的查询结果");
    }


    @Test(description = "查询不存在的用户")
    void Test2() {
        Reporter.log("调用queryByUserId方法");
        String result = auto.jsonUtil.toString(userService.queryByUserId("10000"));

        Reporter.log("验证结果");
        String message = auto.jsonUtil.getBaseData(result, "message");
        Assert.assertEquals(message, "用户不存在", "验证不存在用户的查询结果");
    }
}
