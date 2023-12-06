package api;

import config.EnvironmentConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class BaseSteps {

    public RequestSpecification getBaseReq() {
        return new RequestSpecBuilder()
                .setBaseUri(EnvironmentConfig.getBaseURI())
                .build();
    }
}
