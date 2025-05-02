package Listeners;

import Utilities.LogsUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;

public class ITestResultListeners implements ITestListener {
    //ToDo Before TestCase Run
    public void onStart(ITestContext context) {
        LogsUtils.info("Test Started: " + context.getName());
    }

}
