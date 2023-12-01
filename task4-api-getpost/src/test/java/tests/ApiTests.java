package tests;

import api.PostsSteps;
import api.UsersSteps;
import io.restassured.response.Response;
import models.Post;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PostsUtils;
import utils.UsersUtils;

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
        Post post = response.as(Post.class);
        Assert.assertEquals(response.statusCode(), 201, "Status code is not 201");
        Assert.assertEquals(post.getUserId(), 1, "userId is incorrect");
        Assert.assertEquals(post.getTitle(), "foo", "title is incorrect");
        Assert.assertEquals(post.getBody(), "bar", "body is incorrect");
        Assert.assertFalse(String.valueOf(post.getId()).isEmpty(), "id is not present");
    }

    @Test
    public void getUsers() {
        Response response = UsersSteps.getUsers();
        List<User> users = List.of(response.as(User[].class));
        User user = UsersUtils.getUserFromListById(users,5);
        Assert.assertEquals(response.statusCode(), 200, "Status code is not 200");
        Assert.assertTrue(response.getContentType().contains("application/json"), "Response body is not JSON");
        Assert.assertEquals(user.getName(), "Chelsey Dietrich", "name is incorrect");
        Assert.assertEquals(user.getUsername(), "Kamren", "username is incorrect");
        Assert.assertEquals(user.getEmail(), "Lucio_Hettinger@annie.ca", "email is incorrect");
        Assert.assertEquals(user.getAddress().getStreet(), "Skiles Walks", "street is incorrect");
        Assert.assertEquals(user.getAddress().getSuite(), "Suite 351", "suite is incorrect");
        Assert.assertEquals(user.getAddress().getCity(), "Roscoeview", "city is incorrect");
        Assert.assertEquals(user.getAddress().getZipcode(), "33263", "zipcode is incorrect");
        Assert.assertEquals(user.getAddress().getGeo().getLat(), "-31.8129", "lat is incorrect");
        Assert.assertEquals(user.getAddress().getGeo().getLng(), "62.5342", "lng is incorrect");
        Assert.assertEquals(user.getPhone(), "(254)954-1289", "phone is incorrect");
        Assert.assertEquals(user.getWebsite(), "demarco.info", "website is incorrect");
        Assert.assertEquals(user.getCompany().getName(), "Keebler LLC", "company name is incorrect");
        Assert.assertEquals(user.getCompany().getCatchPhrase(), "User-centric fault-tolerant solution", "catchphrase is incorrect");
        Assert.assertEquals(user.getCompany().getBs(), "revolutionize end-to-end systems", "bs is incorrect");
    }

    @Test
    public void foundUser() {
        Response response = UsersSteps.getUserById("5");
        Assert.assertEquals(response.statusCode(), 200, "Status code is not 200");
    }
}
