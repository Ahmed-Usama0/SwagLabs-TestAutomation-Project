package Tests;

import DriverFactory.DriverFactory;
import Listeners.IInvokedMethodListener;
import Listeners.ITestResultListeners;
import Pages.P01_LoginPage;
import Pages.P02_HomePage;
import Utilities.DataUtils;
import Utilities.Utility;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;

@Listeners({ITestResultListeners.class, IInvokedMethodListener.class})
public class TC02_HomePage {
    //Data
    private final String validUserName = DataUtils.GetJsonData("LoginData", "validUserName");
    private final String validPassword = DataUtils.GetJsonData("LoginData", "validPassword");
    private final String Cart_Page_Url = DataUtils.GetPropertyValue("Environment", "Cart_Page_URL");

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
                Click_On_LoginButton();
        //ToDo Home Page Steps
        new P02_HomePage(DriverFactory.Get_Driver()).Select_All_Products();
        //ToDo Assertion
        new P02_HomePage(DriverFactory.Get_Driver()).Comparing_Number_Of_Selected_Products_With_Cart();

    }

    @Test
    public void Adding_Number_Of_Random_Products_TC() {
        //ToDo Login Steps
        new P01_LoginPage(DriverFactory.Get_Driver())
                .Enter_UserName(validUserName).
                Enter_Password(validPassword).
                Click_On_LoginButton();
        //ToDo Home Page Steps
        new P02_HomePage(DriverFactory.Get_Driver()).Add_Random_Products(Utility.Generate_Rondom_Number(new P02_HomePage(DriverFactory.Get_Driver()).Get_Total_Products()), new P02_HomePage(DriverFactory.Get_Driver()).Get_Total_Products());
        //ToDo Assertion
        new P02_HomePage(DriverFactory.Get_Driver()).Comparing_Number_Of_Selected_Products_With_Cart();

    }

    @Test
    public void Verify_cart_Page_Url_TC() {
        //ToDo Login Steps
        new P01_LoginPage(DriverFactory.Get_Driver())
                .Enter_UserName(validUserName).
                Enter_Password(validPassword).
                Click_On_LoginButton();
        //ToDo Home Page Steps
        new P02_HomePage(DriverFactory.Get_Driver()).Click_On_Cart_Icon();
        //ToDo Assertion
        Utility.Verify_Url(Cart_Page_Url);

    }


    @AfterMethod
    public void Quit() {
        //DriverFactory.Quit_Driver();

    }
}
