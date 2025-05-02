package Utilities;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class Utility {
    private static final String SCREENSHOTS_PATH = "Test_Outputs/ScreenShots/";

    //TODo Get TimeStamp
    public static String getTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd-hh-mm-ssa").format(new Date());
    }

    //ToDo Find Element
    public static WebElement Find_WebElement(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }

    //ToDO general Explicit Wait
    public static WebDriverWait General_Explicit_wait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    //ToDO Send Data to text field
    public static void SendData(WebDriver driver, By Locator, String data) {
        //Wait Before sending Data
        General_Explicit_wait(driver).until(ExpectedConditions.visibilityOfElementLocated(Locator));
        // Send Data To Element
        driver.findElement(Locator).sendKeys(data);
    }

    //ToDO Click On Element
    public static void Clicking_On_Element(WebDriver driver, By locator) {
        //Wait Before Clicking On Element
        General_Explicit_wait(driver).until(ExpectedConditions.elementToBeClickable(locator));
        // Click on Element
        driver.findElement(locator).click();
    }

    //TODO Get text
    public static String Get_Text(WebDriver driver, By locator) {
        //Wait Before Get Text
        General_Explicit_wait(driver).until(ExpectedConditions.visibilityOfElementLocated(locator));
        // Get Text From Element
        return driver.findElement(locator).getText();

    }

    //ToDo Selecting From DropDown
    public static void selectingFromDropDown(WebDriver driver, By locator, String option) {
        new Select(Find_WebElement(driver, locator)).selectByVisibleText(option);
    }

    //TODo scrolling using java-script-executor
    public static void Scrolling(WebDriver driver, By locator){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", Find_WebElement(driver, locator));

    }
    //ToDo Taking ScreenShot Using Selenium
    public static void TakeScreenshot(WebDriver driver, String screenshotName) {
        try {
            // Capture screenshot using TakesScreenshot
            File ScreenShotFileScr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            // Create File For Save ScreenShot
            File ScreenShotFile = new File(SCREENSHOTS_PATH + screenshotName + "--" + getTimeStamp() + ".png");
            //Copy ScreenShot That taken to File That created
            FileUtils.copyFile(ScreenShotFileScr, ScreenShotFile);
            //Attach ScreenShot To Allure Report
            Allure.addAttachment(screenshotName, Files.newInputStream(Path.of(ScreenShotFile.getPath())));

        } catch (Exception e) {

            e.getStackTrace();
        }


    }
}
