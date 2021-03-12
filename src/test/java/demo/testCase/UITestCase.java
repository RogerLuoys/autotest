package demo.testCase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.Date;

public class UITestCase {

    @Test
    void Test1() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.upgradeflag.top/");
//        WebElement we = driver.findElement(By.tagName(""));
//        we.
        System.out.println(new Date());
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='使用访客账号']")));
        System.out.println(new Date());
        Thread.sleep(30000);
        Actions actions = new Actions(driver);
//        actions.contextClick(driver.findElement(By.xpath("//span[text()='使用访客账号']")));
        driver.findElement(By.xpath("//span[text()='使用访客账号']")).click();
        System.out.println(new Date());
        System.out.println("登录成功");
        Thread.sleep(30000);
        System.out.println(new Date());
        driver.close();
        driver.quit();
    }
}
