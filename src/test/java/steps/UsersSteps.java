package steps;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pojos.UserRequest;
import pojos.CreateUserResponse;
import pojos.UserPojo;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UsersSteps {
    public static final RequestSpecification REQUEST_SPECIFICATION =
            new RequestSpecBuilder()
                    .setBaseUri("https://reqres.in/api/")
                    .setBasePath("/users")
                    .setContentType(ContentType.JSON)
                    .build();


    private CreateUserResponse user;

    public CreateUserResponse createUser(UserRequest rq) {
        user = given().spec(REQUEST_SPECIFICATION).body(rq).post().as(CreateUserResponse.class);
        return user;
    }

    public static List<UserPojo> getUsers() {
        return given().spec(REQUEST_SPECIFICATION)
                .get()
                .jsonPath().getList("data", UserPojo.class);
    }

    public static UserPojo getUser(int id) {
        return given().get("/" + id).as(UserPojo.class);
    }
}
