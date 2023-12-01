package tests;

import api.PostsSteps;
import api.UsersSteps;
import config.EnvironmentConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.Post;
import models.User;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ApiTests {

    @Test
    public void apiTest() {
        RestAssured.baseURI = EnvironmentConfig.getBaseURI();

        given().when().get("/posts")
                .then().assertThat().statusCode(200);

        Post post1 = PostsSteps.getPostById("99").as(Post.class);
        System.out.println(post1.getBody());

        Response post2 = PostsSteps.getPostById("150");
        System.out.println(post2.statusCode());

        Post createdPost = PostsSteps.createPost("{" +
                "\"title\": \"foo\"," +
                "\"body\": \"bar\"," +
                "\"userId\": \"1\"}").as(Post.class);
        System.out.println(createdPost.getTitle());

        given().when().get("/users")
                .then().assertThat().statusCode(200);

        User user = UsersSteps.getUserById("5").as(User.class);
        System.out.println(user.getUsername());
    }
}
