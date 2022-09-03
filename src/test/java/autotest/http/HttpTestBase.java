package autotest.http;

import autotest.CommonProxy;
import org.testng.annotations.BeforeSuite;

public class HttpTestBase {

    /**
     * 代理类实例，所有公共模块方法通过此实例调用（这里一定要static）
     */
    public static CommonProxy auto;

    // 用例执行时再开始实例化auto变量，因为用mvn test执行会先把test包下所有类实例化一遍
    @BeforeSuite
    public void supperBeforeSuite() {
        auto = new CommonProxy();
    }
}
