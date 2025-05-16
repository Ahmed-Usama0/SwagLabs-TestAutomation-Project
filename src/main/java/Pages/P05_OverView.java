package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P05_OverView {
    private final WebDriver driver;

    //Locators
    private final By Sub_Total = By.className("summary_subtotal_label");
    private final By Taxes_Value = By.className("summary_tax_label");
    private final By Total = By.className("summary_total_label");
    private final By Finish_Button = By.id("finish");

    //constructor
    public P05_OverView(WebDriver driver) {
        this.driver = driver;
    }

    //Methods Used For Actions on Elements

    public Float Get_Sub_Total() {
        return Float.parseFloat(Utility.Get_Text(driver, Sub_Total).replace("Item total: $", ""));
    }

    public Float Get_Taxes_Value() {
        return Float.parseFloat(Utility.Get_Text(driver, Taxes_Value).replace("Tax: $", ""));
    }

    public Float Get_Total() {
        return Float.parseFloat(Utility.Get_Text(driver, Total).replace("Total: $", ""));
    }

    public String Caluclate_Total() {
        return String.valueOf(Get_Sub_Total() + Get_Taxes_Value());
    }

    public Boolean Comparing_Prices() {
        return Caluclate_Total().equals(String.valueOf(Get_Total()));

    }

    public P06_FinshingOrder ClickOn_Finish_Button() {
        Utility.Clicking_On_Element(driver, Finish_Button);
        return new P06_FinshingOrder(driver);
    }
}
