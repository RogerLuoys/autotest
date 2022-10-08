package autotest.ui.baidu;

import autotest.ui.UITestBase;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaiDuTestBase extends UITestBase {

    @BeforeClass(description = "访问登录页并用自动化专用账号登录", alwaysRun = true)
    public void supperBeforeClass() {
        System.out.println("\n--------> 开始执行百度 UI 前置步骤\n");

        auto.ui.clearCookies();
        auto.ui.openUrl(auto.config.WEB_BAIDU_URL);
        auto.util.sleep(3);

        System.out.println("\n--------> 开始执行百度 UI 自动化用例\n");
    }

    @AfterClass(description = "用例执行结束", alwaysRun = true)
    public void supperAfterClass() {
        System.out.println("\n--------> 完成百度 UI 自动化用例\n");
    }

}
