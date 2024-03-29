package testCase.ui.flag;

import org.testng.annotations.*;
import root.UiFlagRoot;

/**
 * 账号userId： 416160586979148
 */
public class FlagTestBase {

    /*---------------------------配置参数-------------------*/
    // flag ui 自动化专用
    // 环境域名
    public static final String URL = "http://localhost/";
    // 登录名
    public static final String USERNAME = "UITester";
    // 登录密码
    public static final String PASSWORD = "123456";
    // webdriver启动参数,"--headless"
    public static final String[] OPTIONS = {"--disable-dev-shm-usage",
            "--no-sandbox", "url=data:,", "--start-maximized", "--disable-gpu", "--hide-scrollbars",
            "disable-infobars","--headless"};
    /*---------------------------配置参数-------------------*/

    /**
     * 代理类实例，所有公共模块方法通过此实例调用（加static则多线程只能到tests，不加可以tests或class）
     */
    public static UiFlagRoot auto;

    @BeforeSuite(alwaysRun = true, description = "启动webDriver")
    public void supperBeforeSuite() {
        System.out.println("\n--------> 开始初始化WebDriver\n");

        //1 套件开始执行时，再实例化代理类
        auto = new UiFlagRoot();
        //2 初始化webDriver，需要在开始执行UI用例时，才能初始化
//        auto.ui.init();

        System.out.println("\n--------> 初始化WebDriver结束\n");
    }

    @BeforeClass(description = "访问登录页并用自动化专用账号登录", alwaysRun = true)
    public void supperBeforeClass() {
        System.out.println("\n--------> 开始执行 Flag UI 前置步骤\n");

        auto.ui.initChrome(OPTIONS);

        //1 清理cookies
        auto.ui.clearCookies();
        auto.ui.openUrl(URL);
        auto.po.loginFlag(USERNAME, PASSWORD);
        // 设置本地存储，以屏蔽部分提示
        auto.ui.executeJs("localStorage.setItem('guide','close')");

        System.out.println("\n--------> 开始执行 Flag UI 自动化用例\n");
    }

    @AfterClass(description = "用例执行结束", alwaysRun = true)
    public void supperAfterClass() {
        System.out.println("\n--------> 完成 Flag UI 自动化用例\n");
    }

    @AfterSuite(alwaysRun = true, description = "关闭浏览器且关闭驱动进程")
    public void supperAfterSuite() {
        // 整个套件执行完成后，才能关闭资源
        auto.ui.quit();
        System.out.println("\n--------> 关闭WebDriver结束\n");
    }

}
