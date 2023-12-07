package api;

import constants.Endpoints;
import constants.Parameters;
import io.restassured.response.Response;
import models.PostResponse;

import java.util.List;

public class PostsSteps extends BaseSteps {

    public Response getPosts() {
        return getBaseReq()
                .when()
                .get(Endpoints.POSTS);
    }

    public Response getPostById(int id) {
        return getBaseReq()
                .pathParam(Parameters.ID, id)
                .when()
                .get(Endpoints.POST_BY_ID);
    }

    public Response sendPost(PostResponse body) {
        return getBaseReq()
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
