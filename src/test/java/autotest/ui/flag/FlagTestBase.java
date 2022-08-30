package autotest.ui.flag;

import autotest.ui.UITestBase;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class FlagTestBase extends UITestBase {


    // 用例执行时再开始实例化auto变量，因为用mvn test执行会先把test包下所有类实例化一遍
    @BeforeClass(description = "启动浏览器并用自动化专用账号登录")
    public void supperBeforeClass() {
        auto.ui.openUrl(auto.config.FLAG_URL);
        auto.ui.forceWait(3);
        auto.function.loginFlag("UITester", "123456");
    }

    @BeforeMethod(description = "每次执行用例前刷新到主页")
    public void supperBeforeMethod() {
        auto.ui.openUrl(auto.config.FLAG_URL);
    }

}
