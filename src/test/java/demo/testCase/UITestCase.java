package demo.testCase;

import demo.testBase.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class UITestCase extends TestBase {

    @BeforeClass
    void beforeClass() {
        auto.db.delete("sql");
    }

    @Test
    void Test1(){
        auto.ui.init(auto.config.UI_URL);
        auto.ui.click("//span[text()='*****']");
        Assert.assertTrue(auto.ui.isElementExist(""), "***");
        auto.ui.quit();
    }

    @Test
    void Test2(){
        auto.ui.init(auto.config.UI_URL);
        auto.task.login();
        Assert.assertTrue(auto.ui.isElementExist(""), "***");
        auto.ui.quit();
    }

    @AfterClass
    void afterClass() {
        auto.db.delete("sql");
    }

}
