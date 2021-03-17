package api.impl;

import api.UI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumImpl implements UI {


    private static final Long DEFAULT_WAIT_TIME = 30L;
    private WebDriver driver = null;
    private Long forceTimeOut = 1L;

    @Override
    public void init(String url) {
        this.driver = new ChromeDriver();
        this.driver.get(url);
        this.driver.manage().window().maximize();
    }

    @Override
    public void quit() {
        if (driver == null) {
            return;
        }
        driver.close();
        driver.quit();
    }

    @Override
    public void forceWait(Long second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setTimeout(Long second) {
        this.forceTimeOut = second;
    }

    @Override
    public void click(By locator) {
        forceWait(forceTimeOut);
        WebElement webElement = driver.findElement(locator);
        WebDriverWait webDriverWait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
        Actions actions = new Actions(driver);
        actions.click(webElement);
        actions.perform();
    }

    @Override
    public void clickByXpath(String xpath) {
        click(By.xpath(xpath));
    }

    @Override
    public void sendKey(By locator, CharSequence key) {
        forceWait(forceTimeOut);
        WebElement webElement = driver.findElement(locator);
        WebDriverWait webDriverWait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
        webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
        Actions actions = new Actions(driver);
        actions.sendKeys(webElement, key);
        actions.perform();
    }

    @Override
    public void sendKeyByXpath(String xpath, CharSequence key) {
        sendKey(By.xpath(xpath), key);
    }

    @Override
    public void moveToElement(By locator) {
        forceWait(forceTimeOut);
        WebElement webElement = driver.findElement(locator);
        WebDriverWait webDriverWait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
        webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
        Actions actions = new Actions(driver);
        actions.moveToElement(webElement);
        actions.perform();
    }

    @Override
    public void moveToElementByXpath(String xpath) {
        moveToElement(By.xpath(xpath));
    }

    @Override
    public void moveAndClick(By locator) {
        forceWait(forceTimeOut);
        WebElement webElement = driver.findElement(locator);
        WebDriverWait webDriverWait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
        Actions actions = new Actions(driver);
        actions.moveToElement(webElement).click();
        actions.perform();
    }

    @Override
    public void moveAndClickXpath(String xpath) {
        moveAndClick(By.xpath(xpath));
    }
}
