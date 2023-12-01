package utils;

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
}
