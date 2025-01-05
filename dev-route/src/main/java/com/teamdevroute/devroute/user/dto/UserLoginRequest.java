package com.teamdevroute.devroute.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UserLoginRequest(
        @NotNull(message = "이메일은 null 일 수 없습니다.")
        @Email(message = "유효한 이메일 형식이어야 합니다.")
        String email,

        @NotNull(message = "비밀번호는 null 일 수 없습니다.")
        String password
) {

}
