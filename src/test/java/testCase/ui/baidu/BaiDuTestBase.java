package testCase.ui.baidu;

import config.Config;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import proxy.UiBaiduProxy;

public class BaiDuTestBase {

    /**
     * 代理类实例，所有公共模块方法通过此实例调用（这里一定要static，因为只实例化一次）
     */
    public static UiBaiduProxy auto;

    @BeforeSuite(alwaysRun = true, description = "启动webDriver")
    public void supperBeforeSuite() {
        System.out.println("\n--------> 开始初始化WebDriver\n");
        // 套件开始执行时，再实例化代理类
        auto = new UiBaiduProxy();
        // 初始化webDriver，需要在开始执行UI用例时，才能初始化
        System.out.println("\n--------> 初始化WebDriver结束\n");
    }

    @BeforeClass(description = "访问登录页并用自动化专用账号登录", alwaysRun = true)
    public void supperBeforeClass() {
        System.out.println("\n--------> 开始执行百度 UI 前置步骤\n");

        auto.ui.initChrome(Config.UI_BAIDU_OPTIONS);

        auto.ui.clearCookies();
        auto.ui.openUrl(Config.UI_BAIDU_URL);

        System.out.println("\n--------> 开始执行百度 UI 自动化用例\n");
    }

    @AfterClass(description = "用例执行结束", alwaysRun = true)
    public void supperAfterClass() {
        System.out.println("\n--------> 完成百度 UI 自动化用例\n");
    }

    @AfterSuite(alwaysRun = true, description = "关闭浏览器且关闭驱动进程")
    public void supperAfterSuite() {
        // 整个套件执行完成后，才能关闭资源
        auto.ui.quit();
        System.out.println("\n--------> 关闭WebDriver结束\n");
    }

}
