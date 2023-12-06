package api;

import constants.Endpoints;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UsersSteps {

    public Response getUsers() {
        return given()
                .when()
                .get(Endpoints.USERS);
    }

    public Response getUserById(int id) {
        return given()
                .pathParam("id", id)
                .when()
                .get(String.format("%s/{id}", Endpoints.USERS));
    }
}
