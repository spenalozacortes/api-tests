package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.experimental.UtilityClass;

import static org.hamcrest.Matchers.equalTo;

@UtilityClass
public class ResponseUtils {

    private static final String BODY = "{}";

    public static void checkContentType(Response response, ContentType contentType) {
        response.then()
                .assertThat()
                .contentType(contentType);
    }

    public static void isBodyEmpty(Response response) {
        response.then()
                .assertThat()
                .body(equalTo(BODY));
    }
}
