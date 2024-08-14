package com.teamdevroute.devroute.roadmap.description;

import java.util.Arrays;
import java.util.List;

public final class Backend {
   public static String[] stepsBackendNames = {
            "Java",
            "Python",
            "JavaScript, Node.js",
            "HTTP",
            "REST API",
            "RDB - MySQL",
            "NoSQL - MongoDB",
            "Spring Boot(Java framework)",
            "Django(Python framework)",
            "Express.js(Node.js framework)"
    };
    public static String[] stepBackendBriefNames = {
            "객체 지향 프로그래밍 언어.",
            "다재다능하고 사용하기 쉬운 언어.",
            "웹 개발을 위한 스크립트 언어와 런타임.",
            "웹에서 데이터 전송 프로토콜.",
            "웹 서비스 설계 원칙.",
            "관계형 데이터베이스 시스템.",
            "문서 지향 데이터베이스.",
            "Java용 경량 프레임워크.",
            "Python 웹 애플리케이션 프레임워크.",
            "Node.js 기반 웹 프레임워크."
    };
    public static String[] stepsBackendDetailedDescription = {
            "한국의 전자정부 시스템과 주요 기업에서 널리 사용되는 언어입니다.",
            "배우기 쉽고 코드가 간결하여 입문자들에게 적합한 언어입니다.",
            "웹 브라우저와 서버 개발 모두에 사용할 수 있는 유용한 언어입니다.",
            "웹사이트와 서버 간 데이터 전송을 위한 기본 프로토콜입니다.",
            "웹 서비스 간 데이터를 주고받는 표준화된 방법입니다.",
            "데이터를 테이블 형태로 정리하여 저장하는 관계형 데이터베이스입니다.",
            "다양한 형식의 데이터를 유연하게 저장할 수 있는 NoSQL 데이터베이스입니다.",
            "Java로 웹 애플리케이션을 쉽게 개발할 수 있게 돕는 프레임워크입니다.",
            "Python으로 빠르게 웹 애플리케이션을 구축할 수 있는 프레임워크입니다.",
            "Node.js 환경에서 웹 애플리케이션을 구축하기 위한 경량 프레임워크입니다."
    };
    public static List<String>[] stepsBackendRelatedStacks=new List[]{
            Arrays.asList("Java", "Python"),
            Arrays.asList("Python", "JavaScript, Node.js"),
            Arrays.asList("JavaScript, Node.js", "HTTP"),
            Arrays.asList("HTTP", "REST API"),
            Arrays.asList("REST API", "RDB - MySQL"),
            Arrays.asList("RDB - MySQL", "NoSQL - MongoDB"),
            Arrays.asList("NoSQL - MongoDB", "Spring Boot(Java framework)"),
            Arrays.asList("Spring Boot(Java framework)", "Django(Python framework)"),
            Arrays.asList("Django(Python framework)", "Express.js(Node.js framework)"),
            Arrays.asList("Express.js(Node.js framework)")
    };

}
