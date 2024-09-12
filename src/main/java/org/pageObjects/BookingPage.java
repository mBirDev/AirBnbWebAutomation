package org.pageObjects;

import org.base.AirBnbWebPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class BookingPage extends AirBnbWebPage {
    private final static int IMAGE_INDEX_VALUE = 2;
    private final static int NUMBER_OF_GUESTS_TO_BE_ADDED = 2;
    public BookingPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div[id='categoryScroller'] div[role='group'] > div:nth-child(3) label")
    protected List<WebElement> categoryLabels;

    @FindBy(xpath = "//div[@role='presentation']/picture/img")
    protected List<WebElement> imageElements;

    @FindBy(xpath = "//button[@aria-label='Close']")
    protected WebElement cancelPopupButton;

    @FindBy(xpath ="//div[@data-testid='change-dates-checkIn']")
    protected WebElement dateCheckInButton;

    @FindBy(xpath ="//input[@id='checkIn-book_it']")
    protected WebElement checkInDateInput;

    @FindBy(xpath ="//input[@id='checkOut-book_it']")
    protected WebElement checkOutDateInput;

    @FindBy(xpath = "//button[@data-testid='availability-calendar-save']")
    protected WebElement closeDateTabButton;

    @FindBy(xpath = "//div[@id='GuestPicker-book_it-trigger']")
    protected WebElement guestPickerTrigger;

    @FindBy(xpath = "//button[starts-with(@data-testid, 'GuestPicker-book_it-form-') and contains(@data-testid, '-stepper-increase-button')]")
    protected List<WebElement> increaseGuestsButton;

    @FindBy(xpath = "//button[contains(text(), 'Close')]")
    protected WebElement closeGuestPickerButton;

    @FindBy(xpath = "(//span[contains(text(), 'Reserve')])[last()]")
    protected WebElement getReserveBookingMessage;

    private void selectCategoryByName(String categoryName) {
        waitForVisibilityOfElements(categoryLabels);
        Optional<WebElement> categoryElement = categoryLabels.stream()
                .filter(label -> label.getText().contains(categoryName))
                .findFirst();

        categoryElement.ifPresentOrElse(
                element -> {element.click(); },
                () -> {
                    System.err.println("Category not found: " + categoryName);
                }
        );
    }

    private void clickImageAtIndex(int index) {
        if (isIndexValid(index)) {
            WebElement imageToClick = imageElements.get(index);
            waitForElementToBeClickable(imageToClick);
            clickElement(imageToClick);
        } else {
            System.err.println("Index out of bounds: " + index);
        }
    }
    private boolean isIndexValid(int index) {
        return index >= 0 && index < imageElements.size();
    }
    public void closeCancelPopupIfPresent(){
        try{
            switchToNewWindow();
            if(cancelPopupButton.isEnabled() || cancelPopupButton.isDisplayed()){
                clickElement(cancelPopupButton);
            }
        }catch (TimeoutException e) {
            System.out.println("Cancel popup did not appear.");
        } catch(Exception e){
            System.err.println("Error in cancelTranslationDialogBox: " + e.getMessage());
        }
    }

    public void reserveBooking(String categoryName, String checkInDate, String checkOutDate){
        selectCategoryByName(categoryName);
        clickImageAtIndex(IMAGE_INDEX_VALUE);
        closeCancelPopupIfPresent();
        try{
            switchToNewWindow();
            scrollToElement(dateCheckInButton);
            clickElement(dateCheckInButton);
            enterDates(checkInDateInput, checkInDate);
            enterDates(checkOutDateInput,checkOutDate);
            clickElement(closeDateTabButton);
            setAddNumberOfGuests(NUMBER_OF_GUESTS_TO_BE_ADDED);
        }catch(Exception e){
            System.err.println("Error in booking a room: " + e.getMessage());
        }
    }

    private void enterDates(WebElement dateInputElement, String date){
        dateInputElement.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        dateInputElement.sendKeys(date);
        clickElement(dateInputElement);
    }

    private void setAddNumberOfGuests(int numberOfGuestsToAdd){
        clickElement(guestPickerTrigger);
        if (numberOfGuestsToAdd > 0) {
            for (int i = 1; i < numberOfGuestsToAdd; i++) {
                clickElement(increaseGuestsButton.get(i));
            }
        }
        clickElement(closeGuestPickerButton);
    }
    public String displayBookingSuccessMessage(){
        return getReserveBookingMessage.getText();
    }
}

