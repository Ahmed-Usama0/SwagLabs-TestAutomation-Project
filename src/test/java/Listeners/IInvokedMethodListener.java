package Listeners;

import Utilities.*;
import org.testng.IInvokedMethod;
import org.testng.ITestContext;
import org.testng.ITestResult;
import static DriverFactory.DriverFactory.Get_Driver;



public class IInvokedMethodListener implements org.testng.IInvokedMethodListener {
    //ToDo After Calling Method
    public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
        switch (testResult.getStatus()) {
            case ITestResult.FAILURE:
                LogsUtils.info("Test Case " + testResult.getName() + " failed");
                Utility.TakeScreenshot(Get_Driver(), testResult.getName());
                break;
            case ITestResult.SUCCESS:
                LogsUtils.info("Test Case " + testResult.getName() + " passed");
                break;
            case ITestResult.SKIP:
                LogsUtils.info("Test Case " + testResult.getName() + " skipped");
                break;
        }

    }
}
