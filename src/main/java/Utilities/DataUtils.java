package Utilities;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Properties;

public class DataUtils {
    private static final String TEST_DATA_PATH = "src/test/resources/Test-Data/";

    //ToDo: Read Data from json files
    public static String GetJsonData(String File_Name, String Key) {
        try {
            // Define object of File Reader
            FileReader reader = new FileReader(TEST_DATA_PATH + File_Name + ".json");
            // Parse Json_file into a Json_element
            JsonElement jsonElement = JsonParser.parseReader(reader);

            return jsonElement.getAsJsonObject().get(Key).getAsString();

        } catch (Exception e) {
            e.printStackTrace();


        }
        return "";

    }

    //ToDo: Read Data from properties files
    public static String GetPropertyValue(String File_Name, String Key) {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(TEST_DATA_PATH + File_Name + ".properties"));
            return properties.getProperty(Key);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }
}
