package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> driverThreadlocal = new ThreadLocal<WebDriver>();

    //ToDo: set_Driver
    public static void SetUp_Driver(String Browser) {

        switch (Browser.toLowerCase()) {

            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                driverThreadlocal.set(new ChromeDriver(chromeOptions));
                break;
            case "firefox":
                driverThreadlocal.set(new FirefoxDriver());
                break;
            default:
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                driverThreadlocal.set(new EdgeDriver(edgeOptions));


        }

    }

    //ToDo: Get_Driver
    public static WebDriver Get_Driver() {
        return driverThreadlocal.get();
    }

    //ToDo:  Quit_Driver
    public static void Quit_Driver() {

        Get_Driver().quit();
        driverThreadlocal.remove();


    }
}
