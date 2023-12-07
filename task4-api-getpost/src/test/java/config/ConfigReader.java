package config;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;

public class ConfigReader {

    private final JsonObject environment;

    public ConfigReader(String path) {
        try (FileReader reader = new FileReader(path)) {
            environment = JsonParser.parseReader(reader).getAsJsonObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getValueByKey(String key) {
        return environment.get(key).getAsString();
    }
}
