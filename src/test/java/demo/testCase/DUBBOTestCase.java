package demo.testCase;

import demo.testBase.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class DUBBOTestCase extends TestBase {

    private final String serviceURL = auto.config.DUBBO_URL + "***.UserService";

    @BeforeClass
    void beforeClass() {
        auto.db.delete("sql");
    }

    @Test
    void test1() {
//        UserService userService = auto.rpc.getService(serviceURL + "class name", UserService.class);
//        String result = auto.jsonUtil.toString(userService.queryByUserId("101"));
//        Assert.assertTrue(result.contains("***"), "******");
        String result = auto.rpc.invoke(serviceURL, "***.UserService", "login", new String[]{"***.UserDTO"}, new Object[]{"userId", "password"});
        Assert.assertTrue(result.contains("***"), "******");
    }

    @Test
    void test2() {
        List<String> names = auto.excelUtil.getWorksheetNames("D:\\Test.xls");
        System.out.println(names);
        List<String> names2 = auto.excelUtil.getWorksheetNames("D:\\test.xlsx");
        System.out.println(names2);
        Assert.assertTrue(false, "******");
    }


    @AfterClass
    void afterClass() {
        auto.db.delete("sql");
    }

}
