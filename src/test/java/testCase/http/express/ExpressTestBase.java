package testCase.http.express;

import config.Config;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import proxy.HttpExpressProxy;

public class ExpressTestBase {

    /**
     * 代理类实例，所有公共模块方法通过此实例调用（这里一定要static）
     */
    public static HttpExpressProxy auto;

    // 用例执行时再开始实例化auto变量，因为用mvn test执行会先把test包下所有类实例化一遍
    @BeforeSuite
    public void supperBeforeSuite() {
        auto = new HttpExpressProxy();
    }

    @BeforeClass
    public void beforeClass() {
        // 指定环境
        auto.http.setBaseURL(Config.HTTP_EXPRESS_URL);
    }

}