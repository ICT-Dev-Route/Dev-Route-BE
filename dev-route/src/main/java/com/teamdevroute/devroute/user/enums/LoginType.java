package com.teamdevroute.devroute.user.enums;


import java.util.Locale;

public enum LoginType {
    KAKAO,
    NAVER,
    GOOGLE,
    NORMAL;

    public static LoginType toEnum(String name) {
        return switch (name.toLowerCase()) {
            case "google" -> GOOGLE;
            case "kakao" -> KAKAO;
            case "naver" -> NAVER;
            default -> NORMAL;
        };
    }
}
