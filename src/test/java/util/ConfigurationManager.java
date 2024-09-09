package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {
    private static Properties config = new Properties();
    private static FileInputStream fileInput;
    public static void loadConfig() {
        fileInput = null;
        try {
            String filePath = System.getProperty("user.dir") + "//src//test//resources//environment.properties";
            fileInput = new FileInputStream(filePath);
            config.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInput != null) {
                try {
                    fileInput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static String getProperty(String key) {
        return config.getProperty(key);
    }
}
