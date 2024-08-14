package com.teamdevroute.devroute.roadmap.description;

import java.util.Arrays;
import java.util.List;

public final class Ai {
    public static String[] stepsAiNames = {
            "선형 대수",
            "미적분학",
            "파이썬 기본 문법",
            "파이썬 라이브러리",
            "파이썬 판다스",
            "넘파이",
            "SQL",
            "DL ML"
    };
    public static  String[] stepsAiBriefNames ={
            "벡터와 행렬 다루는 수학",
            "함수 변화율과 적분 연구",
            "파이썬의 기초 문법 학습",
            "파이썬 기능 제공 코드 모음",
            "데이터 분석용 파이썬 라이브러리",
            "배열 연산을 위한 파이썬 라이브러리",
            "데이터베이스 관리 언어",
            "딥러닝과 머신러닝"
    };

    public static  String[] stepsAiDetailedDescription = {
            "행렬과 벡터를 통해 데이터를 해석하고, 인공지능 모델을 이해할 수 있는 학문입니다.",
            "기울기와 변화율을 이용해 머신러닝 및 인공지능 모델의 동작을 이해할 수 있습니다.",
            "파이썬을 사용해 데이터를 분석하고, 인공지능 모델을 실행할 수 있습니다.",
            "파이썬 라이브러리를 통해 복잡한 작업을 간편하게 수행할 수 있습니다.",
            "판다스 라이브러리를 사용하여 데이터 처리와 분석을 할 수 있습니다.",
            "넘파이를 통해 대규모의 다차원 배열 및 행렬 데이터를 처리할 수 있습니다.",
            "SQL은 데이터베이스를 조작하고 데이터를 추출 및 가공하는 데 사용됩니다.",
            "딥러닝(DL) 및 머신러닝(ML)에 대해 학습합니다."
    };
    public static List<String>[] stepsAiRelatedStacks=new List[]{
            Arrays.asList("미적분학", "파이썬 기본 문법"),
            Arrays.asList("파이썬 기본 문법", "파이썬 라이브러리"),
            Arrays.asList("파이썬 라이브러리", "파이썬 & 판다스"),
            Arrays.asList("파이썬 & 판다스", "넘파이"),
            Arrays.asList("넘파이", "SQL"),
            Arrays.asList("DL & ML", "SQL"),
            Arrays.asList("DL & ML"),
            Arrays.asList("DL & ML")
    };
}
