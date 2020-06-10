package expedia.utilities;

import org.openqa.selenium.By;

public class Dynamic {

    public static By getNewLocator(By locator, String dynamicText)
    {
        String locatorType = locator.toString().split(": ")[0].split("\\.")[1];
        String newLocatorString = String.format(locator.toString().split(": ")[1],dynamicText);
        switch(locatorType)
        {
            case "xpath":
                locator = By.xpath(newLocatorString);
                System.out.println("Updated Locator :"+locator);
                break;
            case "cssSelector":
                locator = By.cssSelector(newLocatorString);
                break;
            case "id":
                locator = By.id(newLocatorString);
                break;
            case "className":
                locator = By.className(newLocatorString);
                break;
            case "name":
                locator = By.name(newLocatorString);
                break;
            case "linkText":
                locator = By.linkText(newLocatorString);
                break;
            case "partialLinkText":
                locator = By.partialLinkText(newLocatorString);
                break;
            case "tagName":
                locator = By.tagName(newLocatorString);
                break;
        }
        return locator;
    }


}
