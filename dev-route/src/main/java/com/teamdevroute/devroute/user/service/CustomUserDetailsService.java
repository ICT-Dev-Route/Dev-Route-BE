package com.teamdevroute.devroute.user.service;

import com.teamdevroute.devroute.global.auth.LoginUserInfo;
import com.teamdevroute.devroute.user.repository.UserRepository;
import com.teamdevroute.devroute.user.domain.CustomUserDetails;
import com.teamdevroute.devroute.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = userRepository.findById(parseUserId(id))
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저가 없습니다."));

        return new CustomUserDetails(buildLoginUserInfo(user));
    }

    private Long parseUserId(String id) {
        try{
            return Long.parseLong(id);
        } catch(NumberFormatException e) {
            throw new UsernameNotFoundException("적합하지 않은 유저 ID 형식입니다.");
        }
    }

    private LoginUserInfo buildLoginUserInfo(User user) {
        return LoginUserInfo.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .role(user.getUserRole())
                .build();
    }
}
