package com.teamdevroute.devroute.user.service;

import com.teamdevroute.devroute.global.auth.LoginUserInfo;
import com.teamdevroute.devroute.user.UserRepository;
import com.teamdevroute.devroute.user.domain.CustomUserDetails;
import com.teamdevroute.devroute.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
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
        User user = userRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저가 없습니다."));

        LoginUserInfo userInfo = LoginUserInfo.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .role(user.getUserRole())
                .build();

        return new CustomUserDetails(userInfo);
    }
}
