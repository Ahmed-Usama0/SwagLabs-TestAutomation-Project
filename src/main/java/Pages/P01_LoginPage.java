package Pages;

import Utilities.Utility;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P01_LoginPage {

    private final WebDriver driver;
    //Locators
    private final By User_Name = By.id("user-name");
    private final By Password = By.id("password");
    private final By Login_Button = By.id("login-button");
    private final By Error_message = By.xpath("//h3[@data-test=\"error\"]");

    //constructor
    public P01_LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //Methods Used For Actions on Elements
    @Step("Enter username: {username}")
    public P01_LoginPage Enter_UserName(String username) {

        Utility.SendData(driver, User_Name, username);
        return this;
    }

    @Step("Enter password: {password}")
    public P01_LoginPage Enter_Password(String password) {

        Utility.SendData(driver, Password, password);
        return this;
    }

    @Step("Click on Login button")
    public P02_HomePage Click_On_LoginButton() {
        Utility.Clicking_On_Element(driver, Login_Button);
        return new P02_HomePage(driver);

    }

    @Step("Assert current URL equals: {expectedvalue}")
    public void Assertion_Login_TC(String expectedvalue) {
        String actualUrl = driver.getCurrentUrl();
        boolean result = actualUrl.equals(expectedvalue);
        if (!result) {
            throw new AssertionError("Expected URL: " + expectedvalue + ", but got: " + actualUrl);
        }
    }

    @Step("Check if error message is displayed")
    public void Get_Info_About_error_Message() {
        boolean isDisplayed = Utility.Find_WebElement(driver, Error_message).isDisplayed();
        if (!isDisplayed) {
            Allure.addAttachment("Failure", "❌ Error message not displayed.");
            throw new AssertionError("Expected error message to be displayed, but it was not.");
        }
    }

    @Step("Get error message text")
    public String Get_Error_Message() {
        return Utility.Get_Text(driver, Error_message);
    }

    @Step("Check if error message text is correct")
    public void Assert_Error_Message(String expected) {
        String actual = Get_Error_Message();
        if (!actual.equals(expected)) {
            Allure.addAttachment("Actual message", actual);
            Allure.addAttachment("Expected message", expected);
            throw new AssertionError("Expected: " + expected + ", but got: " + actual);
        }
    }

}
