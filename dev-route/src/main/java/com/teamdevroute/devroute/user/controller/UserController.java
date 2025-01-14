package com.teamdevroute.devroute.user.controller;

import com.teamdevroute.devroute.global.auth.LoginUserInfo;
import com.teamdevroute.devroute.user.dto.UserLoginRequest;
import com.teamdevroute.devroute.user.service.UserService;
import com.teamdevroute.devroute.user.domain.CustomUserDetails;
import com.teamdevroute.devroute.user.dto.UserCreateRequest;
import com.teamdevroute.devroute.user.dto.UserCreateResponse;
import com.teamdevroute.devroute.user.enums.LoginType;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Value("${domain.url}")
    private String DOMAIN_URL;

    @PostMapping("/signup")
    public ResponseEntity<UserCreateResponse> createUser(@RequestBody UserCreateRequest request) {
        UserCreateResponse response = userService.createUser(request);
        return ResponseEntity.created(URI.create("/users/" + response.id())).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest loginRequest) throws IOException {
        String token = userService.login(loginRequest);
        log.info("생성된 토큰: {}", token);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @GetMapping("/auth/{provider}")
    public ResponseEntity<String> getRedirectUrl(@PathVariable String provider) {
        LoginType loginType = LoginType.toEnum(provider);
        String url = userService.getRedirectUrl(loginType);
        return ResponseEntity.ok(url);
    }

    @GetMapping("/auth/{provider}/callback")
    public void handleCallback(
            @PathVariable String provider,
            @RequestParam("code") String code,
            HttpSession session,
            HttpServletResponse response
    ) throws IOException {
        LoginType loginType = LoginType.toEnum(provider);
        String accessToken = userService.getAccessToken(code, loginType, null);
        String token = userService.authLogin(accessToken, LoginType.KAKAO);

        session.setAttribute("token", "Bearer " + token);
        response.sendRedirect(DOMAIN_URL + "/mainpage");
    }

    @GetMapping("/token")
    public ResponseEntity<String> getToken(HttpSession session) {
        String token = (String) session.getAttribute("token");
        if (token != null) {
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("토큰을 찾을 수 없습니다.");
        }
    }

    @GetMapping("/login/test")
    public ResponseEntity<String> loginTest(@AuthenticationPrincipal CustomUserDetails userDetails) throws IOException {
        LoginUserInfo user = userDetails.user();
        log.info("유저 정보: {}", user.toString());
        return ResponseEntity.ok("유저가 로그인되었습니다.");
    }

    @GetMapping("/mypage")
    public ResponseEntity<?> readMyPage(@AuthenticationPrincipal CustomUserDetails userDetails) {
        LoginUserInfo user = userDetails.user();
        return ResponseEntity.ok(userService.readMyPage(user.getId()));
    }

    @GetMapping("/healthcheck")
    public String healthcheck() {
        return "OK";
    }
}