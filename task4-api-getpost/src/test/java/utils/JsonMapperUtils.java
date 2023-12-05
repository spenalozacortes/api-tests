package utils;

import com.google.gson.Gson;

public class JsonMapperUtils {

    public static <T> T deserialize(String json, Class<T> targetClass){
        Gson gson = new Gson();
        return gson.fromJson(json, targetClass);
    }
}
