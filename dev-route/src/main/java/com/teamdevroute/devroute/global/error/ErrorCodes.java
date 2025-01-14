package com.teamdevroute.devroute.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodes {
    // Common
    INTERNAL_SERVER_ERROR("COMMON-002", "서버에서 처리할 수 없는 경우"),

    // Business
    DUPLICATE("BUSINESS-001", "중복됐을 경우"),
    NOT_FOUND("BUSINESS-002", "찾을 수 없는 경우"),

    // User
    USER_NOT_FOUND("USER-001", "유저를 찾을 수 없는 경우"),
    UNAUTHORIZED("USER-002", "인가에 실패한 경우"),
    UNAUTHENTICATED("USER-003", "인증에 실패한 경우"),
    DUPLICATE_USER_EMAIL("USER-004", "이메일이 중복된 경우"),

    // Bookmark
    DUPLICATE_BOOKMARK_COMPANY("BOOKMARK-001", "북마크의 기업이 중복된 경우"),
    DUPLICATE_BOOKMARK_VIDEO("BOOKMARK-002", "북마크의 영상이 중복된 경우"),
    DUPLICATE_BOOKMARK_ROADMAP("BOOKMARK-003", "북마크의 로드맵이 중복된 경우"),

    // Company
    COMPANY_NOT_FOUND("COMPANY-001", "기업을 찾을 수 없는 경우");

    private final String code;
    private final String message;
}
