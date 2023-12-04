package api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UsersSteps {

    private static final String END_POINT = "/users";

    public static Response getUsers() {
        return given().when().get(END_POINT);
    }

    public static Response getUserById(int id) {
        return given().pathParam("id", id)
                .when().get(String.format("%s/{id}", END_POINT));
    }
}
