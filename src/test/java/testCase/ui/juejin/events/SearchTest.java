package testCase.ui.juejin.events;

import org.testng.annotations.Test;
import testCase.ui.juejin.JueJinTestBase;

public class SearchTest extends JueJinTestBase {

    @Test
    public void test() {
        auto.ui.openUrl("events/all");
        auto.ui.sendAndEnter("//input[@type='search']", "测试");
        auto.util.sleep(2);
        auto.assertion.isElementExist("//*[contains(text(),'自动化')]");
        auto.assertion.isElementExist("//em[text()='测试']");
    }
}
