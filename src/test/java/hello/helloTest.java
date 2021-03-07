package hello;

import com.alibaba.fastjson.JSON;
import connect.HttpClient;
import connect.JdbcConnect;
import connect.TestJAVA;
import connect.UserBO;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;


public class helloTest {
    private static Logger logger= LoggerFactory.getLogger(helloTest.class);
    @Test
    public void Test1() {

//String a;
//a.length()
//        HttpClient http1 = new HttpClient();
//        String a = http1.hello();
//        String b = http1.doGet("http://118.24.117.181:8888/hello");
//        String b = http1.doGet("http://yqdz.manage-test.sit.91lyd.com/xqy-portal-web/manage/v2/whitelist/isInCustomWhitelist?code=manage_5_0_White&_=1587537844654");
//        System.out.print(b);
//        JdbcConnect jdbc1 = new JdbcConnect();
//        TestJAVA test1 = new TestJAVA();
//        String t1 = test1.funcation1("String");
//        System.out.println(test1.funcation1(11122L));

        UserBO user1 = new UserBO();
        user1.setUserid("123");
        user1.setUsername("tester");
        user1.setUserpw("321");
        String t1 = JSON.toJSONString(user1);
        String t2 = JSON.toJSONString("this is a string");
        System.out.println(t1);
        System.out.println(t2);


        logger.info("info log:{},other:{}",t1,t1);
        logger.error("error log");
        logger.warn("warning log");
        logger.trace("trace logs");
        System.out.println("print info");

//        WebDriver driver = new ChromeDriver();
//        driver.get("http://release-17dz.dc.servyou-it.com/home/login.html");
//        driver.close();

        Assert.assertTrue(true,"pass");

    }

}
