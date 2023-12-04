package tests;

import api.PostsSteps;
import api.UsersSteps;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import config.TestDataConfig;
import io.restassured.response.Response;
import models.Post;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PostsUtils;
import utils.RandomUtils;
import utils.UsersUtils;

import java.util.List;

public class ApiTests extends BaseTest {

    @Test
    public void getPosts() {
        Response response = PostsSteps.getPosts();
        List<Post> posts = List.of(response.as(Post[].class));

        Assert.assertEquals(response.statusCode(), 200, "Status code is not 200");
        Assert.assertTrue(response.getContentType().contains("application/json"), "Response body is not JSON");
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
    public void sendPost() {
        String randomTitle = RandomUtils.generateRandomString(10);
        String randomBody = RandomUtils.generateRandomString(50);
        String post = PostsUtils.createPost(randomTitle, randomBody, 1);
        Response response = PostsSteps.sendPost(post);
        Post actualPost = response.as(Post.class);

        Assert.assertEquals(response.statusCode(), 201, "Status code is not 201");
        Assert.assertEquals(actualPost.getUserId(), 1, "userId is incorrect");
        Assert.assertEquals(actualPost.getTitle(), randomTitle, "title is incorrect");
        Assert.assertEquals(actualPost.getBody(), randomBody, "body is incorrect");
        Assert.assertFalse(String.valueOf(actualPost.getId()).isEmpty(), "id is not present");
    }

    @Test
    public void getUsers() {
        Response response = UsersSteps.getUsers();
        List<User> users = List.of(response.as(User[].class));
        User user = UsersUtils.getUserFromListById(users, 5);
        String actualUser = UsersUtils.convertToJson(user);
        String expectedUser = TestDataConfig.readTestUser().toString();

        Assert.assertEquals(actualUser, expectedUser, "User data is not equal");
    }

    @Test
    public void foundUser() {
        Response response = UsersSteps.getUserById("5");
        User user = response.as(User.class);
        String actualUser = UsersUtils.convertToJson(user);
        String expectedUser = TestDataConfig.readTestUser().toString();

        Assert.assertEquals(actualUser, expectedUser, "User data is not equal");
    }
}
