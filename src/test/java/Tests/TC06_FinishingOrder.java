package Tests;
import Listeners.IInvokedMethodListener;
import Listeners.ITestResultListeners;
import DriverFactory.DriverFactory;
import Pages.*;
import Utilities.DataUtils;
import Utilities.Utility;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;
@Listeners({ITestResultListeners.class, IInvokedMethodListener.class})
public class TC06_FinishingOrder {
    //Data
    private final String First_Name = DataUtils.GetJsonData("CheckOut_Info", "FirstName") + "-" + Utility.getTimeStamp();
    private final String Last_Name = DataUtils.GetJsonData("CheckOut_Info", "LastName") + "-" + Utility.getTimeStamp();
    private final String Postal_Code = new Faker().number().digits(5);
    private final String validUserName = DataUtils.GetJsonData("LoginData", "validUserName");
    private final String validPassword = DataUtils.GetJsonData("LoginData", "validPassword");
    private final String CheckOut_URl = DataUtils.GetPropertyValue("Environment", "Check_Out_URL");

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
    public void Finishing_Order_TC() {
        //ToDo Login Steps
        new P01_LoginPage(DriverFactory.Get_Driver())
                .Enter_UserName(validUserName).
                Enter_Password(validPassword).
                Click_On_LoginButton();
        //ToDo Home Page Steps
        String Total_Price = new P02_HomePage(DriverFactory.Get_Driver()).Add_Random_Products(Utility.Generate_Rondom_Number(new P02_HomePage(DriverFactory.Get_Driver()).Get_Total_Products()), new P02_HomePage(DriverFactory.Get_Driver()).Get_Total_Products()).Get_Prices_Of_Selected_Products();
        System.out.println(Total_Price);
        //ToDo Cart Page Steps
        new P02_HomePage(DriverFactory.Get_Driver()).Click_On_Cart_Icon().
                Click_On_Check_Out_Button();
        //ToDo CheckOut Steps
        new P04_CheckOut(DriverFactory.Get_Driver()).
                Send_First_Name_Data(First_Name).
                Send_Last_Name_Data(Last_Name).
                Send_Postal_Code_Data(Postal_Code).
                Click_On_continue_Button();
        //ToDo OverView Steps
        new P05_OverView(DriverFactory.Get_Driver()).ClickOn_Finish_Button();

        //ToDo Assertion
        Assert.assertTrue(new P06_FinshingOrder(DriverFactory.Get_Driver()).Check_visibility_Of_Message());

    }
















    @AfterMethod
    public void Quit() {
        //DriverFactory.Quit_Driver();

    }
}
