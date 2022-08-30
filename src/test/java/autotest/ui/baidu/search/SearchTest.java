package autotest.ui.baidu.search;

import autotest.ui.baidu.BaiDuTestBase;
import org.testng.annotations.Test;

public class SearchTest extends BaiDuTestBase {

    @Test
    public void test() {
        auto.ui.openUrl("www.baidu.com");
    }
}
