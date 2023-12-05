package tests;

import api.PostsSteps;
import api.UsersSteps;
import config.TestDataConfig;
import io.restassured.response.Response;
import models.PostResponse;
import models.UserResponse;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PostsUtils;
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
        List<PostResponse> posts = List.of(response.as(PostResponse[].class));

        Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK, "Status code is not 200");
        Assert.assertTrue(response.getContentType().contains(CONTENT_TYPE), "Response body is not JSON");
        Assert.assertTrue(PostsSteps.arePostsSorted(posts), "Posts are not sorted in ascending order by id");
    }

    @Test
    public void foundPost() {
        Response response = PostsSteps.getPostById(POST_ID);
        PostResponse post = response.as(PostResponse.class);

        Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK, "Status code is not 200");
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
        String post = PostsUtils.createPost(randomTitle, randomBody, USER_ID_POST);
        Response response = PostsSteps.sendPost(post);
        PostResponse actualPost = response.as(PostResponse.class);

        Assert.assertEquals(response.statusCode(), HttpStatus.SC_CREATED, "Status code is not 201");
        Assert.assertEquals(actualPost.getUserId(), USER_ID_POST, "userId is incorrect");
        Assert.assertEquals(actualPost.getTitle(), randomTitle, "title is incorrect");
        Assert.assertEquals(actualPost.getBody(), randomBody, "body is incorrect");
        Assert.assertFalse(String.valueOf(actualPost.getId()).isEmpty(), "id is not present");
    }

    @Test
    public void getUsers() {
        Response response = UsersSteps.getUsers();
        List<UserResponse> users = List.of(response.as(UserResponse[].class));
        UserResponse user = UsersUtils.getUserFromListById(users, USER_ID);
        String actualUser = UsersUtils.convertToJson(user);
        String expectedUser = TestDataConfig.readTestUser().toString();

        Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK, "Status code is not 200");
        Assert.assertEquals(actualUser, expectedUser, "User data is not equal");
    }

    @Test
    public void foundUser() {
        Response response = UsersSteps.getUserById(USER_ID);
        UserResponse user = response.as(UserResponse.class);
        String actualUser = UsersUtils.convertToJson(user);
        String expectedUser = TestDataConfig.readTestUser().toString();

        Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK, "Status code is not 200");
        Assert.assertEquals(actualUser, expectedUser, "User data is not equal");
    }
}
