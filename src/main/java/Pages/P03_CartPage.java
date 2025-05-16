package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class P03_CartPage {
    private static Float Total_prices = 0f;
    private final WebDriver driver;
    //Locators
    private final By Prices_Of_Selected_Products_Locator = By.xpath("//button[.=\"Remove\"]/preceding-sibling::div[@class=\"inventory_item_price\"]");
    private final By Check_Out_Button = By.id("checkout");
    private List<WebElement> Prices_Of_Selected_Products;

    //constructor
    public P03_CartPage(WebDriver driver) {
        this.driver = driver;
    }


    //Methods Used For Actions on Elements
    public String Get_Prices_Of_Selected_Products() {
        try {
            Prices_Of_Selected_Products = driver.findElements(Prices_Of_Selected_Products_Locator);
            for (int i = 1; i <= Prices_Of_Selected_Products.size(); i++) {
                By Dynamic_Locator = By.xpath("(//button[.=\"Remove\"]/preceding-sibling::div[@class=\"inventory_item_price\"])[" + i + "]");
                String price = Utility.Get_Text(driver, Dynamic_Locator).replace("$", "");
                Total_prices += Float.parseFloat(price);
            }
            System.out.println(Total_prices);
            return String.valueOf(Total_prices);
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return "0";
        }

    }


    public Boolean Comparing_Prices(String expected_price) {
        return Get_Prices_Of_Selected_Products().equals(expected_price);
    }


    public P04_CheckOut Click_On_Check_Out_Button() {
        Utility.Clicking_On_Element(driver, Check_Out_Button);
        return new P04_CheckOut(driver);
    }
}
