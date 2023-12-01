package api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UsersSteps {

    public static Response getUsers() {
        return given().when().get("/users");
    }

    public static Response getUserById(String id) {
        return given().pathParam("id", id)
                .when().get("/users/{id}");
    }
}
