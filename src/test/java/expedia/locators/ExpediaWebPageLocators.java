package expedia.locators;

import org.openqa.selenium.By;

public interface ExpediaWebPageLocators extends CommonLocators {



    public By destinationInputLocator = By.xpath("//span[text()='Going to']/following-sibling::input[@id='hotel-destination-hp-hotel']");
    public By searchBtnLocator = By.xpath("(//span[text()='Search']/parent::button[@type='submit'])[2]");
    public By ListedLocationsInListLocator = By.xpath("//ul[@id='typeaheadDataPlain']/descendant::a[@data-value='%s']");

    public By DestinationInputLocatorForDifferentUI = By.xpath("//input[@id='location-field-destination-input']/following-sibling::button");
    public By DestinationInputLocatorInAlertForDifferentUI = By.name("foo");
    public By DestinationInDropDownLocator = By.xpath("//span[normalize-space()='%s']/ancestor::button");
    public By SearchButtonLocatorForDifferentUI = By.xpath("//button[@type='submit' and text()='Search']");

    public By SpecificInnForRoomBookingLocator = By.xpath("//h3[normalize-space()='%s']/parent::div/descendant::a[@data-stid='open-hotel-information']");
    public By preferenceLocatorForRoomBooking = By.xpath("(//span[text()='Room, 1 King Bed, Accessible, Non Smoking']/parent::button[@type='submit'])[2]");
    public By SignInBuuttonForValidationLocator = By.id("gc-custom-header-nav-bar-acct-menu");
    public By ReserveRoomBtnLocator=By.xpath("(//div[@data-stid='reserve-button']/child::button[@data-hpv-navigate='rate'])[2]");
    public By ReserveRoomBasedOnSelectionBtnLocator = By.xpath("//h3[text()='Room, 1 King Bed, Accessible, Non Smoking']/parent::div/following-sibling::div/descendant::button[@type='submit'][last()]");
    public By CompleteBookingBtnLocator = By.xpath("//div[@id='rules-and-restrictions']/following-sibling::button[@id='complete-booking']");

    public By ErrorMessageTxtLocator = By.cssSelector("#hotel-contact-name0+p");

    public By ContinueAsGuestLinkLocator = By.cssSelector("a[data-stid='checkoutAsGuest-button']");

    public By SerchPropertyBtnLocator = By.cssSelector("input[name='hotelName']+button");
    public By SearchPropertyInputLocator = By.cssSelector("input[name='foo'][placeholder*='eg']");
    public By PropertyListedItemLocator = By.xpath("//strong[text()='%s']/ancestor::button");
    public By PayAtPropertyBtnLocator = By.xpath("//div[@class='payment-options__button']/descendant::button[text()='Pay at property']");
    public By ModalDialogCloseLocator=By.xpath("//div[@class='modal-inner modal-dismiss']/descendant::button[@id='free-cancellation-close-btn']");

}
