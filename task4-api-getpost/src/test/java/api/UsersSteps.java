package api;

import constants.Endpoints;
import constants.Parameters;
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
                .pathParam(Parameters.ID, id)
                .when()
                .get(Endpoints.USER_BY_ID);
    }
}
