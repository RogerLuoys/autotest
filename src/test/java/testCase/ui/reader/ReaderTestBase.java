package testCase.ui.reader;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import root.UiReaderRoot;
import root.UiReaderRoot;

public class ReaderTestBase {

    /*---------------------------配置参数-------------------*/
    // reader ui 自动化专用
    // 环境域名
    // 启动参数
    public static final String[] OPTIONS = {"--disable-dev-shm-usage",
            "--no-sandbox", "url=data:,", "--start-maximized", "--disable-gpu", "--hide-scrollbars",
            "disable-infobars", "deviceName,Samsung Galaxy S8+","--headless"};
    /*---------------------------配置参数-------------------*/


    /**
     * 代理类实例，所有公共模块方法通过此实例调用（加static则多线程只能到tests，不加可以tests或class）
     */
    public static UiReaderRoot auto;

    @BeforeSuite(alwaysRun = true, description = "启动webDriver")
    public void supperBeforeSuite() {

        //1 套件开始执行时，再实例化代理类
        auto = new UiReaderRoot();
        // 全民阅读官网apk
        auto.ui.initAndroid();
    }

    @BeforeClass(description = "--", alwaysRun = true)
    public void supperBeforeClass() {
        System.out.println("\n--------> 开始 Reader UI 自动化用例\n");
    }

    @AfterClass(description = "用例执行结束", alwaysRun = true)
    public void supperAfterClass() {
        System.out.println("\n--------> 完成 Reader UI 自动化用例\n");
    }

    @AfterSuite(alwaysRun = true, description = "关闭浏览器且关闭驱动进程")
    public void supperAfterSuite() {
        // 整个套件执行完成后，才能关闭资源
        auto.ui.quit();
        System.out.println("\n--------> 关闭WebDriver结束\n");
    }

}
