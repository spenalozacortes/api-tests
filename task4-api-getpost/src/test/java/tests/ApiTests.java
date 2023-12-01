package tests;

import api.PostsSteps;
import api.UsersSteps;
import io.restassured.response.Response;
import models.Post;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PostsUtils;

import java.util.List;

public class ApiTests extends BaseTest {

    @Test
    public void getPosts() {
        Response response = PostsSteps.getPosts();
        Assert.assertEquals(response.statusCode(), 200, "Status code is not 200");
        Assert.assertTrue(response.getContentType().contains("application/json"), "Response body is not JSON");
        List<Post> posts = List.of(response.as(Post[].class));
        Assert.assertTrue(PostsUtils.arePostsSorted(posts), "Posts are not sorted in ascending order by id");
    }

    @Test
    public void foundPost() {
        Response response = PostsSteps.getPostById("99");
        Post post = response.as(Post.class);
        Assert.assertEquals(response.statusCode(), 200, "Status code is not 200");
        Assert.assertEquals(post.getUserId(), 10, "userId is incorrect");
        Assert.assertEquals(post.getId(), 99, "id is incorrect");
        Assert.assertFalse(post.getTitle().isEmpty(), "title is empty");
        Assert.assertFalse(post.getBody().isEmpty(), "body is empty");
    }

    @Test
    public void notFoundPost() {
        Response response = PostsSteps.getPostById("150");
        Assert.assertEquals(response.statusCode(), 404, "Status code is not 404");
        Assert.assertEquals(response.getBody().asString(), "{}", "Response body is not empty");
    }

    @Test
    public void createPost() {
        Response response = PostsSteps.createPost("{" +
                "\"title\": \"foo\"," +
                "\"body\": \"bar\"," +
                "\"userId\": \"1\"}");
        Assert.assertEquals(response.statusCode(), 201, "Status code is not 201");
    }

    @Test
    public void getUsers() {
        Response response = UsersSteps.getUsers();
        Assert.assertEquals(response.statusCode(), 200, "Status code is not 200");
    }

    @Test
    public void foundUser() {
        Response response = UsersSteps.getUserById("5");
        Assert.assertEquals(response.statusCode(), 200, "Status code is not 200");
    }
}
