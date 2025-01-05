package com.teamdevroute.devroute.user.dto;

import com.teamdevroute.devroute.user.domain.User;
import lombok.Builder;

@Builder
public record UserCreateResponse (
    Long id,
    String email,
    String name
) {

    public static UserCreateResponse of(User user) {
        return UserCreateResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName()).build();
    }
}
