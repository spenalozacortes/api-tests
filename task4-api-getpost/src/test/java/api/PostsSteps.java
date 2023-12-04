package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostsSteps {

    private static final String END_POINT = "/posts";

    public static Response getPosts() {
        return given().when().get(END_POINT);
    }

    public static Response getPostById(int id) {
        return given().pathParam("id", id)
                .when().get(String.format("%s/{id}", END_POINT));
    }

    public static Response sendPost(String body) {
        return given().contentType(ContentType.JSON)
                .body(body).when().post(END_POINT);
    }
}
