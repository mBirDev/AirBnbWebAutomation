package pageObjects;

import base.BaseTest;
import org.openqa.selenium.support.PageFactory;
import org.pageObjects.SearchPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import util.ConfigurationManager;

public class SearchPageTest extends BaseTest {

    protected SearchPage searchPage;
    private static final String DESTINATION = "Florida";

    @BeforeMethod
    protected void setUp() {
        searchPage = PageFactory.initElements(driver,SearchPage.class);
    }
    @Test(priority = 0)
    public void testSearchDestination() {
        searchPage.setDestination(DESTINATION);
        Assert.assertNotNull(searchPage.destinationInputField);

    }
    @Test(priority = 1)
    public void testCheckInDate(){
        searchPage.enterCheckInDate();
        String actualCheckInDate = searchPage.getCheckInDateText();
        String actualDateResult = searchPage.getDateResultText();
        Assert.assertEquals(formatDateString(actualCheckInDate, actualDateResult),
                ConfigurationManager.getProperty("expectedCheckInDate"),
                "Check-in date does not match the expected date.");

    }
    @Test(priority = 2)
    public void testCheckOutDate(){
        searchPage.enterCheckOutDate();
        String actualCheckOutDate = searchPage.getCheckOutDateText();
        String actualDateResult = searchPage.getDateResultText();
        Assert.assertEquals(formatDateString(actualCheckOutDate, actualDateResult),
                ConfigurationManager.getProperty("expectedCheckOutDate"),
                "Check-out date does not match the expected date.");
    }
    private String formatDateString(String dateText, String resultText) {
        return dateText + " " + resultText;
    }
    @Test(priority = 3)
    public void testAddGuests(){
        searchPage.addGuestsOption();
        String actualNumberOfGuestsAdded = searchPage.getNumberOfGuestsAddedText();
        Assert.assertEquals(actualNumberOfGuestsAdded,
                ConfigurationManager.getProperty("expectedGuests"),
                "Number of guests added does not match the expected number.");
    }
}
