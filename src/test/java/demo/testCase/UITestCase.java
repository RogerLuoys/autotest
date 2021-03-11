package demo.testCase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class UITestCase {

    @Test
    void Test1() {
        WebDriver driver = new ChromeDriver();
        driver.get("");
        WebElement we = driver.findElement(By.tagName(""));
        we.

    }
}
