package demo.testCase;

import demo.testBase.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.Date;

public class UITestCase extends TestBase {

    @Test
    void Test1() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("http://118.24.117.181/#/");

        System.out.println(new Date());
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='使用访客账号']")));
        System.out.println(new Date());
//        Thread.sleep(30000);
        WebElement we = driver.findElement(By.xpath("//span[text()='使用访客账号']"));

        Actions actions = new Actions(driver);
        actions.click(driver.findElement(By.xpath("//span[text()='使用访客账号']")));
        actions.perform();
//        driver.findElement(By.xpath("//span[text()='使用访客账号']")).click();
        System.out.println(new Date());
        System.out.println("登录成功");
        Thread.sleep(30000);
        System.out.println(new Date());
        driver.close();
        driver.quit();
    }

    @Test
    void Test2() throws InterruptedException {
        auto.ui.init("http://118.24.117.181/#/");
        System.out.println(new Date());
        auto.ui.clickByXpath("//span[text()='使用访客账号']");
        System.out.println(new Date());
        Thread.sleep(30000);
        System.out.println(new Date());
        auto.ui.quit();
    }

    @Test
    void Test3() throws InterruptedException {
        auto.ui.init("http://118.24.117.181/#/");
        auto.ui.sendKeyByXpath("//input[@placeholder='请输入账号']", "tester");
        auto.ui.sendKeyByXpath("//input[@placeholder='请输入密码']", "123456");
        auto.ui.clickByXpath("//span[text()='登录']");
        Thread.sleep(30000);
        auto.ui.quit();
    }
}
