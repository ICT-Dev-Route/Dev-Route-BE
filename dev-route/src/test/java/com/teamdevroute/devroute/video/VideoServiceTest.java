package com.teamdevroute.devroute.video;

import com.teamdevroute.devroute.video.dto.youtube.YouTubeApiResponse;
import com.teamdevroute.devroute.video.dto.youtube.YouTubeApiResponse.Item;
import com.teamdevroute.devroute.video.dto.youtube.YouTubeApiResponse.Item.Id;
import com.teamdevroute.devroute.video.dto.youtube.YouTubeApiResponse.Item.Snippet;

import com.teamdevroute.devroute.video.dto.youtube.YouTubeApiResponse.Item.Thumbnails;
import com.teamdevroute.devroute.video.dto.youtube.YouTubeApiResponse.Item.Thumbnails.Thumbnail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VideoServiceTest {

    @InjectMocks
    private VideoService videoService;

    @Mock
    private VideoRepository videoRepository;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {

    }

    @DisplayName("가짜 객체를 생성하여,Fetch하고 Save하는 과정이 9번 진행되나 테스트한다.")
    @Test
    public void testFetchAndSaveYoutubeVideos() {
        // Given
        YouTubeApiResponse response = getMockYouTubeApiResponse();
        //When
        when(restTemplate.getForObject(anyString(), eq(YouTubeApiResponse.class))).thenReturn(response);
        videoService.fetchAndSaveVideo();
        // Then
        //Tehcnology stack name이 총 9개임으로 9번 호출 되는 것이 맞음.
        verify(videoRepository, times(9)).save(any());
    }

    @DisplayName("가짜 객체를 생성하는데 videoid가 null인경우,Save가 되지 않는지 테스트한다.")
    @Test
    public void testFetchAndSaveYoutubeVideosWithVideoIdIsNull() {
        // Given
        YouTubeApiResponse response = getMockYouTubeApiResponseWithVideoIdIsNull();
        //When
        when(restTemplate.getForObject(anyString(), eq(YouTubeApiResponse.class))).thenReturn(response);
        videoService.fetchAndSaveVideo();
        // Then
        //저장이 되면 안됨.
        verify(videoRepository, times(0)).save(any());
    }



    //가짜 YoububeApiResponse를 가져온다.
    private YouTubeApiResponse getMockYouTubeApiResponse() {
        ReflectionTestUtils.setField(videoService, "youtubeApiKey", "fakeApiKey");
        Item item = new Item();
        setYoutubeItem(item);
        YouTubeApiResponse response = new YouTubeApiResponse();
        response.setItems(Collections.singletonList(item));
        return response;
    }
    private YouTubeApiResponse getMockYouTubeApiResponseWithVideoIdIsNull() {
        ReflectionTestUtils.setField(videoService, "youtubeApiKey", "fakeApiKey");
        Item item = new Item();
        setYoutubeItemWithVideoIdIsNull(item);
        YouTubeApiResponse response = new YouTubeApiResponse();
        response.setItems(Collections.singletonList(item));
        return response;
    }

    //가짜 YoububeItem을 생성한다.
    private Item setYoutubeItem(Item item) {
        Id id = new Id();
        id.setVideoId("video123");
        item.setId(id);
        Snippet snippet = new Snippet();
        snippet.setTitle("Sample Video");
        Thumbnails thumbnails = new Thumbnails();
        Thumbnail thumbnail = new Thumbnail();
        thumbnail.setUrl("Sample Thumnail");
        thumbnails.setDefault(thumbnail);
        snippet.setThumbnails(thumbnails);
        item.setSnippet(snippet);
        return item;
    }

    //videoId가 없는 가짜 YoububeItem을 생성한다.
    private Item setYoutubeItemWithVideoIdIsNull(Item item) {

        Snippet snippet = new Snippet();
        snippet.setTitle("Sample Video");
        Thumbnails thumbnails = new Thumbnails();
        Thumbnail thumbnail = new Thumbnail();
        thumbnail.setUrl("Sample Thumnail");
        thumbnails.setDefault(thumbnail);
        snippet.setThumbnails(thumbnails);
        item.setSnippet(snippet);
        return item;
    }
}