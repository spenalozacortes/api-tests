package api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostsSteps {

    public static Response getPosts() {
        return given().when().get("/posts");
    }

    public static Response getPostById(String id) {
        return given().pathParam("id", id)
                .when().get("/posts/{id}");
    }

    public static Response createPost(String body) {
        return given().header("Content-Type", "application/json")
                .body(body).when().post("/posts");
    }
}
