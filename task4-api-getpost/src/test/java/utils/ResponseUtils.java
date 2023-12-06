package utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

public class ResponseUtils {

    private static final String BODY = "{}";

    public static boolean checkContentType(Response response, ContentType contentType) {
        return response
                .getContentType()
                .contains(contentType.toString());
    }

    public static void isBodyEmpty(Response response) {
        response.then()
                .assertThat()
                .body(equalTo(BODY));
    }
}
