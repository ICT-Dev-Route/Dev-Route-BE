package com.teamdevroute.devroute.user.service;

import com.teamdevroute.devroute.bookmark.BookmarkService;
import com.teamdevroute.devroute.global.auth.Oauth2Util;
import com.teamdevroute.devroute.global.auth.LoginUserInfo;
import com.teamdevroute.devroute.global.auth.jwt.JwtUtils;
import com.teamdevroute.devroute.global.exception.DuplicateUserException;
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

import java.util.Optional;

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
            throw new DuplicateUserException();
        }
        User user = userRepository.save(request.toEntity(LoginType.NORMAL.name(), encoder.encode(request.password())));
        bookmarkService.createBookmark(user);
        return UserCreateResponse.of(user);
    }

    public String login(UserLoginRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();

        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);

        if(!encoder.matches(password, user.getPassword())){
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }
        LoginUserInfo loginUserInfo = LoginUserInfo.builder()
                .id(user.getId())
                .role(user.getUserRole())
                .name(user.getName())
                .email(user.getEmail())
                .password(encoder.encode(user.getPassword()))
                .build();

        return jwtUtils.create(loginUserInfo);
    }


    public String getRedirectUrl(LoginType loginType) {
        switch (loginType) {
            case KAKAO -> {
                return oauth2Util.getKakaoRedirectUrl();
            }
            case GOOGLE -> {
                return oauth2Util.getGoogleRedirectUrl();
            }
            case NAVER -> {
                return oauth2Util.getNaverRedirectUrl();
            }
        }
        return null;
    }

    public String getAccessToken(String authorizationCode, LoginType loginType, String state){
        String accessToken = null;
        switch (loginType) {
            case KAKAO -> {
                accessToken = oauth2Util.getKakaoAccessToken(authorizationCode);
            }
            case GOOGLE -> {
                accessToken = oauth2Util.getGoogleAccessToken(authorizationCode);
            }
            case NAVER -> {
                accessToken = oauth2Util.getNaverAccessToken(authorizationCode, state);
            }
        }
        return accessToken;
    }

    public String authLogin(String accessToken, LoginType loginType) {
        UserAuthResponse userAuthResponse = null;
        switch (loginType) {
            case KAKAO -> {
                userAuthResponse = oauth2Util.getKakaoUserInformation(accessToken);
            }
            case GOOGLE -> {
                userAuthResponse = oauth2Util.getGoogleUserInformation(accessToken);
            }
            case NAVER -> {
                userAuthResponse = oauth2Util.getNaverUserInformation(accessToken);
            }
        }

        Optional<User> findUser = userRepository.findByEmail(userAuthResponse.email());
        User user;
        if(findUser.isEmpty()){
            User createdUser = User.builder()
                    .email(userAuthResponse.email())
                    .name(userAuthResponse.name())
                    .userRole("USER")
                    .developField(DevelopField.NONE)
                    .loginType(loginType.toString())
                    .build();

            user = userRepository.save(createdUser);
            bookmarkService.createBookmark(user);
        }
        else{
            user = findUser.get();
        }

        LoginUserInfo loginUserInfo = LoginUserInfo.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getUserRole())
                .name(user.getName())
                .build();

        return jwtUtils.create(loginUserInfo);
    }

    private boolean checkEmailDuplicate(String email) {
        return userRepository.existsByEmail(email);
    }

    public User findByUserId(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public UserMyPageResponse readMyPage(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return UserMyPageResponse.of(user);
    }
}

