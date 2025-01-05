package com.teamdevroute.devroute.user.dto;

import lombok.Builder;

@Builder
public record UserAuthResponse(
        String email,
        String name
) {

}
