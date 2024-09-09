package pageObjects;

import base.BaseTest;
import org.openqa.selenium.support.PageFactory;
import org.pageObjects.FilterPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FilterPageTest extends BaseTest {
    protected FilterPage filterPage;
    @BeforeMethod
    protected void setUp() {
        filterPage = PageFactory.initElements(driver, FilterPage.class);
    }
    @DataProvider(name = "filterData")
    public Object[][] createFilterData() {
        return new Object[][] {
                { "Room", 4000, "Wifi", "Self check-in", "Apartment" },
                { "Entire home", 3000, "Heating", "Instant Book", "House" },
                { "Any type", 1500, "Kitchen", "Self check-in", "Hotel" }
        };
    }
    @Test(dataProvider = "filterData", priority = 0)
    public void testFilter(String placeType, int maxPrice, String amenity, String bookingOption, String propertyType) {
        filterPage.applyFilter(placeType, maxPrice, amenity, bookingOption, propertyType);
        String actualMessage = filterPage.displayMessage();
        String expectedMessage = "Continue exploring trending homes";
        Assert.assertEquals(actualMessage,expectedMessage);
    }
}
