package expedia.utilities;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import expedia.pages.ExpediaHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    public WebDriver driver;
    public ExpediaHomePage expediaHomePage;
    public ConfigManager configManager;


    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest test;

    public BaseClass() {
        configManager = new ConfigManager();
    }


    @BeforeClass
    public void reportSetup() {

        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Automation_Reports/index.html");
        extent = new ExtentReports();  //create object of ExtentReports
        extent.attachReporter(htmlReporter);
        htmlReporter.config().setDocumentTitle("Automation Report"); // Tittle of Report
        htmlReporter.config().setReportName("Extent Report V4"); // Name of the report
        htmlReporter.config().setTheme(Theme.DARK);//Default Theme of Report
        htmlReporter.config().setAutoCreateRelativePathMedia(true);
        // General information releated to application
        extent.setSystemInfo("Application Name", "Expedia");
        extent.setSystemInfo("User Name", "Shyam");
        extent.setSystemInfo("Envirnoment", "stage");


    }

    @BeforeMethod
    public void initalSetup(Method result) {

        System.setProperty("webdriver.chrome.driver", configManager.getProperty("driverPath"));
        driver = new ChromeDriver();
        driver.get(configManager.getProperty("appUrl"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Constants.VERY_SHORT_WAIT, TimeUnit.SECONDS);
        expediaHomePage = new ExpediaHomePage(driver);
        test = extent.createTest(result.getName());

    }

    @AfterMethod
    public void finalSetup(ITestResult result) {


        if (result.getStatus() == ITestResult.FAILURE) {
            //MarkupHelper is used to display the output in different colors
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));

            //To capture screenshot path and store the path of the screenshot in the string "screenshotPath"
            //We do pass the path captured by this method in to the extent reports using "logger.addScreenCapture" method.

            //	String Scrnshot=TakeScreenshot.captuerScreenshot(driver,"TestCaseFailed");
            String screenshotPath = new Utils(driver).getScreenShot(driver);
            //To add it in the extent report
            try {
                test.fail("Test Case Failed Snapshot is below " + test.addScreenCaptureFromBase64String(screenshotPath));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (result.getStatus() == ITestResult.SKIP) {
            //logger.log(Status.SKIP, "Test Case Skipped is "+result.getName());
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
        }

        if (driver != null) {
            driver.quit();
        }
    }

    @AfterClass
    public void close() {
        extent.flush();
    }

}
