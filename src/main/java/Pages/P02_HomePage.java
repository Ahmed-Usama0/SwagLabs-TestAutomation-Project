package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

public class P02_HomePage {
    private static Float Total_prices = 0.0f;
    private static List<WebElement> all_products;
    private static List<WebElement> Selected_products;
    private static List<WebElement> Prices_Of_Selected_Products;
    private final WebDriver driver;
    //Locators
    private final By Add_To_Cart_Button_For_All_Products = By.xpath("//button[@class]");
    private final By Number_Of_Selected_Products_On_Cart = By.className("shopping_cart_badge");
    private final By Number_Of_Selected_Items = By.xpath("//button[text()='Remove']");
    private final By Cart_Icon = By.className("shopping_cart_link");
    private final By Prices_Of_Selected_Products_Locator = By.xpath("//button[.=\"Remove\"]/preceding-sibling::div[@class=\"inventory_item_price\"]");

    //constructor
    public P02_HomePage(WebDriver driver) {
        this.driver = driver;
        all_products = driver.findElements(Add_To_Cart_Button_For_All_Products);
    }

    //Methods Used For Actions on Elements
    public P02_HomePage Select_All_Products() {

        LogsUtils.info("The Number Of All Products is" + all_products.size());
        for (int i = 1; i <= all_products.size(); i++) {
            By Dynamic_Locator = By.xpath("(//button[@class])[" + i + "]");
            Utility.Clicking_On_Element(driver, Dynamic_Locator);
        }
        return this;
    }

    public String Get_Number_Of_Selected_Products_On_Cart() {
        try {
            LogsUtils.info(Utility.Get_Text(driver, Number_Of_Selected_Products_On_Cart));
            return Utility.Get_Text(driver, Number_Of_Selected_Products_On_Cart);

        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return "0";
        }
    }

    public String Get_Number_Of_Selected_Products() {
        try {
            Selected_products = driver.findElements(Number_Of_Selected_Items);
            LogsUtils.info("The Number Of Selected Products is" + Selected_products.size());
            return String.valueOf(Selected_products.size());
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return "0";
        }


    }

    public Boolean Comparing_Number_Of_Selected_Products_With_Cart() {
        return Get_Number_Of_Selected_Products_On_Cart().equals(Get_Number_Of_Selected_Products());
    }


    public P02_HomePage Add_Random_Products(int number_of_products, int total_number_of_products) {
        Set<Integer> random_numbers = Utility.Generate_a_Set_of_Rondom_Values(number_of_products, total_number_of_products);
        for (int i : random_numbers) {
            By Dynamic_Locator = By.xpath("(//button[@class])[" + i + "]");
            Utility.Clicking_On_Element(driver, Dynamic_Locator);
        }
        return this;
    }

    public int Get_Total_Products() {
        return all_products.size();
    }


    public P03_CartPage Click_On_Cart_Icon() {
        Utility.Clicking_On_Element(driver, Cart_Icon);
        return new P03_CartPage(driver);
    }

    public String Get_Prices_Of_Selected_Products() {
        try {
            Prices_Of_Selected_Products = driver.findElements(Prices_Of_Selected_Products_Locator);
            for (int i = 1; i <= Prices_Of_Selected_Products.size(); i++) {
                By Dynamic_Locator = By.xpath("(//button[.=\"Remove\"]/preceding-sibling::div[@class=\"inventory_item_price\"])[" + i + "]");
                String price = Utility.Get_Text(driver, Dynamic_Locator).replace("$", "");
                Total_prices += Float.parseFloat(price);
            }
            return String.valueOf(Total_prices);

        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return "0";
        }

    }

}
