package expedia.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    public static Properties prop = new Properties();

    public ConfigManager() {
        String fileSeparator = File.separator;
        prop  = new Properties();
        try {
            prop.load(new FileInputStream(new File(System.getProperty("user.dir") + fileSeparator + "src" + fileSeparator + "test" + fileSeparator + "resources" + fileSeparator+"testdata"+fileSeparator+"testData.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static ConfigManager getInstance(){
        return new ConfigManager();
    }

    public  String getProperty(String key){
        return prop.getProperty(key);
    }



}
