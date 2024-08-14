package com.teamdevroute.devroute.roadmap.description;

import java.util.Arrays;
import java.util.List;

public final class Frontend {
   public static String[] stepsFrontendNames = {
            "HTML",
            "CSS",
            "JavaScript",
            "Git GitHub",
            "Npm",
            "React, Next.js, Vue.js"
    };
    public static String[] stepsFrontendBriefNames  = {
            "웹 페이지 마크업 언어.",
            "웹 페이지 스타일 지정 언어.",
            "동적 웹 콘텐츠 생성.",
            "소스 코드 버전 관리.",
            "패키지 관리 및 설치 도구.",
            "프론트엔드 개발 라이브러리/프레임워크."
    };

    public static String[] stepsFrontendDetailedDescrption = {
            "웹 페이지의 구조를 정의하는 마크업 언어입니다.",
            "웹 페이지의 스타일과 레이아웃을 설계하는 언어입니다.",
            "웹 페이지에 동적인 기능을 추가하는 프로그래밍 언어입니다.",
            "소스 코드 관리와 협업을 위한 버전 관리 시스템입니다.",
            "JavaScript 패키지 관리 도구로, 다양한 라이브러리와 패키지를 관리합니다.",
            "프론트엔드 개발을 위한 인기 있는 JavaScript 라이브러리 및 프레임워크입니다."
    };
 public static List<String>[] stepsfrontendRelatedStacks = new List[]{
         Arrays.asList("HTML", "CSS"),
         Arrays.asList("CSS", "JavaScript"),
         Arrays.asList("JavaScript", "Git / GitHub"),
         Arrays.asList("Git / GitHub", "Npm"),
         Arrays.asList("Npm", "React, Next.js, Vue.js"),
         Arrays.asList("React, Next.js, Vue.js")
 };
}
