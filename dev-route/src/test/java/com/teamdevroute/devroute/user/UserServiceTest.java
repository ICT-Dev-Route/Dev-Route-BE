package com.teamdevroute.devroute.user;

import com.teamdevroute.devroute.user.domain.User;
import com.teamdevroute.devroute.user.dto.UserCreateRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @DisplayName("유저 회원가입이 잘 되는지 확인한다.")
    @Test
    void create_user() {
        User user = UserFixture.userWithName("name");
        UserCreateRequest request = UserCreateRequest.builder()
                .email(user.getEmail())
                .name(user.getName())
                .development_field(user.getDevelopField())
                .password(user.getPassword())
                .build();

        assertThatCode(() -> userService.createUser(request)).doesNotThrowAnyException();
    }
}
