package org.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class AirBnbWebPage {
    public static final int SHORT_WAIT_TIME = 3;
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions action;

    public AirBnbWebPage(WebDriver driver){
        this.driver = driver;
        action = new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public WebElement waitForElementToBeClickable(WebElement element){
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitForElementToBeVisible(WebElement element){
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void waitForVisibilityOfElements(List<WebElement> webElements) {
        wait.until(ExpectedConditions.visibilityOfAllElements(webElements));
    }
    public void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void clickElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

}
