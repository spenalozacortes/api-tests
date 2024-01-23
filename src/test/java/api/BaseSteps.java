package api;

import config.EnvironmentConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public abstract class BaseSteps {

    protected RequestSpecification getBaseReq() {
        return RestAssured.given()
                .baseUri(EnvironmentConfig.getBaseURI())
                .contentType(ContentType.JSON);
    }
}
