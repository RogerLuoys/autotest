package autotest.ui;

import autotest.CommonProxy;
import org.testng.annotations.*;

/**
 * 专用账号/密码：UITester/123456
 * 账号userId： 416160586979148
 */
public class UITestBase {
    public CommonProxy auto;

    @BeforeSuite(alwaysRun = true, description = "启动webDriver")
    public void supperBeforeSuite() {
        // 套件开始执行时，再实例化代理类
        auto = new CommonProxy();
        // 初始化webDriver，需要在开始执行UI用例时，才能初始化
        auto.ui.init();
    }

    @AfterSuite(alwaysRun = true, description = "关闭浏览器且关闭驱动进程")
    public void supperAfterSuite() {
        // 整个套件执行完成后，才能关闭资源
        auto.ui.quit();
    }


}
