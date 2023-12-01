package config;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;

public class EnvironmentConfig {

    private static final JsonObject ENVIRONMENT;

    static {
        try {
            FileReader reader = new FileReader("src/test/resources/environment.json");
            ENVIRONMENT = JsonParser.parseReader(reader).getAsJsonObject();
            reader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getBaseURI() {
        return ENVIRONMENT.get("baseURI").getAsString();
    }
}
