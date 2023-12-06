package api;

import constants.Endpoints;
import constants.Parameters;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UsersSteps extends BaseSteps {

    public Response getUsers() {
        return given()
                .spec(getBaseReq())
                .when()
                .get(Endpoints.USERS);
    }

    public Response getUserById(int id) {
        return given()
                .spec(getBaseReq())
                .pathParam(Parameters.ID, id)
                .when()
                .get(Endpoints.USER_BY_ID);
    }
}
