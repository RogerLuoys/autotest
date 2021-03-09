package demo.testCase;

import com.alibaba.fastjson.JSON;
import com.luoys.upgrade.uc.share.service.UserService;
import connect.UserBO;
import demo.testBase.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class APITestCase extends TestBase {

    String url = "http://118.24.117.181:9001/api/flag";

    @Test
    void testCase1(){
        String result = auto.http.doGet("http://118.24.117.181:9001/api/flag/queryFlagDetail/116149450801523");
        System.out.println(result);
    }

    @Test
    void testCase2(){
        Map<String, Object> params = new HashMap<>();
        params.put("pageIndex", 1);
//        params.put("test2", "string1");
        params.put("type", 1);
        String result = auto.http.doPost("http://118.24.117.181:9001/api/flag/queryFlagList", params);
        System.out.println(result);
    }

    @Test
    void testCase3() {
        Class<?> userServiceClass = UserService.class;
        String className = UserService.class.getName();
//        auto.rpc.dubboService();
        UserService userService = auto.rpc.getService("dubbo://118.24.117.181:20881/com.luoys.upgrade.uc.share.service.UserService", UserService.class);
        String result = JSON.toJSONString(userService.queryByUserId("101"));

        System.out.println(result);
    }

}
