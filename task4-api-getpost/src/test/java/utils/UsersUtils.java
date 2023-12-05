package utils;

import com.google.gson.Gson;
import models.UserResponse;

import java.util.List;

public class UsersUtils {

    public static UserResponse getUserFromListById(List<UserResponse> users, int id) {
        for(UserResponse user : users) {
            if(user.getId() == id) {
                return user;
            }
        }
        return null;
    }
}
