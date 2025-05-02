package Utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogsUtils {
    public final static String LOGS_PATH = "Test_Outputs/Logs";
    //ToDo Log trace method
    public static void trace(String message) {
        Logger logger = LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString());
        logger.trace(message);
    }
    //ToDo Log debug method
    public static void debug(String message) {
        Logger logger = LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString());
        logger.debug(message);
    }
    //ToDo Log info method
    public static void info(String message) {
        Logger logger = LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString());
        logger.info(message);
    }

    //ToDo Log warn method
    public static void warn(String message) {
        Logger logger = LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString());
        logger.warn(message);
    }
    //ToDo Log error method
    public static void error(String message) {
        Logger logger = LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString());
        logger.error(message);

    }
    //ToDo Log fatal method
    public static void fatal(String message) {
        Logger logger = LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString());
        logger.fatal(message);

    }
}
