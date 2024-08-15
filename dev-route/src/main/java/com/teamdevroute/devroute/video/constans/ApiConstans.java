package com.teamdevroute.devroute.video.constans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public final class ApiConstans {

    public static String YOUTUBE_API_URL_SEARCH="https://www.googleapis.com/youtube/v3/search?part=snippet&type=video";

//    @Value("${udemy.api.url.search}")
    public static String UDEMY_API_URL_SEARCH="https://www.udemy.com/api-2.0/courses/?search=";
    public static String YOUTUBE_API_URL_FRONT_VIDEOID="https://www.youtube.com/watch?v=";
    public static String UDEMY_API_URL_FRONT_VIDEOID="https://www.udemy.com";

    public static String INFREAN_CRAWRLING_URL_SEARCH="https://www.inflearn.com/courses/it-programming/web-dev?skill=";
    public static String INFREAN_CRAWRLING_URL_SEARCH_MOBILE = "https://www.inflearn.com/courses/it-programming/mobile-app?skill=";
    public static String INFREAN_CRAWRLING_URL_SEARCH_MOBILE_AI = "https://www.inflearn.com/courses/";
    public static String QUERY_FRONT_VALUE="&q=";
    public static String QUERY_FRONT_KEY = "&key=";
    public static String QUERY_MAX_RESULT = "&maxResults=12";
    public static String QUERY_UDEMY_SET_FEILD = "&fields[course]=title,url,price,image_125_H";

    private  ApiConstans(){

    }
}
