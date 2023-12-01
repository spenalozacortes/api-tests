package tests;

import api.PostsSteps;
import api.UsersSteps;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.post.Post;
import models.user.User;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ApiTests {

    @Test
    public void apiTest() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";

        PostsSteps postsSteps = new PostsSteps();

        given().when().get("/posts")
                .then().assertThat().statusCode(200);

        Post post1 = postsSteps.getPostById("99").as(Post.class);
        System.out.println(post1.getBody());

        Response post2 = postsSteps.getPostById("150");
        System.out.println(post2.statusCode());

        Post createdPost = postsSteps.createPost("{" +
                "\"title\": \"foo\"," +
                "\"body\": \"bar\"," +
                "\"userId\": \"1\"}").as(Post.class);
        System.out.println(createdPost.getTitle());

        UsersSteps usersSteps = new UsersSteps();

        given().when().get("/users")
                .then().assertThat().statusCode(200);

        User user = usersSteps.getUserById("5").as(User.class);
        System.out.println(user.getUsername());
    }
}
