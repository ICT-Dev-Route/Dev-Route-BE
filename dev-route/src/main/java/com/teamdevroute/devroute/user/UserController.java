package com.teamdevroute.devroute.user;

import com.teamdevroute.devroute.global.auth.LoginUserInfo;
import com.teamdevroute.devroute.user.domain.CustomUserDetails;
import com.teamdevroute.devroute.user.dto.UserCreateRequest;
import com.teamdevroute.devroute.user.dto.UserCreateResponse;
import com.teamdevroute.devroute.user.enums.LoginType;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
public class UserController {
    private final UserService userService;

    @Value("${domain.url}")
    private String DOMAIN_URL;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity createUser(@RequestBody UserCreateRequest request) {
        UserCreateResponse response = userService.createUser(request);
        return ResponseEntity.created(URI.create("/users/" + response.id())).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody UserLoginRequest loginRequest
    ) throws IOException {
        String token = userService.login(loginRequest);
        log.info("토큰: " + token);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @GetMapping("/auth/kakao")
    public ResponseEntity getKakaoRedirectUrl() {
        String url = userService.getRedirectUrl(LoginType.KAKAO);
        return new ResponseEntity<>(url, HttpStatus.OK);
    }

    @GetMapping("/auth/google")
    public ResponseEntity getGoogleRedirectUrl() {
        String url = userService.getRedirectUrl(LoginType.GOOGLE);
        return new ResponseEntity<>(url, HttpStatus.OK);
    }

    @GetMapping("/auth/naver")
    public ResponseEntity getNaverRedirectUrl() {
        String url = userService.getRedirectUrl(LoginType.NAVER);
        return new ResponseEntity<>(url, HttpStatus.OK);
    }

    @GetMapping("/auth/kakao/callback")
    public ResponseEntity<String> kakaoCallback(@RequestParam("code") String code, HttpSession session, HttpServletResponse response) throws IOException {
        String accessToken = userService.getAccessToken(code, LoginType.KAKAO, null);
        String token = userService.authLogin(accessToken, LoginType.KAKAO);

        session.setAttribute("token" ,"Bearer " + token);
        response.sendRedirect(DOMAIN_URL + "/mainpage");

        return new ResponseEntity<>("유저가 로그인되었습니다.", HttpStatus.OK);
    }


    @GetMapping("/auth/google/callback")
    public ResponseEntity<String> googleCallback(@RequestParam("code") String code, HttpSession session, HttpServletResponse response) throws IOException {
        String accessToken = userService.getAccessToken(code, LoginType.GOOGLE, null);
        String token = userService.authLogin(accessToken, LoginType.GOOGLE);

        session.setAttribute("token" ,"Bearer " + token);
        response.sendRedirect(DOMAIN_URL + "/mainpage");

        return new ResponseEntity<>("유저가 로그인되었습니다.", HttpStatus.OK);
    }

    @GetMapping("/auth/naver/callback")
    public ResponseEntity<String> naverCallback(@RequestParam("code") String code, @RequestParam("state") String state, HttpSession session, HttpServletResponse response) throws IOException {
        String accessToken = userService.getAccessToken(code, LoginType.NAVER, state);
        String token = userService.authLogin(accessToken, LoginType.NAVER);

        session.setAttribute("token" ,"Bearer " + token);
        response.sendRedirect(DOMAIN_URL + "/mainpage");
        return new ResponseEntity<>("유저가 로그인되었습니다.", HttpStatus.OK);
    }

    @GetMapping("/token")
    public ResponseEntity<String> getToken(HttpSession session) {
        String token = (String) session.getAttribute("token");
        if (token != null) {
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No token found");
        }
    }

    @GetMapping("/login/test")
    public ResponseEntity<String> loginTest(@AuthenticationPrincipal CustomUserDetails userDetails) throws IOException {
        LoginUserInfo user = userDetails.user();
        System.out.println("user = " + user.toString());
        return new ResponseEntity<>("유저가 로그인되었습니다.", HttpStatus.OK);
    }

    @GetMapping("/mypage")
    public ResponseEntity readMyPage(@AuthenticationPrincipal CustomUserDetails userDetails){
        LoginUserInfo user = userDetails.user();
        return ResponseEntity.ok(userService.readMyPage(user.getId()));
    }

    @GetMapping("/healthcheck")
    public String healthcheck(){
        return "OK";
    }
}