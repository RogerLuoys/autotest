package autotest.ui.flag;

import autotest.ui.UITestBase;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class FlagTestBase extends UITestBase {


    @BeforeClass(description = "访问登录页并用自动化专用账号登录", alwaysRun = true)
    public void supperBeforeClass() {
        System.out.println("\n--------> 开始执行 Flag UI 前置步骤\n");

        auto.ui.clearCookies();
        auto.ui.openUrl(auto.config.WEB_FLAG_URL);
        auto.util.sleep(5);
        auto.function.loginFlag("UITester", "123456");

        System.out.println("\n--------> 开始执行 Flag UI 自动化用例\n");
    }

    @AfterClass(description = "用例执行结束", alwaysRun = true)
    public void supperAfterClass() {
        System.out.println("\n--------> 完成 Flag UI 自动化用例\n");
    }


//    @BeforeMethod(description = "每次执行用例前刷新到主页")
//    public void supperBeforeMethod() {
//        auto.ui.openUrl(auto.config.WEB_FLAG_URL);
//    }

}
