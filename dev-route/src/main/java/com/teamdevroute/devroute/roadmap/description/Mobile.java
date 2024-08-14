package com.teamdevroute.devroute.roadmap.description;

import java.util.Arrays;
import java.util.List;

public final class Mobile {


    public static String[] stepsAndroidNames = {
            "Java&Kotlin",
            "Android Studio",
            "Drag and Drop Design",
            "Code Completion",
            "Virtual Device Testing",
            "Android Basics",
            "XML for UI Design",
            "Android Components",
            "App Lifecycle"
    };
    public static String[] stepsAndroidBriefNames = {
            "안드로이드 개발 언어.",
            "안드로이드 앱 개발 도구.",
            "시각적 인터페이스 디자인.",
            "자동 코드 완성 기능.",
            "앱 테스트용 가상 기기.",
            "안드로이드 기본 개념.",
            "UI 디자인을 위한 XML.",
            "안드로이드 주요 구성 요소.",
            "앱의 생명주기 관리."
    };


    public static String[] stepsAndroidDetailedDescription = {
            "안드로이드 어플은 Kotlin과 Java라는 언어를 통해서 만들 수 있어요.",
            "안드로이드 앱 개발에 필요한 모든 도구를 제공하는 통합 개발 환경입니다.",
            "Android Studio를 이용하면 드래그로 쉽게 어플을 디자인할 수 있어요.",
            "자동완성 기능으로 코딩의 편리함을 제공합니다.",
            "실제 기기 없이 가상 기기로 앱을 테스트할 수 있어요.",
            "레이아웃과 UI 요소를 배치하는 방법 등 UI 디자인을 학습합니다.",
            "XML을 이용해 더욱 디테일하게 UI를 디자인할 수 있어요.",
            "안드로이드 앱의 4대 구성요소는 Activity, Service, Content Provider, Broadcast Receiver입니다.",
            "앱의 시작, 동작, 종료 등 생명주기 관리 방법을 이해합니다."
    };
    public static List<String>[] stepsAndroidRelatedStacks = new List[]{
            Arrays.asList("Java&Kotlin", "Android Studio"),
            Arrays.asList("Android Studio", "Drag and Drop Design"),
            Arrays.asList("Drag and Drop Design", "Code Completion"),
            Arrays.asList("Code Completion", "Virtual Device Testing"),
            Arrays.asList("Virtual Device Testing", "Android Basics"),
            Arrays.asList("Android Basics", "XML for UI Design"),
            Arrays.asList("XML for UI Design", "Android Components"),
            Arrays.asList("Android Components", "App Lifecycle"),
            Arrays.asList("App Lifecycle")
    };
    public static String[] stepsIosNames = {
                "Swift",
                "Xcode",
                "Storyboards in Xcode",
                "iOS Basics",
                "SwiftUI",
                "App Lifecycle"
    };
    public static String[] stepsIosBriefNames={
            "iOS 앱 개발 언어.",
            "iOS 및 macOS 개발 도구.",
            "Xcode에서 시각적 UI 디자인.",
            "iOS 기본 개념.",
            "Apple의 UI 프레임워크.",
            "앱의 생명주기 관리."
    };

    public static String[] stepsIosDetailedDescription = {
            "iOS 어플은 Swift라는 언어를 이용해서 만들 수 있어요.",
            "Swift는 코드가 간결하고 빠르게 실행되어 iOS 앱 개발을 더 쉽고 효율적으로 만들어 줘요.",
            "Xcode는 iOS 앱 개발에 필요한 모든 도구를 제공하는 통합 개발 환경입니다.",
            "스토리보드를 사용해 쉽게 어플을 디자인할 수 있어요.",
            "실제 iOS 기기 없이도 앱을 테스트할 수 있어요.",
            "iOS 어플의 구성 요소는 Cocoa Touch, Media, Core Services, Core OS입니다.",
            "SwiftUI를 이용해 UI 디자인을 배우며, 레이아웃과 UI 요소를 효율적으로 배치하는 방법을 익힐 수 있어요.",
            "앱의 시작, 동작, 종료 등 생명주기 관리 방법을 이해합니다."
    };

    public static List<String>[] stepsIosRelatedStacks = new List[]{
            Arrays.asList("Swift", "Xcode"),
            Arrays.asList("Xcode", "Storyboards in Xcode"),
            Arrays.asList("Storyboards in Xcode", "iOS Basics"),
            Arrays.asList("iOS Basics", "SwiftUI"),
            Arrays.asList("SwiftUI", "App Lifecycle"),
            Arrays.asList("App Lifecycle")
    };
}
