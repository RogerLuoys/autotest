package autotest.ui.baidu.search;

import autotest.ui.baidu.BaiDuTestBase;
import org.testng.annotations.Test;

public class SearchTest extends BaiDuTestBase {

    @Test
    public void test() {
        auto.ui.sendKey("//input[@id='kw']", "测试");
        auto.ui.sendKey("//input[@id='kw']", "{ENTER}");
        auto.util.sleep(3);
        auto.assertion.isElementExist("//*[@aria-label='测试，汉语词语，百度百科']");
    }
}
