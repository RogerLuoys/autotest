package demo.testCase;

import com.alibaba.fastjson.JSON;
import com.luoys.upgrade.uc.share.service.UserService;
import demo.testBase.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class DUBBOTestCase extends TestBase {

    @Test
    void test1() {
        UserService userService = auto.rpc.getService(DUBBO_URL + "com.luoys.upgrade.uc.share.service.UserService", UserService.class);
        String result = auto.jsonUtil.toString(userService.queryByUserId("101"));
        Assert.assertTrue(result.contains("***"), "******");
    }

    @Test
    void test2() {
        List<String> names = auto.excelUTILHSSF.getWorksheetNames("D:\\Test.xls");
        System.out.println(names);
//        List<String> names2 = auto.excelUTILXSSF.getWorksheetNames("D:\\test.xlsx");
//        System.out.println(names2);
        Assert.assertTrue(false, "******");
    }

}
