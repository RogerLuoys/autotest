package testCaseUserCenter.userService;

import com.luoys.upgrade.uc.share.service.UserService;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import testBase.userCenter.UserCenterTestBase;

import java.util.Map;

public class QueryByUserIdTest extends UserCenterTestBase {

    private final String serviceURL = URL + "com.luoys.upgrade.uc.share.service.UserService";
    private final UserService userService = auto.rpc.getService(serviceURL, UserService.class);

    @Test(description = "查询正常用户信息")
    void Test1() {
        String result = auto.jsonUtil.toString(userService.queryByUserId("416160586979148"));
        System.out.println("----------------->result: " + result);
        Map<String, Object> test = auto.ucDB.select("select * from user;");
        System.out.println("----------------->data: " + test);
        int count = auto.ucDB.count("select count(1) from user where id=1123");
        System.out.println("----------------->count: " + count);
        Reporter.log("test log");
    }

    @Test(description = "查询不存在的用户", enabled = false)
    void Test2() {
        String result = auto.jsonUtil.toString(userService.queryByUserId("10000"));
        String message = auto.jsonUtil.getBaseData(result, "message");
        Assert.assertEquals(message, "用户不存在", "验证不存在用户的查询结果");
    }
}
