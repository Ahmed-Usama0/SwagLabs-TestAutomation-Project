package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P06_FinshingOrder {
    private final WebDriver driver;

    //Locators
    private final By Thanks_message=By.className("complete-header");
    //constructor
    public P06_FinshingOrder(WebDriver driver) {
        this.driver = driver;
    }

    //Methods Used For Actions on Elements
    public Boolean Check_visibility_Of_Message(){
        return Utility.Find_WebElement(driver,Thanks_message).isDisplayed();

    }
}
