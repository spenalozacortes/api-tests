package utils;

import com.google.gson.Gson;
import models.PostResponse;

public class PostsUtils {

    public static String createPost(String title, String body, int userId) {
        PostResponse post = new PostResponse();
        post.setTitle(title);
        post.setBody(body);
        post.setUserId(userId);
        Gson gson = new Gson();
        return gson.toJson(post);
    }
}
