import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pojos.UserRequest;
import pojos.CreateUserResponse;
import pojos.UserPojo;
import utils.RestWrapper;
import utils.UserGenerator;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static steps.UsersSteps.REQUEST_SPECIFICATION;


public class RestTest {
    private static RestWrapper api;

    @BeforeAll
    public static void prepareClient() {
        api = RestWrapper.loginAs("eve.holt@regres.in","cityslicka");
    }


    @Test
    void getUsersEmails() {
        List<String> emails = given()
                .spec(REQUEST_SPECIFICATION)
                .when().get()
                .then()
                .statusCode(200)
                .extract().jsonPath().getList("data.email");
    }

    @Test
    void getUsersEmailsWithPojo() {
        assertThat(api.user.getUsers()).extracting(UserPojo::getEmail).contains("george.bluth@reqres.in");
    }

    @Test
    public void createUser() {
        UserRequest rq = UserGenerator.getSimpleUser();
        CreateUserResponse rs = api.user.createUser(rq);

        assertThat(rs)
                .isNotNull()
                .extracting(CreateUserResponse::getName)
                .isEqualTo("simple");
    }
}

