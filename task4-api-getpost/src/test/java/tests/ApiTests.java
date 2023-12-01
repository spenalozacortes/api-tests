package tests;

import io.restassured.RestAssured;
import models.post.Post;
import models.user.User;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ApiTests {

    @Test
    public void apiTest() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";

        given().when().get("/posts")
                .then().assertThat().statusCode(200);

        Post post = given().pathParam("id", "99")
                .when().get("/posts/{id}")
                .as(Post.class);

        given().pathParam("id", "150")
                .when().get("/posts/{id}")
                .then().assertThat().statusCode(404);

        given().header("Content-Type", "application/json")
                .body("{" +
                        "\"title\": \"foo\"," +
                        "\"body\": \"bar\"," +
                        "\"userId\": \"1\"}")
                .when().post("/posts")
                .then().assertThat().statusCode(201);

        given().when().get("/users")
                .then().assertThat().statusCode(200);

        User user = given().pathParam("id", "5")
                .when().get("/users/{id}")
                .as(User.class);
    }
}
