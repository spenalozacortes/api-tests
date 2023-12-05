package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.PostResponse;

import java.util.List;

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

    public static boolean arePostsSorted(List<PostResponse> posts) {
        for (int i = 0; i < posts.size() - 1; i++) {
            if (posts.get(i).getId() > posts.get(i + 1).getId()) {
                return false;
            }
        }
        return true;
    }
}
