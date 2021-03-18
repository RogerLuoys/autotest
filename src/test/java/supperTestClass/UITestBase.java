package supperTestClass;

import base.FullAutoAPI;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class UITestBase {
    public final static UIFullAPI auto = new UIFullAPI();
    public final static String URL = "http://118.24.117.181/";

    @BeforeClass
    public void startBrowser() {
        auto.ui.init(URL);
        auto.ui.forceWait(10L);
        auto.task.login("autoTester", "123456");
    }

    @AfterClass
    public void quitDriver() {
        auto.ui.quit();
    }
}
