package expedia.pages;

import expedia.locators.ExpediaWebPageLocators;
import expedia.utilities.Dynamic;
import expedia.utilities.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ExpediaHomePage implements ExpediaWebPageLocators {

    public WebDriver driver;
    public Utils utils;

    public HotelsListPage hotelsListPage;


    public ExpediaHomePage(WebDriver driver) {
        this.driver = driver;
        utils = new Utils(driver);

    }


    /**
     * Method is to Book A room in Specific Inn
     *
     * @param destinationLocation
     * @param nameOfInn
     * @param expectedText
     * @param choice
     * @param priority
     */
    public void verifyBookingAvailabilityInSpecifiedCity(String destinationLocation, String nameOfInn, String expectedText, String choice, String priority) {


        //Since UI Is different ,Performing actions accoring to UI
        if (utils.isElementDisplayed(destinationInputLocator, "destination Input field", SHORT_WAIT)) {
            utils.sendKeys(destinationInputLocator, destinationLocation, "destination Input field", MEDIUM_WAIT);
            utils.safeNormalClick(Dynamic.getNewLocator(ListedLocationsInListLocator, destinationLocation), "Location In Search Results", MEDIUM_WAIT);
            utils.safeNormalClick(searchBtnLocator, "Search Button", MEDIUM_WAIT);

        } else if (utils.isElementDisplayed(DestinationInputLocatorForDifferentUI, "destination Input", SHORT_WAIT)) {
            utils.safeNormalClick(DestinationInputLocatorForDifferentUI, "destination Input", MEDIUM_WAIT);
            utils.sendKeys(DestinationInputLocatorInAlertForDifferentUI, destinationLocation, "Destination Place", MEDIUM_WAIT);
            utils.safeNormalClick(Dynamic.getNewLocator(DestinationInDropDownLocator, destinationLocation), "Destination Place", MEDIUM_WAIT);
            utils.safeNormalClick(SearchButtonLocatorForDifferentUI, "Search button", MEDIUM_WAIT);
        }

        utils.isElementDisplayed(SignInBuuttonForValidationLocator, "Sign In Button", LONG_WAIT);
        utils.safeNormalClick(SerchPropertyBtnLocator, "Search Property Button", MEDIUM_WAIT);
        utils.sendKeys(SearchPropertyInputLocator, nameOfInn, "Search Property Input", MEDIUM_WAIT);
        utils.safeNormalClick(Dynamic.getNewLocator(PropertyListedItemLocator, nameOfInn), "Searched Property item in the list", MEDIUM_WAIT);

        utils.safeNormalClick(Dynamic.getNewLocator(SpecificInnForRoomBookingLocator, nameOfInn), nameOfInn, MEDIUM_WAIT);
        utils.switchToWindow(driver);

        utils.isElementDisplayed(SignInBuuttonForValidationLocator, "Sign In Button", LONG_WAIT);
        if (choice.equalsIgnoreCase("yes") && utils.isElementDisplayed(Dynamic.getNewLocator(ReserveRoomBasedOnSelectionBtnLocator, priority), "Reserve Room ", SHORT_WAIT)) {
            utils.safeNormalClick(ReserveRoomBasedOnSelectionBtnLocator, "Reserve Room ", SHORT_WAIT);
        } else {
            utils.safeNormalClick(ReserveRoomBtnLocator, "Reserve Room ", SHORT_WAIT);
        }
        utils.safeNormalClick(PayAtPropertyBtnLocator, "Pay At Property Button", SHORT_WAIT);
        if (utils.isElementDisplayed(ContinueAsGuestLinkLocator, "Continue as Guest Element", SHORT_WAIT)) {
            utils.safeNormalClick(ContinueAsGuestLinkLocator, "Continue as Guest Element", VERY_SHORT_WAIT);
        }
        if (utils.isElementDisplayed(ModalDialogCloseLocator, "ModalDialog", MEDIUM_WAIT)) {
            utils.safeNormalClick(ModalDialogCloseLocator, "ModalDialog", VERY_SHORT_WAIT);
        }
        utils.safeNormalClick(CompleteBookingBtnLocator, "Complete Booking Button", MEDIUM_WAIT);

        //Expecting Error message as part of test case
        String errorText = utils.getText(ErrorMessageTxtLocator, "Error Message", MEDIUM_WAIT);
        Assert.assertEquals(errorText, expectedText);
    }

    public HotelsListPage setDestinationForBookingRoom(String destinationLocation) {
        if (utils.isElementDisplayed(destinationInputLocator, "destination Input field", SHORT_WAIT)) {
            utils.sendKeys(destinationInputLocator, destinationLocation, "destination Input field", MEDIUM_WAIT);
            utils.safeNormalClick(Dynamic.getNewLocator(ListedLocationsInListLocator, destinationLocation), "Location In Search Results", MEDIUM_WAIT);
            utils.safeNormalClick(searchBtnLocator, "Search Button", MEDIUM_WAIT);

        } else if (utils.isElementDisplayed(DestinationInputLocatorForDifferentUI, "destination Input", SHORT_WAIT)) {
            utils.safeNormalClick(DestinationInputLocatorForDifferentUI, "destination Input", MEDIUM_WAIT);
            utils.sendKeys(DestinationInputLocatorInAlertForDifferentUI, destinationLocation, "Destination Place", MEDIUM_WAIT);
            utils.safeNormalClick(Dynamic.getNewLocator(DestinationInDropDownLocator, destinationLocation), "Destination Place", MEDIUM_WAIT);
            utils.safeNormalClick(SearchButtonLocatorForDifferentUI, "Search button", MEDIUM_WAIT);
        }
        hotelsListPage = new HotelsListPage(driver);
        return hotelsListPage;

    }
}
