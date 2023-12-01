package tests;

import api.PostsSteps;
import config.EnvironmentConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.Post;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PostsUtils;

import java.util.List;

public class ApiTests {

    @Test
    public void apiTest() {
        RestAssured.baseURI = EnvironmentConfig.getBaseURI();

        Response getPosts = PostsSteps.getPosts();
        Assert.assertEquals(getPosts.statusCode(), 200, "Status code is not 200");
        Assert.assertTrue(getPosts.getContentType().contains("application/json"), "Response body is not JSON");
        List<Post> posts = List.of(getPosts.as(Post[].class));
        Assert.assertTrue(PostsUtils.arePostsSorted(posts), "Posts are not sorted in ascending order by id");

       /* Post post1 = PostsSteps.getPostById("99").as(Post.class);
        System.out.println(post1.getBody());

        Response post2 = PostsSteps.getPostById("150");
        System.out.println(post2.statusCode());

        Post createdPost = PostsSteps.createPost("{" +
                "\"title\": \"foo\"," +
                "\"body\": \"bar\"," +
                "\"userId\": \"1\"}").as(Post.class);
        System.out.println(createdPost.getTitle());

        List<User> users = List.of(UsersSteps.getUsers()
                .as(User[].class));
        for(User user : users) {
            System.out.println(user.getUsername());
        }

        User user = UsersSteps.getUserById("5").as(User.class);
        System.out.println(user.getUsername());*/
    }
}
