package com.teamdevroute.devroute.user.enums;

import java.util.Map;

public enum DevelopField {
    BACKEND,
    FRONTEND,
    MOBILE,
    AI,
    DATA_SCIENCE,
    NONE;

    private static final Map<String, DevelopField> TYPE_MAP = Map.of(
            "backend", BACKEND,
            "frontend", FRONTEND,
            "ai", AI,
            "mobile", MOBILE,
            "datascience", DATA_SCIENCE
    );

    private static final Map<String, DevelopField> SEARCH_KEYWORD_MAP = Map.of(
            "백엔드", BACKEND,
            "프론트엔드", FRONTEND,
            "인공지능", AI,
            "모바일", MOBILE,
            "데이터", DATA_SCIENCE
    );

    public static DevelopField toEnum(String type) {
        return TYPE_MAP.getOrDefault(type.toLowerCase(), NONE);
    }

    public static DevelopField toEnumBySearchKeyWord(String searchKeyword) {
        return SEARCH_KEYWORD_MAP.getOrDefault(searchKeyword.toLowerCase(), NONE);
    }
}
