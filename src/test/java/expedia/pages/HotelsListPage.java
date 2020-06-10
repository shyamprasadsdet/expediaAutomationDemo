package expedia.pages;

import expedia.locators.ExpediaWebPageLocators;
import expedia.utilities.ConfigManager;
import expedia.utilities.Dynamic;
import expedia.utilities.Utils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HotelsListPage implements ExpediaWebPageLocators {


    public WebDriver driver;
    public Utils utils;

    public HotelsListPage(WebDriver driver) {
        this.driver = driver;
        utils = new Utils(driver);

    }


    /**
     * Purpose - Method is to select inn based on priority
     *
     * @param nameOfInn
     */
    public void selectRoomBasedOnInn(String nameOfInn) {

        //Had to choose LONG_WAIT since application is not that responsive
        utils.isElementDisplayed(SignInBuuttonForValidationLocator, "Sign In Button", LONG_WAIT);
        utils.safeNormalClick(SerchPropertyBtnLocator, "Search Property Button", LONG_WAIT);
        utils.sendKeys(SearchPropertyInputLocator, nameOfInn, "Search Property Input", MEDIUM_WAIT);
        utils.safeNormalClick(Dynamic.getNewLocator(PropertyListedItemLocator, nameOfInn), "Searched Property item in the list", MEDIUM_WAIT);

        utils.safeNormalClick(Dynamic.getNewLocator(SpecificInnForRoomBookingLocator, nameOfInn), nameOfInn, MEDIUM_WAIT);

    }


    /**
     * Purpose - Method is to reserve the room based on priority
     *
     * @param choice
     * @param priority
     */
    public void reserveRoomBasedOnPreference(String choice, String priority) {

        utils.switchToWindow(driver);
        utils.isElementDisplayed(SignInBuuttonForValidationLocator, "Sign In Button", LONG_WAIT);
        /*if (choice.equalsIgnoreCase("yes") && utils.isElementDisplayed(Dynamic.getNewLocator(ReserveRoomBasedOnSelectionBtnLocator, priority), "Reserve Room ", SHORT_WAIT)) {
            utils.safeNormalClick(Dynamic.getNewLocator(ReserveRoomBasedOnSelectionBtnLocator, priority), "Reserve Room ", SHORT_WAIT);
        } else {
            utils.scrollToElement(ReserveRoomBtnLocator,"Reserve Room ", SHORT_WAIT);
            utils.safeNormalClick(ReserveRoomBtnLocator, "Reserve Room ", MEDIUM_WAIT);
        }*/

        utils.safeJavaScriptClick(Dynamic.getNewLocator(ReserveRoomBasedOnSelectionBtnLocator, priority), "Reserve Room ", LONG_WAIT);

        if (utils.isElementDisplayed(PayAtPropertyBtnLocator, "Pay At Property Button", SHORT_WAIT))
            utils.safeNormalClick(PayAtPropertyBtnLocator, "Pay At Property Button", VERY_SHORT_WAIT);

    }

    /**
     * Purpose - Method is to book a room as guest
     */
    public void completeBookingAsGuest() {
        if (utils.isElementDisplayed(ContinueAsGuestLinkLocator, "Continue as Guest Element", SHORT_WAIT)) {
            utils.safeNormalClick(ContinueAsGuestLinkLocator, "Continue as Guest Element", SHORT_WAIT);
        }
        if (utils.isElementDisplayed(ModalDialogCloseLocator, "ModalDialog", MEDIUM_WAIT)) {
            utils.safeNormalClick(ModalDialogCloseLocator, "ModalDialog", SHORT_WAIT);
        }
        utils.safeJavaScriptClick(CompleteBookingBtnLocator, "Complete Booking Button", MEDIUM_WAIT);

    }

    /**
     * Purpose - Method is to check user is able to book a room
     *
     * @param expectedText
     */
    public void verifyUserableTobookRoom(String expectedText) {
        //Expecting Error message as part of test case
        String errorText = utils.getText(ErrorMessageTxtLocator, "Error Message", MEDIUM_WAIT);
        Assert.assertEquals(errorText, expectedText);
    }

    /**
     * Purpose - Method is to check if user is navigated to hotel listing page based on destination selection
     *
     * @param destination
     */
    public void verifyUserNavigatedToSelectedDestinationPage(String destination) {
        String actualTitle = utils.getTitle(driver);
        boolean isContains = actualTitle.contains(destination);
        Assert.assertTrue(isContains, "User is not navigated to Selected Destination Booking Inn Page. Expected[" + destination + "] Actual[ " + actualTitle + "]");


    }

}
