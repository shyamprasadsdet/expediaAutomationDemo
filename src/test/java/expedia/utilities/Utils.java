package expedia.utilities;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Utils {


    public WebDriver driver;
    public Utils(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPageloading(WebDriver driver) {
        return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
    }

    public void setValue(By element, String eleName, String value) {
        if (isElementPresent(element, eleName, 50)) {
            WebElement ele = driver.findElement(element);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('value',arguments[1])", ele, value);

        }
    }

    public void refreshPage() {
        driver.get(driver.getCurrentUrl());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
    }


    /**
     *
     * @param driver
     * @return
     */
    public String getTitle(WebDriver driver){

        String title=driver.getTitle();
        System.out.println("Current WebPage title :"+driver.getTitle());
        return title;
    }


    public boolean waitForElementToLoad(By element, String sElementName, int waitTime) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitTime);
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        } catch (TimeoutException e) {
            System.out.println("Time out Exception :" + sElementName + " Could not found");
            return false;
        } catch (NoSuchElementException e) {
            System.out.println("Element :" + sElementName + " Could not found");
            return false;
        } catch (Exception e) {
            System.out.println("Element :" + sElementName + " Could not found");
            return false;
        }
        return true;
    }

    public boolean isElementPresent(By element, String sElementName, int waitTime) {
        boolean bFlag = false;
        WebElement ele = null;

        try {
            if (waitForElementToLoad(element, sElementName, waitTime)) {
                ele = driver.findElement(element);
                if (ele.isDisplayed()) {
                    return true;
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("Element :" + sElementName + " Not Present");
            return false;

        } catch (TimeoutException e) {
            System.out.println("Element :" + sElementName + " Not Present");
            return false;
        } catch (Exception e) {
            System.out.println("Element :" + sElementName + " Not Present");
            return false;
        }

        return true;

    }

    public void refreshPage(int num) {

        for (int i = 0; i < num; i++) {
            try {
                driver.navigate().refresh();
            } catch (Exception e) {
                System.out.println("Exception caught while refreshing the page.. Just Ignore it for now");
            }
        }


    }

    public boolean waitForPageToLoad() {
        boolean isPageLoadComplete = false;
        try {
            int waitTime = 0;

            do {
                driver.navigate().refresh();
                isPageLoadComplete = ((String) ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState")).equals("complete");
                //sleep(1);
                waitTime++;
                if (waitTime > 250) {
                    break;
                }

            } while (!isPageLoadComplete);

        } catch (TimeoutException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void safeClick(By element, String sElementName, int optionWaitTime) {
        try {

            if (isElementPresent(element, sElementName, optionWaitTime)) {
                driver.findElement(element).click();
            }
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void safeTypeNormal(By element, String text, String sElementName) {
        try {
            if (isElementPresent(element, sElementName, 50)) {
                driver.findElement(element).sendKeys(text);
            }
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshPageUntilTitleAppears(String expTitle) {
        //

        for (int i = 0; i < 1; i++) {
            try {
                driver.navigate().refresh();
                String actulTitle = driver.getTitle();
                waitForPageToLoad();
                if (actulTitle.equalsIgnoreCase(expTitle)) {
                    System.out.println("Navigated To expected page");
                    break;
                }
            } catch (Exception e) {
                System.out.println("Exception caught while refreshing the page.. Just Ignore it for now");
            }
        }

    }

    public WebElement getWebElement(By locatorType) {
        WebElement element = null;
        try {
            element = driver.findElement(locatorType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return element;
    }

    public void safeNormalClick(By locator,String sElementName,int OptionalTime){

        WebElement element=null;
        try {
            if (isElementDisplayed(locator, sElementName, OptionalTime)) {
                element = driver.findElement(locator);
                System.out.println("Clicked on :"+sElementName);
            }
            element.click();
        }
        catch (TimeoutException e){
            System.out.println("Unable to click on :"+sElementName+" In Give Time :"+OptionalTime);
            e.printStackTrace();
        }
        catch (NoSuchElementException e){
            System.out.println("Unable to Find  :"+sElementName+" In DOM");
            e.printStackTrace();
        }
        catch (Exception e){
            System.out.println("Unable to click on :"+sElementName);
            e.printStackTrace();
        }

    }

    public void sendKeys(By locator,String value,String sElementName,int OptionalTime){

        try {
            if (isElementDisplayed(locator, sElementName, OptionalTime)) {
                WebElement element = driver.findElement(locator);
                element.sendKeys(value);
                System.out.println("Entered value :"+sElementName);
            }
        }
        catch (Exception e){
            System.out.println("Unable to Enter value  on :"+sElementName);
            e.printStackTrace();
        }

    }
    public boolean isElementDisplayed(By locator, String eleName, int optionalTime) {

        boolean value=false;
        try {
            WebDriverWait webDriverWait = new WebDriverWait(driver, optionalTime);
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));

            WebElement ele = driver.findElement(locator);

             value= ele.isDisplayed();
            System.out.println("Element :"+eleName+" is Displayed ?? :"+value);
            return value;

        } catch (Exception e) {

            System.out.println("Element :"+eleName+" is Not Displayed");
        }
        return value;

    }

    public String getText(By locator,String sElementName,int optionalTime){
        String value=null;
        try {
            if(isElementDisplayed(locator,sElementName,optionalTime)){
                WebElement element=driver.findElement(locator);
                value=element.getText();
            }
        }
        catch (Exception e){
            System.out.println("Unable to get text of the element :"+sElementName);
            e.printStackTrace();
        }
        return value;
    }

    public void scrollToElement(By locator,String sElementName,int optionalTime){

       if(isElementDisplayed(locator,sElementName,optionalTime)) {
           WebElement element=driver.findElement(locator);
           JavascriptExecutor js = (JavascriptExecutor) driver;
           js.executeScript("arguments[0].scrollIntoView(true);", element);

       }

    }

    public void switchToWindow(WebDriver driver){

        String parentWindow = driver.getWindowHandle();
        for (String window : driver.getWindowHandles()) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }

    public void setFinalHighlight(WebElement element,String sElement) {
        try {
            if (ConfigManager.getInstance().getProperty("HighlightElement").equalsIgnoreCase("yes")) {
                String attributevalue = "border:2px solid limegreen;";
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, attributevalue);
            }
        } catch (Exception e) {
            System.out.println(sElement + "- Element could not be highlighted");
        }
    }

    public String getCurrentTimeStamp(){

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyyhmmss");
        String formattedDate = sdf.format(date);
        System.out.println(formattedDate);
        return formattedDate;
    }

    public String getRandomString(){

        return String.valueOf(((long)Math.random()*100000)+getCurrentTimeStamp());
    }

    public String getScreenShot(WebDriver driver) {


        File destinationFile = new File("./Automation_Reports/Screenshots/" + getRandomString() + ".png");

        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String encodedBase64 = null;
        FileInputStream fileInputStreamReader = null;
        try {
            fileInputStreamReader = new FileInputStream(sourceFile);
            byte[] bytes = new byte[(int) sourceFile.length()];
            fileInputStreamReader.read(bytes);
            encodedBase64 = new String(Base64.encodeBase64(bytes));

            String screenShotDestination = destinationFile.getAbsolutePath();

            File destination = new File(screenShotDestination);
            FileUtils.copyFile(sourceFile, destination);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "data:image/png;base64," + encodedBase64;

    }

    public void scrollToElement(By locator,String elementName){
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        waitFor(Constants.VERY_SHORT_WAIT);

    }

    public void waitFor(int optionalTime){
        try {
            Thread.sleep(optionalTime*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean verifyTitleBasedOnWindow(String exptitle) {

        String parentWindow = driver.getWindowHandle();
        for (String window : driver.getWindowHandles()) {

            waitFor(1);
            String currentTitle = driver.getTitle();
            System.out.println("Title of current window "+currentTitle);
            if (currentTitle.contains(exptitle)) {
                return true;
            } else {
                driver.switchTo().window(window);
            }
        }
        return false;

    }


    public void safeJavaScriptClick(By locator,String sElementName,int optionalTime) {
        WebElement element=null;

        if(isElementDisplayed(locator,sElementName,optionalTime)){
            element=driver.findElement(locator);
        }

        try {
            if (element.isEnabled() && element.isDisplayed()) {
                System.out.println("Clicking on element with using java script click");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            } else {
                System.out.println("Unable to click on element");
            }
        } catch (StaleElementReferenceException e) {
            System.out.println("Element is not attached to the page document "+ e.getStackTrace());
        } catch (NoSuchElementException e) {
            System.out.println("Element was not found in DOM "+ e.getStackTrace());
        } catch (Exception e) {
            System.out.println("Unable to click on element "+ e.getStackTrace());
        }
    }

    }
