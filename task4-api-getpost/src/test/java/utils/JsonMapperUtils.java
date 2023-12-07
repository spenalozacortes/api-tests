package utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.experimental.UtilityClass;

import java.io.FileReader;

@UtilityClass
public class JsonMapperUtils {

    public static <T> T deserialize(String path, Class<T> targetClass){
        try {
            FileReader reader = new FileReader(path);
            JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
            Gson gson = new Gson();
            return gson.fromJson(json, targetClass);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
