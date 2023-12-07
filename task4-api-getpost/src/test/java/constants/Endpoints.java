package constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Endpoints {

    public static final String POSTS = "/posts";
    public static final String USERS = "/users";
    public static final String POST_BY_ID = String.format("%s/{%s}", POSTS, Parameters.ID);
    public static final String USER_BY_ID = String.format("%s/{%s}", USERS, Parameters.ID);
}
