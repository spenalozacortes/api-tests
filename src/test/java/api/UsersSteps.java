package api;

import constants.Endpoints;
import constants.Parameters;
import io.restassured.response.Response;
import models.UserResponse;

import java.util.List;
import java.util.NoSuchElementException;

public class UsersSteps extends BaseSteps {

    public Response getUsers() {
        return getBaseReq()
                .when()
                .get(Endpoints.USERS);
    }

    public Response getUserById(int id) {
        return getBaseReq()
                .pathParam(Parameters.ID, id)
                .when()
                .get(Endpoints.USER_BY_ID);
    }

    public static UserResponse getUserFromListById(Response response, int id) {
        List<UserResponse> users = List.of(response.as(UserResponse[].class));
        for(UserResponse user : users) {
            if(user.getId() == id) {
                return user;
            }
        }
        throw new NoSuchElementException(String.format("User with ID %d not found", id));
    }
}
