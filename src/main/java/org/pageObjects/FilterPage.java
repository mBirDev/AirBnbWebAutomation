package org.pageObjects;

import org.base.AirBnbWebPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FilterPage extends AirBnbWebPage {

    public FilterPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[contains(text(),'Trending')]")
    protected WebElement trendingButton;
    @FindBy(xpath = "//button[@data-testid='category-bar-filter-button']/span")
    protected WebElement filterButton;
    @FindBy(css = "div[role='dialog'] > div:last-child > div > div:first-child section[role='group'] > div > div:last-child > div:first-child > div[role='radio']")
    protected List<WebElement> typeOfPlaceButton;
    @FindBy(xpath = "//input[@id='price_filter_max']")
    protected WebElement maxPriceRangeInputField;
    @FindBy(css = "div[role='dialog'] > div:last-child > div > div:nth-child(3) button[type='button']")
    protected List<WebElement> roomsAndBedsOption;
    @FindBy(css = "div[role='dialog'] > div:last-child > div > div:nth-child(4) section[role='group'] > div:nth-child(3) > div:last-child > div")
    protected List<WebElement> amenitiesOptions;
    @FindBy(css = "div[role='dialog'] > div:last-child > div > div:nth-child(5) section[role='group'] > div:nth-child(3) > div:last-child > div")
    protected List<WebElement> bookingOptions;
    @FindBy(css = "div[role='dialog'] > div:last-child > div > div:nth-child(6) button[type='button']")
    protected WebElement guestFavouriteButton;
    @FindBy(css = "div[role='dialog'] > div:last-child > div > div:nth-child(7) div:nth-child(2)")
    protected WebElement propertyTypeToggleButton;
    @FindBy(css = "div[role='dialog'] > div:last-child > div > div:nth-child(7) button[type='button']")
    protected List<WebElement> propertyTypeOptions;
    @FindBy(css = "div[role='dialog'] > div:last-child footer > div")
    protected WebElement showResults;
    @FindBy(xpath = "//div[contains(text(),'Continue exploring trending homes')]")
    protected WebElement resultAfterSearch;

    public void applyFilter(String placeType, int maxPrice, String amenity,String bookingOption,String propertyType) {
        clickElement(trendingButton);
        clickElement(filterButton);
        selectPlaceType(placeType);
        enterPriceRange(maxPrice);
        selectRoomsAndBeds();
        selectAmenity(amenity);
        selectBookingOption(bookingOption);
        clickElement(guestFavouriteButton);
        clickElement(propertyTypeToggleButton);
        selectPropertyType(propertyType);
        clickElement(showResults);
    }

    public void selectPlaceType(String placeType) {
        WebElement typeOption = findElementByText(typeOfPlaceButton, placeType);
        if (typeOption != null) {
            clickElement(typeOption);
        }
    }

    public void selectAmenity(String amenity) {
        WebElement amenityOption = findElementByText(amenitiesOptions, amenity);
        if (amenityOption != null) {
            clickElement(amenityOption);
        }
    }

    public void selectBookingOption(String bookingOption) {
        WebElement option = findElementByText(bookingOptions, bookingOption);
        if (option != null) {
            clickElement(option);
        }
    }
    public void selectPropertyType(String propertyType) {
        WebElement option = findElementByText(propertyTypeOptions, propertyType);
        if (option != null) {
            clickElement(option);
        }
    }

    public void enterPriceRange(int maxPrice) {
        maxPriceRangeInputField.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        clickElement(maxPriceRangeInputField);
        maxPriceRangeInputField.sendKeys(String.valueOf(maxPrice));
    }

    public void selectRoomsAndBeds() {
        for (int i = 0; i < roomsAndBedsOption.size(); i++) {
            if (i % 2 != 0) {
                clickElement(roomsAndBedsOption.get(i));
            }
        }
    }
    public String displayMessage(){
        return resultAfterSearch.getText();
    }

    private WebElement findElementByText(List<WebElement> elements, String text) {
        for (WebElement element : elements) {
            if (element.getText().contains(text)) {
                return element;
            }
        }
        return null;
    }

}
