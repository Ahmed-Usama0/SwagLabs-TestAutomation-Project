package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class P03_CartPage {
    private static Float Total_prices;
    private List<WebElement> Prices_Of_Selected_Products;
    private final WebDriver driver;
    //Locators
    private final By Prices_Of_Selected_Products_Locator=By.xpath("//button[.=\"Remove\"]/preceding-sibling::div[@class=\"inventory_item_price\"]");

    //constructor
    public P03_CartPage(WebDriver driver) {
        this.driver = driver;
        Prices_Of_Selected_Products=driver.findElements(Prices_Of_Selected_Products_Locator);}



    //Methods Used For Actions on Elements
    public String Get_Prices_Of_Selected_Products(){
        try {
            for(int i=1;i<=Prices_Of_Selected_Products.size();i++){
                By Dynamic_Locator=By.xpath("(//button[.=\"Remove\"]/preceding-sibling::div[@class=\"inventory_item_price\"])["+i+"]");
                String price= Utility.Get_Text(driver,Dynamic_Locator).replace("$","");
                Total_prices+=Float.parseFloat(price);
            }
            return String.valueOf(Total_prices);

        }catch (Exception e){
            LogsUtils.error(e.getMessage());
            return "0";
        }

    }


    public Boolean Comparing_Prices(String expected_price){
        return Get_Prices_Of_Selected_Products().equals(expected_price);
    }
}
