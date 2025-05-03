package Pages;

import Utilities.Utility;
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
    public P01_LoginPage Enter_UserName(String username) {

        Utility.SendData(driver, User_Name, username);
        return this;
    }

    public P01_LoginPage Enter_Password(String password) {

        Utility.SendData(driver, Password, password);
        return this;
    }

    public P02_HomePage Click_On_LoginButton() {
        Utility.Clicking_On_Element(driver, Login_Button);
        return new P02_HomePage(driver);

    }

    public Boolean Get_Info_About_error_Message() {
        return Utility.Find_WebElement(driver, Error_message).isDisplayed();
    }

    public String Get_Error_Message() {
        return Utility.Get_Text(driver, Error_message);
    }

    public Boolean Assertion_Login_TC(String expectedvalue) {

        return driver.getCurrentUrl().equals(expectedvalue);
    }

}
