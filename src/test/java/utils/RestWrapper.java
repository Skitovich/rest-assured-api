package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.specification.RequestSpecification;
import pojos.CreateUserRequest;
import pojos.CreateUserResponse;
import pojos.UserLogin;
import pojos.UserPojo;

import java.util.List;

import static io.restassured.RestAssured.given;

public class RestWrapper {
    private static final String BASE_URL = "https://reqres.in/api/";
    private static RequestSpecification REQUEST_SPECIFICATION;
    private Cookies cookies;

    private RestWrapper(Cookies cookies) {
        this.cookies = cookies;

        REQUEST_SPECIFICATION = new RequestSpecBuilder()
                .addCookies(cookies)
                .setBaseUri("https://reqres.in/api/")
                .setContentType(ContentType.JSON)
                .build();
    }

    public static RestWrapper loginAs(String login, String password) {
        Cookies cookies = given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .basePath("/login")
                .body(new UserLogin(login, password))
                .post()
                .getDetailedCookies();

        return new RestWrapper(cookies);
    }

    public CreateUserResponse createUser(CreateUserRequest rq) {
        return given().spec(REQUEST_SPECIFICATION).body(rq).post().as(CreateUserResponse.class);
    }

    public List<UserPojo> getUsers() {
        return given().spec(REQUEST_SPECIFICATION)
                .get()
                .jsonPath().getList("data", UserPojo.class);
    }

    public List<UserPojo> getOrders() {
        return given().spec(REQUEST_SPECIFICATION)
                .get()
                .jsonPath().getList("data", UserPojo.class);
    }
}
