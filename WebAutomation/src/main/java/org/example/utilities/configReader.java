package org.example.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class configReader {
    private Properties properties;

    public configReader(){
        try(FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties")){
            properties = new Properties();
            properties.load(fileInputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public String getBaseURL(){
        return properties.getProperty("baseURL");
    }
}
