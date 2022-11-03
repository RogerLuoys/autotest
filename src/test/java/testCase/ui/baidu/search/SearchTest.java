package testCase.ui.baidu.search;

import testCase.ui.baidu.BaiDuTestBase;
import org.testng.annotations.Test;

public class SearchTest extends BaiDuTestBase {

    @Test(description = "校验搜索")
    public void test() {
        auto.ui.sendKey("//input[@id='kw']", "测试");
        auto.ui.sendKey("//input[@id='kw']", "{ENTER}");
        auto.util.sleep(3);
        auto.assertion.isElementExist("//em[text()='测试']");
        auto.assertion.isElementExist("//*[text()='广告']");
    }
}
