package pageObjects;

import base.BaseTest;
import org.openqa.selenium.support.PageFactory;
import org.pageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import util.ConfigurationManager;

public class LoginPageTest extends BaseTest {

    protected LoginPage loginPage;
    @BeforeMethod
    protected void setUp() {
        loginPage = PageFactory.initElements(driver, LoginPage.class);
    }
    @Test(priority = 1)
    public void verifyLogin(){
        loginPage.openHeaderNavigation();
        loginPage.initiateLogin();
        loginPage.completeLogin(
                ConfigurationManager.getProperty("email"),
                ConfigurationManager.getProperty("pwd"));
        Assert.assertTrue(loginPage.isLogoPresent()
                , "Logo is not present after login.");
    }
}
