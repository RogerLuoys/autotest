package autotest.ui.baidu.navigation;

import autotest.ui.baidu.BaiDuTestBase;
import org.testng.annotations.Test;

public class NetDisk extends BaiDuTestBase {

    @Test(description = "点首页网盘入口")
    public void test() {
        auto.ui.click("//*[text()='网盘']");
        auto.util.sleep(2);
        auto.ui.switchTab();
        auto.assertion.isElementExist("//*[text()=' 数据管理 ']");
    }

}
