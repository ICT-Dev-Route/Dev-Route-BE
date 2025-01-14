package com.teamdevroute.devroute.user.service;

import com.teamdevroute.devroute.bookmark.BookmarkService;
import com.teamdevroute.devroute.global.auth.Oauth2Util;
import com.teamdevroute.devroute.global.auth.LoginUserInfo;
import com.teamdevroute.devroute.global.auth.jwt.JwtUtils;
import com.teamdevroute.devroute.global.exception.UserDuplicateException;
import com.teamdevroute.devroute.global.exception.UserNotFoundException;
import com.teamdevroute.devroute.user.dto.UserLoginRequest;
import com.teamdevroute.devroute.user.repository.UserRepository;
import com.teamdevroute.devroute.user.domain.User;
import com.teamdevroute.devroute.user.dto.UserAuthResponse;
import com.teamdevroute.devroute.user.dto.UserCreateRequest;
import com.teamdevroute.devroute.user.dto.UserCreateResponse;
import com.teamdevroute.devroute.user.dto.UserMyPageResponse;
import com.teamdevroute.devroute.user.enums.DevelopField;
import com.teamdevroute.devroute.user.enums.LoginType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {

    private final BookmarkService bookmarkService;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder encoder;
    private final Oauth2Util oauth2Util;

    public UserCreateResponse createUser(UserCreateRequest request) {
        if(checkEmailDuplicate(request.email())){
            throw new UserDuplicateException(request.email());
        }
        User user = userRepository.save(request.toEntity(LoginType.NORMAL.name(), encoder.encode(request.password())));
        bookmarkService.createBookmark(user);
        return UserCreateResponse.of(user);
    }

    public String login(UserLoginRequest request) {
        User user = userRepository.findByEmail(request.email()).orElseThrow(UserNotFoundException::new);

        if(!encoder.matches(request.password(), user.getPassword())){
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

        LoginUserInfo loginUserInfo = createLoginUserInfo(user);
        return jwtUtils.create(loginUserInfo);
    }

    public String getRedirectUrl(LoginType loginType) {
        return switch (loginType) {
            case KAKAO -> oauth2Util.getKakaoRedirectUrl();
            case GOOGLE -> oauth2Util.getGoogleRedirectUrl();
            case NAVER -> oauth2Util.getNaverRedirectUrl();
            default -> throw new IllegalArgumentException("지원하지 않는 로그인 타입입니다. 로그인타입: " + loginType);
        };
    }

    public String getAccessToken(String authorizationCode, LoginType loginType, String state){
        return switch (loginType) {
            case KAKAO ->  oauth2Util.getKakaoAccessToken(authorizationCode);
            case GOOGLE -> oauth2Util.getGoogleAccessToken(authorizationCode);
            case NAVER -> oauth2Util.getNaverAccessToken(authorizationCode, state);
            default -> throw new IllegalArgumentException("원하지 않는 로그인 타입입니다. 로그인타입: " + loginType);
        };
    }

    public String authLogin(String accessToken, LoginType loginType) {
        UserAuthResponse userAuthResponse = getUserAuthResponse(accessToken, loginType);
        User user = userRepository.findByEmail(userAuthResponse.email())
                .orElseGet(() -> createUserFromOAuth(userAuthResponse, loginType));

        LoginUserInfo loginUserInfo = createLoginUserInfo(user);
        return jwtUtils.create(loginUserInfo);
    }

    public UserMyPageResponse readMyPage(Long id) {
        User user = findByUserId(id);
        return UserMyPageResponse.of(user);
    }

    private User createUserFromOAuth(UserAuthResponse userAuthResponse, LoginType loginType){
        User newUser = User.builder()
                .email(userAuthResponse.email())
                .name(userAuthResponse.name())
                .userRole("USER")
                .developField(DevelopField.NONE)
                .loginType(loginType.toString())
                .build();

        User savedUser = userRepository.save(newUser);
        bookmarkService.createBookmark(savedUser);
        return savedUser;
    }

    private UserAuthResponse getUserAuthResponse(String accessToken, LoginType loginType) {
        return switch (loginType) {
            case KAKAO -> oauth2Util.getKakaoUserInformation(accessToken);
            case GOOGLE -> oauth2Util.getGoogleUserInformation(accessToken);
            case NAVER -> oauth2Util.getNaverUserInformation(accessToken);
            default -> throw new IllegalArgumentException("원하지 않는 로그인 타입입니다. 로그인타입: " + loginType);
        };
    }

    private LoginUserInfo createLoginUserInfo(User user) {
        return LoginUserInfo.builder()
                .id(user.getId())
                .role(user.getUserRole())
                .name(user.getName())
                .email(user.getEmail())
                .password(encoder.encode(user.getPassword()))
                .build();
    }

    private boolean checkEmailDuplicate(String email) {
        return userRepository.existsByEmail(email);
    }

    public User findByUserId(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }
}

