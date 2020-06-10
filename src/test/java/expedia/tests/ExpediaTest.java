package expedia.tests;

import expedia.data.TestData;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ExpediaTest extends TestData {


    @Test(description = "Test to book a Single Room in Hampton Inn Brooklyn Downtown NY")
    public void tc001_verifyBookHotelInHamptonInn(){
        hotelListPage=expediaHomePage.setDestinationForBookingRoom(destinationLocation);
        hotelListPage.verifyUserNavigatedToSelectedDestinationPage(destinationLocation);
        hotelListPage.selectRoomBasedOnInn(nameOfInn);
        hotelListPage.reserveRoomBasedOnPreference(choice,priority);
        hotelListPage.completeBookingAsGuest();
        hotelListPage.verifyUserableTobookRoom(expectedErrorText);
    }


}
