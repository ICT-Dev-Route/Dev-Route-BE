package com.teamdevroute.devroute.user.dto;

import com.teamdevroute.devroute.user.domain.User;
import com.teamdevroute.devroute.user.enums.DevelopField;
import com.teamdevroute.devroute.user.enums.UserRole;
import lombok.Builder;

@Builder
public record UserCreateRequest (
       String email,
       String password,
       String name,
       DevelopField development_field,
       String loginType
) {

    public User toEntity(String loginType, String encoded){
        return User.builder()
                .email(email)
                .name(name)
                .password(encoded)
                .developField(DevelopField.NONE)
                .loginType(loginType)
                .userRole(UserRole.USER.name())
                .build();
    }
}
