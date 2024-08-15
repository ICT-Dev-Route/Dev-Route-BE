package com.teamdevroute.devroute.video.fetcher;

import com.teamdevroute.devroute.video.dto.youtube.YouTubeApiResponse;
import com.teamdevroute.devroute.video.enums.TechnologyStackName;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static com.teamdevroute.devroute.video.constans.ApiConstans.*;

@Component
public class YoutubeVideoFetcher {
    private final RestTemplate restTemplate;
    @Value("${youtube.api.key}")
    private String youtubeApiKey;

    public YoutubeVideoFetcher(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public YouTubeApiResponse fetchYoutubeVideos(String value) {
//        System.out.println(getYoutubeApiUrl(value));
        return restTemplate.getForObject(getYoutubeApiUrl(value), YouTubeApiResponse.class);
    }

    private String getYoutubeApiUrl(String value) {
        return YOUTUBE_API_URL_SEARCH + QUERY_FRONT_VALUE + value+"개발" + QUERY_FRONT_KEY + youtubeApiKey + QUERY_MAX_RESULT;
    }
}