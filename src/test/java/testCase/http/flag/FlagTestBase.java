package testCase.http.flag;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import root.HttpFlagRoot;

/**
 * 该测试专用账号/密码：APITester/123456
 * 账号userId： 416176799148282
 */
public class FlagTestBase extends Config {

    /**
     * 代理类实例，所有公共模块方法通过此实例调用（这里一定要static）
     */
    public static HttpFlagRoot auto;

    // 用例执行时再开始实例化auto变量，因为用mvn test执行会先把test包下所有类实例化一遍
    @BeforeSuite
    public void supperBeforeSuite() {
        auto = new HttpFlagRoot();
    }

    @BeforeClass
    public void beforeClass() {
        // 指定环境
        auto.http.setBaseURL(URL);
    }
}
