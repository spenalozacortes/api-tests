package utils;

import com.google.gson.Gson;
import models.Post;

import java.util.List;

public class PostsUtils {

    public static boolean arePostsSorted(List<Post> posts) {
        for (int i = 0; i < posts.size() - 1; i++) {
            if (posts.get(i).getId() > posts.get(i + 1).getId()) {
                return false;
            }
        }
        return true;
    }

    public static String createPost(String title, String body, int userId) {
        Post post = new Post();
        post.setTitle(title);
        post.setBody(body);
        post.setUserId(userId);
        Gson gson = new Gson();
        return gson.toJson(post);
    }
}
