package demo.testCase;

import demo.testBase.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;

public class UITestCase extends TestBase {

    @Test
    void Test1(){
        auto.ui.init(UI_URL);
        auto.ui.click("//span[text()='*****']");
        Assert.assertTrue(auto.ui.isElementExist(""), "***");
        auto.ui.quit();
    }

    @Test
    void Test2(){
        auto.ui.init(UI_URL);
        auto.task.login();
        Assert.assertTrue(auto.ui.isElementExist(""), "***");
        auto.ui.quit();
    }
}
