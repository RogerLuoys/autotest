package userCenterTestCase.userService;

import org.testng.annotations.Test;
import supperTestClass.userCenter.UserCenterTestBase;

import java.util.Map;

public class QueryByUserIdTestCase extends UserCenterTestBase {

    private final String serviceURL = dubboURL + "com.luoys.upgrade.uc.share.service.UserService";

    @Test
    public void Test1() {
//        UserService userService = auto.rpc.getService(serviceURL, UserService.class);
//        String result = JSON.toJSONString(userService.queryByUserId("101"));
//        System.out.println("----------------->result: " + result);
        Map<String, Object> test = auto.ucDB.select("select * from user;");
        System.out.println("----------------->data: " + test);
        int count = auto.ucDB.count("select count(1) from user");
        System.out.println("----------------->count: " + count);
        auto.ucDB.delete("delete from flag_bind where flag_id in (select flag_id from flag where flag_name = '自动化新增FLAG测试');");
        auto.ucDB.delete("delete from flag where flag_name = '自动化新增FLAG测试';");
    }
}
