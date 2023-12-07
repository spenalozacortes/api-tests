package tests;

import api.PostsSteps;
import api.UsersSteps;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.PostResponse;
import models.UserResponse;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.JsonMapperUtils;
import utils.RandomUtils;
import utils.ResponseUtils;

import java.util.List;

public class ApiTests extends BaseTest {

    private static final String USER_PATH = "src/test/resources/apiresponses/user5Response.json";
    private static final int POST_ID = 99;
    private static final int POST_ID_NOT_FOUND = 150;
    private static final int USER_ID_GET = 10;
    private static final int USER_ID_POST = 1;
    private static final int USER_ID = 5;
    private static final int TITLE_LENGTH = 10;
    private static final int BODY_LENGTH = 50;
    private final UsersSteps usersSteps = new UsersSteps();
    private final PostsSteps postsSteps = new PostsSteps();

    @Test
    public void getPosts() {
        Response response = postsSteps.getPosts();
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK, "Status code is not 200");
        List<PostResponse> posts = List.of(response.as(PostResponse[].class));
        ResponseUtils.checkContentType(response, ContentType.JSON);
        Assert.assertTrue(postsSteps.arePostsSorted(posts), "Posts are not sorted in ascending order by id");
    }

    @Test
    public void foundPost() {
        Response response = postsSteps.getPostById(POST_ID);
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK, "Status code is not 200");
        PostResponse post = response.as(PostResponse.class);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(post.getUserId(), USER_ID_GET, "userId is incorrect");
        softAssert.assertEquals(post.getId(), POST_ID, "id is incorrect");
        softAssert.assertFalse(post.getTitle().isEmpty(), "title is empty");
        softAssert.assertFalse(post.getBody().isEmpty(), "body is empty");
        softAssert.assertAll();
    }

    @Test
    public void notFoundPost() {
        Response response = postsSteps.getPostById(POST_ID_NOT_FOUND);
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_NOT_FOUND, "Status code is not 404");
        ResponseUtils.isBodyEmpty(response);
    }

    @Test
    public void sendPost() {
        String randomTitle = RandomUtils.generateRandomString(TITLE_LENGTH);
        String randomBody = RandomUtils.generateRandomString(BODY_LENGTH);
        PostResponse post = new PostResponse();
        post.setTitle(randomTitle);
        post.setBody(randomBody);
        post.setUserId(USER_ID_POST);
        Response response = postsSteps.sendPost(post);
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_CREATED, "Status code is not 201");
        PostResponse actualPost = response.as(PostResponse.class);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualPost.getUserId(), USER_ID_POST, "userId is incorrect");
        softAssert.assertEquals(actualPost.getTitle(), randomTitle, "title is incorrect");
        softAssert.assertEquals(actualPost.getBody(), randomBody, "body is incorrect");
        softAssert.assertFalse(String.valueOf(actualPost.getId()).isEmpty(), "id is not present");
        softAssert.assertAll();
    }

    @Test
    public void getUsers() {
        Response response = usersSteps.getUsers();
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK, "Status code is not 200");
        ResponseUtils.checkContentType(response, ContentType.JSON);
        List<UserResponse> users = List.of(response.as(UserResponse[].class));
        UserResponse actualUser = UsersSteps.getUserFromListById(users, USER_ID);
        UserResponse expectedUser = JsonMapperUtils.deserialize(USER_PATH, UserResponse.class);
        Assert.assertEquals(actualUser, expectedUser, "User data is not as expected");
    }

    @Test
    public void foundUser() {
        Response response = usersSteps.getUserById(USER_ID);
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK, "Status code is not 200");
        UserResponse actualUser = response.as(UserResponse.class);
        UserResponse expectedUser = JsonMapperUtils.deserialize(USER_PATH, UserResponse.class);
        Assert.assertEquals(actualUser, expectedUser, "User data is not as expected");
    }
}
