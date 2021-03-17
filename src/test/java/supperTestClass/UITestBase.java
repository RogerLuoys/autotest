package supperTestClass;

import base.FullAutoAPI;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class UITestBase {
    public FullAutoAPI auto = new FullAutoAPI();

    public final static String URL = "http://118.24.117.181/";

    @BeforeClass
    public void startBrowser() {
        auto.ui.init(URL);
        auto.ui.forceWait(10L);
    }

    @AfterClass
    public void quitDriver() {
        auto.ui.quit();
    }
}
