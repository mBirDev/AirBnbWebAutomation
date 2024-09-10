package pageObjects;

import base.BaseTest;
import org.openqa.selenium.support.PageFactory;
import org.pageObjects.BookingPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BookingPageTest extends BaseTest {

    protected BookingPage bookingPage;
    @BeforeMethod
    protected void setUp() {
        bookingPage = PageFactory.initElements(driver, BookingPage.class);
    }
    @DataProvider(name = "bookingDetailsProvider")
    public Object[][] provideBookingDetails() {
        return new Object[][] {
                { "Cabins", "2025-01-22", "2025-01-25"}
        };
    }

    @Test(dataProvider = "bookingDetailsProvider", priority = 0)
    public void shouldReserveBookingSuccessfully(String categoryName, String checkInDate, String checkOutDate ){
        bookingPage.reserveBooking(categoryName,checkInDate,checkOutDate);
    }
}
