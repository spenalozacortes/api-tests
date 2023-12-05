package config;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Getter;

import java.io.FileReader;

@Getter
public class ConfigReader {

    private final JsonObject environment;

    public ConfigReader(String path) {
        try {
            FileReader reader = new FileReader(path);
            environment = JsonParser.parseReader(reader).getAsJsonObject();
            reader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
