package supperTestClass.ui;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class UITestBase {
    public final static UIFullAPI auto = new UIFullAPI();
    public final static String URL = "http://118.24.117.181/";

    @BeforeClass(description = "启动浏览器并用自动化账号登录")
    public void startBrowser() {
        auto.ui.init(URL);
        auto.ui.forceWait(3L);
        auto.task.login("autoTester", "123456");
    }

    @AfterClass(alwaysRun = true)
    public void quitDriver() {
        auto.ui.quit();
    }
}
