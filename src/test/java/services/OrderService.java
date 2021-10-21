package services;

import io.restassured.http.Cookies;
import pojos.UserPojo;

import java.util.List;

import static io.restassured.RestAssured.given;

public class OrderService extends RestService {

    @Override
    protected String getBasePath() {
        return "/orders";
    }

    public OrderService(Cookies cookies) {
        super(cookies);
    }

    public List<UserPojo> getOrders() {
        return given().spec(REQUEST_SPECIFICATION)
                .get("/orders")
                .jsonPath().getList("data", UserPojo.class);
    }
}
