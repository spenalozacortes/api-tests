package config;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;

public class TestDataConfig {

    public static JsonObject readTestUser() {
        try {
            FileReader reader = new FileReader("src/test/resources/testUser.json");
            return JsonParser.parseReader(reader).getAsJsonObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
