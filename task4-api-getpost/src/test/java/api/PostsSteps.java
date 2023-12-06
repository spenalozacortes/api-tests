package api;

import constants.Endpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.PostResponse;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsSteps {

    public Response getPosts() {
        return given()
                .when()
                .get(Endpoints.POSTS);
    }

    public Response getPostById(int id) {
        return given()
                .pathParam("id", id)
                .when()
                .get(String.format("%s/{id}", Endpoints.POSTS));
    }

    public Response sendPost(PostResponse body) {
        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(Endpoints.POSTS);
    }

    public boolean arePostsSorted(List<PostResponse> posts) {
        for (int i = 0; i < posts.size() - 1; i++) {
            if (posts.get(i).getId() > posts.get(i + 1).getId()) {
                return false;
            }
        }
        return true;
    }
}
