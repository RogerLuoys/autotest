package autotest.ui.baidu.navigation;

import autotest.ui.baidu.BaiDuTestBase;
import org.testng.annotations.Test;

public class NewsTest extends BaiDuTestBase {

    @Test(description = "点首页新闻入口")
    public void test() {
        auto.ui.click("//*[text()='新闻']");
        auto.util.sleep(2);
        auto.ui.switchTab();
        auto.assertion.isElementExist("//a[text()='热点要闻']");
    }

}