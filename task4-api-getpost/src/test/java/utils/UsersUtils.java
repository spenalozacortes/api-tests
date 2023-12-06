package utils;

import models.UserResponse;

import java.util.List;
import java.util.NoSuchElementException;

public class UsersUtils {

    public static UserResponse getUserFromListById(List<UserResponse> users, int id) {
        for(UserResponse user : users) {
            if(user.getId() == id) {
                return user;
            }
        }
        throw new NoSuchElementException(String.format("User with ID %d not found", id));
    }
}
