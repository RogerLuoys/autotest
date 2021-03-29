package testBase.ui;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.Date;

public class UITestBase {
    public final static UIFullAPI auto = new UIFullAPI();
    public final static String URL = "http://118.24.117.181/";

    @BeforeClass(description = "启动浏览器并用自动化专用账号登录")
    public void supperBeforeClass() {
        auto.ui.init(URL);
        auto.ui.forceWait(3L);
        auto.task.login("autoTester", "123456");
        System.out.println("******************启动浏览器"+new Date());
    }

    @BeforeMethod(description = "每次执行用例前刷新到主页")
    public void supperBeforeMethod() {
        auto.ui.refresh(URL);
        System.out.println("******************刷新一次"+new Date());
    }

    @AfterClass(alwaysRun = true, description = "关闭浏览器且关闭驱动进程")
    public void supperAfterClass() {
        auto.ui.quit();
    }
}
