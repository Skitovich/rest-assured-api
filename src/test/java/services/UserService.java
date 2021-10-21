package services;

import io.restassured.http.Cookies;
import pojos.UserRequest;
import pojos.CreateUserResponse;
import pojos.UserPojo;


import java.util.List;

import static io.restassured.RestAssured.given;


public class UserService extends RestService {


    @Override
    protected String getBasePath() {
        return "/users";
    }

    public UserService(Cookies cookies) {
        super(cookies);
    }

    public CreateUserResponse createUser(UserRequest rq) {
        return given().spec(REQUEST_SPECIFICATION).body(rq).post().as(CreateUserResponse.class);
    }

    public List<UserPojo> getUsers() {
        return given().spec(REQUEST_SPECIFICATION)
                .get()
                .jsonPath().getList("data", UserPojo.class);
    }

}
