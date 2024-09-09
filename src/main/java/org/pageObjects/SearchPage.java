package org.pageObjects;

import org.base.AirBnbWebPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class SearchPage extends AirBnbWebPage {
    private static final String LOCATION = "United States";
    public SearchPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@name='query' and @placeholder='Search destinations']")
    public WebElement destinationInputField;

    @FindBy(xpath = "//div[@id='bigsearch-query-location-listbox']/div")
    protected List<WebElement> locationSuggestions;

    @FindBy(xpath = "//div[@data-testid='2024-09-10']")
    protected WebElement checkInDate;

    @FindBy(xpath = "//div[@data-testid='2024-09-13']")
    protected WebElement checkOutDate;

    @FindBy(xpath = "//div[@role='application']/div[2]/div/div[2]/div/div/section/h2")
    protected WebElement dateResultElement;

    @FindBy(xpath = "//div[@data-testid='structured-search-input-field-guests-button']")
    protected WebElement addGuestButton;
    @FindBy(xpath = "//div[@data-testid='structured-search-input-field-guests-button']/div/div[2]")
    protected WebElement getNumberOfGuestsAdded;
    @FindBy(xpath = "//button[@data-testid='stepper-adults-increase-button']")
    protected WebElement addAdultButton;
    @FindBy(xpath = "//button[@data-testid='structured-search-input-search-button']")
    protected WebElement searchButton;

    public void setDestination(String destination) {
        waitForElementToBeVisible(destinationInputField);
        destinationInputField.clear();
        destinationInputField.sendKeys(destination);
        waitForVisibilityOfElements(locationSuggestions);
        selectLocationSuggestion(LOCATION);
    }

    private void selectLocationSuggestion(String location) {
        for (WebElement suggestion : locationSuggestions) {
            if (suggestion.getText().contains(location)) {
                suggestion.click();
                return;
            }
        }
        System.err.println("Location suggestion not found: " + location);
    }
    public void enterCheckInDate() {
        clickDate(checkInDate);
    }
    public void enterCheckOutDate() {
        clickDate(checkOutDate);
    }

    public String getElementText(WebElement element) {
        waitForElementToBeVisible(element);
        return element.getText();
    }
    public String getCheckInDateText() {
        return getElementText(checkInDate);
    }
    public String getCheckOutDateText() {
        return getElementText(checkOutDate);
    }
    public String getDateResultText() {
        return getElementText(dateResultElement);
    }
    public String getNumberOfGuestsAddedText() {
        waitForElementToBeVisible(getNumberOfGuestsAdded);
        return getElementText(getNumberOfGuestsAdded);
    }


    public void clickDate(WebElement dateElement) {
        try {
            if (dateElement.isDisplayed()) {
                dateElement.click();
            } else {
                System.out.println("Element is not clickable");
            }
        } catch (Exception e) {
            System.err.println("Exception occurred while clicking on date element: " + e.getMessage());
        }
    }

    public void addGuestsOption() {
        try {
            clickElement(addGuestButton);
            clickElement(addAdultButton);
            clickElement(searchButton);
        } catch (Exception e) {
            System.err.println("Exception occurred while adding guests: " + e.getMessage());
        }
    }
}



