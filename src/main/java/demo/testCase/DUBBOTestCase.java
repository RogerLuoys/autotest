package demo.testCase;

import com.alibaba.fastjson.JSON;
import com.luoys.upgrade.uc.share.service.UserService;
import demo.testBase.TestBase;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class DUBBOTestCase extends TestBase {

    @Test
    void test1() {
        UserService userService = auto.rpc.getService(DUBBO_URL + "com.luoys.upgrade.uc.share.service.UserService", UserService.class);
        String result = auto.jsonUtil.toString(userService.queryByUserId("101"));
        Assert.assertTrue(result.contains("***"), "******");
    }

}
