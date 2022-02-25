package testCaseUI;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

/**
 * 专用账号/密码：UITester/123456
 * 账号userId： 416160586979148
 */
public class UITestBase {
    public final static UICommonProxy auto = new UICommonProxy();

    @BeforeClass(description = "启动浏览器并用自动化专用账号登录")
    public void supperBeforeClass() {
        auto.ui.init(auto.config.URL);
        auto.ui.forceWait(3);
        auto.task.login("UITester", "123456");
    }

    @BeforeMethod(description = "每次执行用例前刷新到主页")
    public void supperBeforeMethod() {
        auto.ui.refresh(auto.config.URL);
    }

    @AfterClass(alwaysRun = true, description = "关闭浏览器且关闭驱动进程")
    public void supperAfterClass() {
        auto.ui.quit();
    }
}
