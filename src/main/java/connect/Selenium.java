package connect;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Selenium {
    WebDriver driver = new ChromeDriver();
    public void test1() {
        driver.get("");
        driver.findElement(By.tagName(""));
    }

}