package api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UsersSteps {

    public Response getUsers() {
        return given().when().get("/users");
    }

    public Response getUserById(String id) {
        return given().pathParam("id", id)
                .when().get("/users/{id}");
    }
}
