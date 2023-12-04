package utils;

import com.google.gson.Gson;
import models.User;

import java.util.List;

public class UsersUtils {

    public static User getUserFromListById(List<User> users, int id) {
        for(User user : users) {
            if(user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public static String convertToJson(User user) {
        Gson gson = new Gson();
        return gson.toJson(user);
    }
}
