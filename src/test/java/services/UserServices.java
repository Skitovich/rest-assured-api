package services;

import io.restassured.http.Cookies;
import io.restassured.specification.RequestSpecification;
import pojos.CreateUserRequest;
import pojos.CreateUserResponse;
import pojos.UserPojo;


import java.util.List;

import static io.restassured.RestAssured.given;


public class UserServices extends RestService {


    public UserServices(Cookies cookies) {
        super(cookies);
    }

    public CreateUserResponse createUser(CreateUserRequest rq) {
        return given().spec(REQUEST_SPECIFICATION).body(rq).post().as(CreateUserResponse.class);
    }

    public List<UserPojo> getUsers() {
        return given().spec(REQUEST_SPECIFICATION)
                .get()
                .jsonPath().getList("data", UserPojo.class);
    }

}
