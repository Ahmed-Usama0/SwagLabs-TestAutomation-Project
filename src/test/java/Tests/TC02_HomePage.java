package Tests;

import DriverFactory.DriverFactory;
import Pages.P01_LoginPage;
import Pages.P02_HomePage;
import Utilities.DataUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.time.Duration;

public class TC02_HomePage {
    //Data
    private final String validUserName = DataUtils.GetJsonData("LoginData", "validUserName");
    private final String validPassword = DataUtils.GetJsonData("LoginData", "validPassword");

    @BeforeMethod
    public void SetUp() {
        //Data
        String Browser = DataUtils.GetPropertyValue("Environment", "Browser");
        String Base_URL = DataUtils.GetPropertyValue("Environment", "Base_URL");
        //Setup
        DriverFactory.SetUp_Driver(Browser);
        DriverFactory.Get_Driver().get(Base_URL);
        //General Wait
        DriverFactory.Get_Driver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }


    @Test
    public void Checking_Number_Of_Selected_Products_TC() {
        //ToDo Login Steps
        new P01_LoginPage(DriverFactory.Get_Driver())
                .Enter_UserName(validUserName).
                Enter_Password(validPassword).
                Click_On_LoginButton().
                Select_All_Products();
        //ToDo Assertion
        Assert.assertTrue(new P02_HomePage(DriverFactory.Get_Driver()).Comparing_Number_Of_Selected_Products_With_Cart());

    }



    @AfterMethod
    public void Quit() {
        //DriverFactory.Quit_Driver();

    }
}
