package userCenterTestCase.userService;

import com.alibaba.fastjson.JSON;
import com.luoys.upgrade.uc.share.dto.UserDTO;
import com.luoys.upgrade.uc.share.service.UserService;
import org.testng.annotations.Test;
import supperTestClass.userCenter.UserCenterTestBase;

import java.util.Map;

public class QueryByUserIdTest extends UserCenterTestBase {

    private final String serviceURL = URL + "com.luoys.upgrade.uc.share.service.UserService";

    @Test(description = "查询正常用户信息")
    void Test1() {
        UserService userService = auto.rpc.getService(serviceURL, UserService.class);
        String result = auto.util.toJSONString(userService.queryByUserId("416160586979148"));
        System.out.println("----------------->result: " + result);
        Map<String, Object> test = auto.ucDB.select("select * from user;");
        System.out.println("----------------->data: " + test);
        int count = auto.ucDB.count("select count(1) from user where id=1123");
        System.out.println("----------------->count: " + count);

    }

    @Test(description = "查询正常用户信息")
    void Test2() {
        String var = "{\"code\":1,\"data\":{\"message\":[{\"loginName\":\"autoTester\"},{\"loginName\":\"autoTester2\"}],\"loginName\":\"autoTester\",\"password\":\"123456\",\"status\":1,\"type\":2,\"userId\":\"416160586979148\",\"userName\":\"新干旗人\"},\"message\":\"成功\",\"success\":true}";
        String result = auto.util.getJSONData(var, "message");
        result = auto.util.getJSONBaseData(var, "data");
        System.out.println("------>"+var);
        System.out.println("------>"+result);

    }
}
