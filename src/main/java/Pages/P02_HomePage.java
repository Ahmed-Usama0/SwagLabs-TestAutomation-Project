package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class P02_HomePage {
    private static List<WebElement> all_products;
    private static List<WebElement> Selected_products;
    private final WebDriver driver;
    //Locators
    private final By Add_To_Cart_Button_For_All_Products=By.xpath("//button[@class]");
    private final By Number_Of_Selected_Products_On_Cart=By.className("shopping_cart_badge");
    private final By Number_Of_Selected_Items=By.xpath("//button[text()='Remove']");
    //constructor
    public P02_HomePage(WebDriver driver) {
        this.driver = driver;
    }
    //Methods Used For Actions on Elements
    public P02_HomePage Select_All_Products(){
        all_products=driver.findElements(Add_To_Cart_Button_For_All_Products);
        LogsUtils.info("The Number Of All Products is"+ all_products.size());
        for(int i=1;i<=all_products.size();i++){
           By Dynamic_Locator=By.xpath("(//button[@class])[" + i + "]");
         Utility.Clicking_On_Element(driver,Dynamic_Locator);
        }
        return this;
    }
    public String Get_Number_Of_Selected_Products_On_Cart(){
        try{
            LogsUtils.info(Utility.Get_Text(driver,Number_Of_Selected_Products_On_Cart));
            return Utility.Get_Text(driver,Number_Of_Selected_Products_On_Cart);

        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return "0";
        }
    }

    public String Get_Number_Of_Selected_Products(){
        try {
            Selected_products=driver.findElements(Number_Of_Selected_Items);
            LogsUtils.info("The Number Of Selected Products is"+Selected_products.size());
            return String.valueOf(Selected_products.size());
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return "0";
        }


    }
    public Boolean Comparing_Number_Of_Selected_Products_With_Cart(){
       return Get_Number_Of_Selected_Products_On_Cart().equals(Get_Number_Of_Selected_Products());
    }

}
