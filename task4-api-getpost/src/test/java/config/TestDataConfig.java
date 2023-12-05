package config;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;

public class TestDataConfig {

    public static String readTestUser() {
        try {
            FileReader reader = new FileReader("src/test/resources/apiresponses/user5Response.json");
            return JsonParser.parseReader(reader).getAsJsonObject().toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
