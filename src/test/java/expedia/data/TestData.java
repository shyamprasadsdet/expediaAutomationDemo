package expedia.data;

import expedia.pages.HotelsListPage;
import expedia.utilities.BaseClass;
import org.testng.annotations.Test;

public class TestData  extends BaseClass {

    public String nameOfInn;
    public String destinationLocation;
    public String expectedErrorText,choice,priority;

    public HotelsListPage hotelListPage;

    public TestData(){
        destinationLocation=configManager.getProperty("destinationLocation");
        nameOfInn=configManager.getProperty("nameOfInn");
        expectedErrorText=configManager.getProperty("expectedErrorText");
        choice=configManager.getProperty("choice");
        priority=configManager.getProperty("priority");
    }
}
