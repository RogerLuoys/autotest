package autotest.rpc.testCaseUserCenter.userService;

import org.testng.annotations.Test;
import autotest.rpc.testCaseUserCenter.UserCenterTestBase;


public class QueryByUserIdTest extends UserCenterTestBase {

//    private final String serviceURL = auto.config.URL + "com.luoys.upgrade.uc.share.service.UserService";
//    private final UserService userService = auto.rpc.getService(serviceURL, UserService.class);
//
//    @Test(description = "查询正常用户信息")
//    void test1() {
//        Reporter.log("调用queryByUserId方法");
//        String result = auto.jsonUtil.toString(userService.queryByUserId("416160586979148"));
//
//        Reporter.log("验证结果");
//        String userId = auto.jsonUtil.getData(result, "userId");
//        Assert.assertEquals(userId, "416160586979148", "验证用户的查询结果");
//    }
//
//
//    @Test(description = "查询不存在的用户")
//    void test2() {
//        Reporter.log("调用queryByUserId方法");
//        String result = auto.jsonUtil.toString(userService.queryByUserId("10000"));
//
//        Reporter.log("验证结果");
//        String message = auto.jsonUtil.getBaseData(result, "message");
//        Assert.assertEquals(message, "用户不存在", "验证不存在用户的查询结果");
//    }
//
//    @Test(description = "泛化调用")
//    void test3() {
//        String result = auto.rpc.invoke(serviceURL, UserService.class.getName(), "queryByUserId", new String[] {"java.lang.String"}, new Object[]{"416160586979148"});
//        String userId = auto.jsonUtil.getData(result, "userId");
//        Assert.assertEquals(userId, "416160586979148", "验证用户的查询结果");
//        System.out.println(result);
//    }

    @Test(description = "查询正常用户信息")
    public void test() {
        String result = auto.rpc.invoke("com.luoys.upgrade.uc.share.service.UserService#queryByUserId", "java.lang.String", "416160586979148");
        String userId = auto.util.getFirstValue("userId", result);
        auto.assertion.isEquals(userId, "416160586979148");
    }

}
