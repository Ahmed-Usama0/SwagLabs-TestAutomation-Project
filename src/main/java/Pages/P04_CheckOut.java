package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P04_CheckOut {
    private final WebDriver driver;

    //Locators
    private final By First_Name = By.id("first-name");
    private final By Last_Name = By.id("last-name");
    private final By Postal_Code = By.id("postal-code");
    private final By continue_Button = By.id("continue");

    //constructor
    public P04_CheckOut(WebDriver driver) {
        this.driver = driver;
    }

    //Methods Used For Actions on Elements
    public P04_CheckOut Send_First_Name_Data(String firstname) {
        Utility.SendData(driver, First_Name, firstname);
        return this;
    }

    public P04_CheckOut Send_Last_Name_Data(String lastname) {
        Utility.SendData(driver, Last_Name, lastname);
        return this;
    }

    public P04_CheckOut Send_Postal_Code_Data(String postalcode) {
        Utility.SendData(driver, Postal_Code, postalcode);
        return this;
    }

    public P05_OverView Click_On_continue_Button() {
        Utility.Clicking_On_Element(driver, continue_Button);
        return new P05_OverView(driver);
    }


}
