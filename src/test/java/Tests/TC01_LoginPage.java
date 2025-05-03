package Tests;

import DriverFactory.DriverFactory;
import Listeners.IInvokedMethodListener;
import Listeners.ITestResultListeners;
import Pages.P01_LoginPage;
import Utilities.DataUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

@Listeners({ITestResultListeners.class, IInvokedMethodListener.class})
public class TC01_LoginPage {
    //Data
    private final String validUserName = DataUtils.GetJsonData("LoginData", "validUserName");
    private final String validPassword = DataUtils.GetJsonData("LoginData", "validPassword");
    private final String InvalidUserName = DataUtils.GetJsonData("LoginData", "InvalidUserName");
    private final String InvalidPassword = DataUtils.GetJsonData("LoginData", "InvalidPassword");
    private final String Home_URL = DataUtils.GetPropertyValue("Environment", "Home_URL");
    //SoftAssert
    SoftAssert softAssert = new SoftAssert();

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
    public void Valid_Login_TC() {
        //ToDo Login Steps
        new P01_LoginPage(DriverFactory.Get_Driver())
                .Enter_UserName(validUserName).
                Enter_Password(validPassword).
                Click_On_LoginButton();
        //ToDo Assertion
        Assert.assertTrue(new P01_LoginPage(DriverFactory.Get_Driver()).Assertion_Login_TC(Home_URL));

    }

    @Test
    public void InValid_Login_TC() {
        //ToDo Login Steps
        new P01_LoginPage(DriverFactory.Get_Driver())
                .Enter_UserName(InvalidUserName).
                Enter_Password(InvalidPassword).
                Click_On_LoginButton();
        //ToDo Assertion
        softAssert.assertTrue(new P01_LoginPage(DriverFactory.Get_Driver()).Get_Info_About_error_Message());
        softAssert.assertEquals(new P01_LoginPage(DriverFactory.Get_Driver()).Get_Error_Message(), "Epic sadface: Username and password do not match any user in this service");
        softAssert.assertAll();

    }

    @AfterMethod
    public void Quit() {
        DriverFactory.Quit_Driver();

    }
}
