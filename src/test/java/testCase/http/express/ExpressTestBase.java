package testCase.http.express;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import root.HttpExpressRoot;

public class ExpressTestBase {

    /*---------------------------配置参数-------------------*/
    // express http 自动化专用
    // 接口域名(调用环境)
    public static final String URL = "http://www.kuaidi100.com/";
    /*---------------------------配置参数-------------------*/


    /**
     * 代理类实例，所有公共模块方法通过此实例调用（这里一定要static）
     */
    public static HttpExpressRoot auto;

    // 用例执行时再开始实例化auto变量，因为用mvn test执行会先把test包下所有类实例化一遍
    @BeforeSuite
    public void supperBeforeSuite() {
        auto = new HttpExpressRoot();
    }

    @BeforeClass
    public void beforeClass() {
        // 指定环境
        auto.http.setDefaultUrl(URL);
    }

    @AfterSuite
    public void afterSuite() {
        auto.http.close();
    }
}
