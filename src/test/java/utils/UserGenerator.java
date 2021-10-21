package utils;

import pojos.CreateUserRequest;

public class UserGenerator {

    public static CreateUserRequest getSimpleUser() {
        return CreateUserRequest.builder()
                .name("simple")
                .job("automation")
                .build();
    }
}
