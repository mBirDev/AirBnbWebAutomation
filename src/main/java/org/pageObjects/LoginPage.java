package org.pageObjects;

import org.base.AirBnbWebPage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AirBnbWebPage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@data-testid='cypress-headernav-profile']")
    protected WebElement headerNavButton;

    @FindBy(xpath = "//header/div/div/a")
    protected WebElement airBnbLogo;

    @FindBy(xpath = "//a[@data-testid='cypress-headernav-login']")
    protected WebElement loginButton;

    @FindBy(xpath = "//button[@data-testid='social-auth-button-google']")
    protected WebElement loginViaGoogleButton;

    @FindBy(xpath = "//input[@name='identifier']")
    protected WebElement emailInputField;

    @FindBy(css = "div[id='identifierNext'] span")
    protected WebElement continueButtonAfterEmail;

    @FindBy(css = "div[id='passwordNext'] span")
    protected WebElement continueButtonAfterPassword;

    @FindBy(css = "input[name='Passwd']")
    protected WebElement passwdInputField;

    public boolean isLogoPresent(){
        return airBnbLogo.isDisplayed();
    }

    public void openHeaderNavigation(){
        waitForElementToBeVisible(headerNavButton);
        clickElement(headerNavButton);
    }
    public void initiateLogin(){
        loginButton.click();
        clickElement(loginViaGoogleButton);
    }
    public void completeLogin(String email, String pwd){
        switchToNewWindow();
        emailInputField.sendKeys(email);
        clickContinueButton();
        passwdInputField.sendKeys(pwd);
        clickContinueButton();
    }
    private void clickContinueButton() {
        if (isElementPresent(continueButtonAfterEmail)) {
            continueButtonAfterEmail.click();
        } else if (isElementPresent(continueButtonAfterPassword)) {
            continueButtonAfterPassword.click();
        } else {
            throw new NoSuchElementException("Continue button not found.");
        }
    }
    private boolean isElementPresent(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
