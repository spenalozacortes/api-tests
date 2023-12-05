package tests;

import api.PostsSteps;
import api.UsersSteps;
import com.google.gson.JsonObject;
import config.TestDataConfig;
import io.restassured.response.Response;
import models.PostResponse;
import models.UserResponse;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.JsonMapperUtils;
import utils.RandomUtils;
import utils.UsersUtils;

import java.util.List;

public class ApiTests extends BaseTest {

    private static final String CONTENT_TYPE = "json";
    private static final String BODY = "{}";
    private static final int POST_ID = 99;
    private static final int POST_ID_NOT_FOUND = 150;
    private static final int USER_ID_GET = 10;
    private static final int USER_ID_POST = 1;
    private static final int USER_ID = 5;

    @Test
    public void getPosts() {
        Response response = PostsSteps.getPosts();
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK, "Status code is not 200");
        List<PostResponse> posts = List.of(response.as(PostResponse[].class));
        Assert.assertTrue(response.getContentType().contains(CONTENT_TYPE), "Response body is not JSON");
        Assert.assertTrue(PostsSteps.arePostsSorted(posts), "Posts are not sorted in ascending order by id");
    }

    @Test
    public void foundPost() {
        Response response = PostsSteps.getPostById(POST_ID);
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK, "Status code is not 200");
        PostResponse post = response.as(PostResponse.class);
        Assert.assertEquals(post.getUserId(), USER_ID_GET, "userId is incorrect");
        Assert.assertEquals(post.getId(), POST_ID, "id is incorrect");
        Assert.assertFalse(post.getTitle().isEmpty(), "title is empty");
        Assert.assertFalse(post.getBody().isEmpty(), "body is empty");
    }

    @Test
    public void notFoundPost() {
        Response response = PostsSteps.getPostById(POST_ID_NOT_FOUND);
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_NOT_FOUND, "Status code is not 404");
        Assert.assertEquals(response.getBody().asString(), BODY, "Response body is not empty");
    }

    @Test
    public void sendPost() {
        String randomTitle = RandomUtils.generateRandomString(10);
        String randomBody = RandomUtils.generateRandomString(50);
        PostResponse post = new PostResponse();
        post.setTitle(randomTitle);
        post.setBody(randomBody);
        post.setUserId(USER_ID_POST);
        Response response = PostsSteps.sendPost(post);
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_CREATED, "Status code is not 201");
        PostResponse actualPost = response.as(PostResponse.class);
        Assert.assertEquals(actualPost.getUserId(), USER_ID_POST, "userId is incorrect");
        Assert.assertEquals(actualPost.getTitle(), randomTitle, "title is incorrect");
        Assert.assertEquals(actualPost.getBody(), randomBody, "body is incorrect");
        Assert.assertFalse(String.valueOf(actualPost.getId()).isEmpty(), "id is not present");
    }

    @Test
    public void getUsers() {
        Response response = UsersSteps.getUsers();
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK, "Status code is not 200");
        List<UserResponse> users = List.of(response.as(UserResponse[].class));
        UserResponse actualUser = UsersUtils.getUserFromListById(users, USER_ID);
        UserResponse expectedUser = JsonMapperUtils.deserialize(TestDataConfig.readTestUser(), UserResponse.class);
        Assert.assertEquals(actualUser, expectedUser, "User data is not as expected");
    }

    @Test
    public void foundUser() {
        Response response = UsersSteps.getUserById(USER_ID);
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK, "Status code is not 200");
        UserResponse actualUser = response.as(UserResponse.class);
        UserResponse expectedUser = JsonMapperUtils.deserialize(TestDataConfig.readTestUser(), UserResponse.class);
        Assert.assertEquals(actualUser, expectedUser, "User data is not as expected");
    }
}
