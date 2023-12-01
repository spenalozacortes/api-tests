package tests;

import config.EnvironmentConfig;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    @BeforeMethod
    public void setup() {
        RestAssured.baseURI = EnvironmentConfig.getBaseURI();
    }
}
