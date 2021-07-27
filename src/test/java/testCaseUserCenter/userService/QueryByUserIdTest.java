package testCaseUserCenter.userService;

import com.alibaba.fastjson.JSON;
import com.luoys.upgrade.uc.share.dto.UserDTO;
import com.luoys.upgrade.uc.share.service.UserService;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import testBase.userCenter.UserCenterTestBase;

import java.util.ArrayList;
import java.util.List;


public class QueryByUserIdTest extends UserCenterTestBase {

    private final String serviceURL = auto.config.URL + "com.luoys.upgrade.uc.share.service.UserService";
    private final UserService userService = auto.rpc.getService(serviceURL, UserService.class);

    @Test(description = "查询正常用户信息")
    void test1() {
        Reporter.log("调用queryByUserId方法");
        String result = auto.jsonUtil.toString(userService.queryByUserId("416160586979148"));

        Reporter.log("验证结果");
        String userId = auto.jsonUtil.getData(result, "userId");
        Assert.assertEquals(userId, "416160586979148", "验证用户的查询结果");
    }


    @Test(description = "查询不存在的用户")
    void test2() {
        Reporter.log("调用queryByUserId方法");
        String result = auto.jsonUtil.toString(userService.queryByUserId("10000"));

        Reporter.log("验证结果");
        String message = auto.jsonUtil.getBaseData(result, "message");
        Assert.assertEquals(message, "用户不存在", "验证不存在用户的查询结果");
    }

    @Test(description = "泛化调用")
    void test3() {
        String result = auto.rpc.invoke(serviceURL, UserService.class.getName(), "queryByUserId", new String[] {"java.lang.String"}, new Object[]{"416160586979148"});
        String userId = auto.jsonUtil.getData(result, "userId");
        Assert.assertEquals(userId, "416160586979148", "验证用户的查询结果");
        System.out.println(result);
    }

    @Test(description = "泛化调用2")
    void test4() {
//        auto.rpc.invoke();
        Integer t1 = 1234455;
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName("接口自动化注册的用户名");
        userDTO.setLoginName("DUBBOAuto");
        userDTO.setPassword("auto123456");
        UserDTO userDTO2 = new UserDTO();
        userDTO2.setUserName("接口自动化注册的用户名2");
        userDTO2.setLoginName("DUBBOAuto");
        userDTO2.setPassword("auto123456");
        List<UserDTO> list1 = new ArrayList<>();
        list1.add(userDTO);
        list1.add(userDTO2);
        System.out.println(JSON.toJSONString(list1));
    }
}
