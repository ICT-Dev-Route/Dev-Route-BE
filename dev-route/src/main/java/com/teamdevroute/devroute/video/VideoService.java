package com.teamdevroute.devroute.video;

import com.teamdevroute.devroute.video.Repository.TechnologyStackRepository;
import com.teamdevroute.devroute.video.Repository.VideoRepository;
import com.teamdevroute.devroute.video.domain.TechnologyStack;
import com.teamdevroute.devroute.video.domain.Videos;
import com.teamdevroute.devroute.video.dto.LectureResponseDTO;
import com.teamdevroute.devroute.video.dto.infrean.InfreanVideoDTO;
import com.teamdevroute.devroute.video.dto.udemy.UdemyApiResponse;
import com.teamdevroute.devroute.video.dto.udemy.UdemyVideoDTO;
import com.teamdevroute.devroute.video.dto.youtube.YouTubeApiResponse;
import com.teamdevroute.devroute.video.dto.youtube.YoutubeVideoDTO;
import com.teamdevroute.devroute.video.enums.TechnologyStackName;
import com.teamdevroute.devroute.video.fetcher.InfreanVideoFetcher;
import com.teamdevroute.devroute.video.fetcher.UdemyVideoFetcher;
import com.teamdevroute.devroute.video.fetcher.YoutubeVideoFetcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.teamdevroute.devroute.video.constans.ApiConstans.UDEMY_API_URL_FRONT_VIDEOID;
import static com.teamdevroute.devroute.video.constans.ApiConstans.YOUTUBE_API_URL_FRONT_VIDEOID;
import static com.teamdevroute.devroute.video.enums.PlatformName.*;

@Service
@Slf4j
@Transactional
public class VideoService {

    private final VideoRepository videoRepository;
    private final YoutubeVideoFetcher youtubeVideoFetcher;
    private final UdemyVideoFetcher udemyVideoFetcher;
    private final InfreanVideoFetcher infreanVideoFetcher;
    private final TechnologyStackRepository technologyStackRepository;


    public VideoService(VideoRepository videoRepository, YoutubeVideoFetcher youtubeVideoFetcher,
                        UdemyVideoFetcher udemyVideoFetcher, InfreanVideoFetcher infreanVideoFetcher, TechnologyStackRepository technologyStackRepository) {
        this.videoRepository = videoRepository;
        this.youtubeVideoFetcher = youtubeVideoFetcher;
        this.udemyVideoFetcher = udemyVideoFetcher;
        this.infreanVideoFetcher = infreanVideoFetcher;
        this.technologyStackRepository = technologyStackRepository;
    }

    //매주 토요일에 실행
    @Scheduled(cron = "* * * * * 6", zone = "Asia/Seoul")
    public void fetchAndSaveVideo() throws IOException {
        fetchAndSaveYoutubeVideos();
        fetchAndSaveUdemyVideos();
        fetchAndSaveInfreanVideos();

    }

    public void fetchAndSaveYoutubeVideos() {
        for (TechnologyStackName value : TechnologyStackName.values()) {
            YouTubeApiResponse response = youtubeVideoFetcher.fetchYoutubeVideos(String.valueOf(value));
            if (response != null) {
                saveYoutubeVideo(response, value);
            }
        }
    }

    public void fetchAndSaveUdemyVideos() {
        for (TechnologyStackName value : TechnologyStackName.values()) {
            UdemyApiResponse response = udemyVideoFetcher.fetchUdemyVideos(String.valueOf(value));
            if (response != null) {
                saveUdemyVideo(response, value);
            }
        }
    }

    public void fetchAndSaveInfreanVideos() throws IOException {
        for (TechnologyStackName value : TechnologyStackName.values()) {
            ArrayList<InfreanVideoDTO> infreanVideoDTOS = infreanVideoFetcher.fetchInfreanVideos(String.valueOf(value));
            saveInfreanVideo(infreanVideoDTOS, value);
        }
    }

    private void saveYoutubeVideo(YouTubeApiResponse response, TechnologyStackName techStack) {
        Long rank = 0L;
        for (YouTubeApiResponse.Item item : response.getItems()) {
            if (item.getId() == null) continue;
            String videoId = item.getId().getVideoId();
            String videoUrl = YOUTUBE_API_URL_FRONT_VIDEOID + videoId;
            String title = item.getSnippet().getTitle();
            String thumbnailUrl = item.getSnippet().getThumbnails().getDefault().getUrl();
            log.info("YoutubeFetching result: " + "thumnail: " + thumbnailUrl + " url: " + videoUrl +
                    " title: " + title);
            if (videoId != null && title != null && thumbnailUrl != null) {
                videoRepository.save(new YoutubeVideoDTO(videoUrl, title, thumbnailUrl).toEntity(
                        String.valueOf(Youtube), String.valueOf(techStack), 0L, ++rank));
            }
        }
    }

    private void saveUdemyVideo(UdemyApiResponse response, TechnologyStackName techStack) {
        Long rank = 0L;
        int currentCourseNumber = 0;
        for (UdemyApiResponse.Course course : response.getResults()) {
            String videoUrl = UDEMY_API_URL_FRONT_VIDEOID + course.getUrl();
            String title = course.getTitle();
            String thumbnailUrl = course.getImage_125_H();
            Long price = course.getPrice().replaceAll("[^\\d]", "") == "" ? 0L
                    : Long.valueOf(course.getPrice().replaceAll("[^\\d]", ""));
            log.info("UdemyFetching result: " + "thumnail: " + thumbnailUrl + " url: " + videoUrl +
                    " title: " + title + " price: " + price);
            if (course.getUrl() != null && title != null && thumbnailUrl != null && price != null) {
                videoRepository.save(new UdemyVideoDTO(videoUrl, title, thumbnailUrl, price).toEntity(
                        String.valueOf(Udemy), String.valueOf(techStack), 0L, ++rank));
                currentCourseNumber += 1;
            }
            if (currentCourseNumber >= 10) {
                break;
            }
        }
    }

    private void saveInfreanVideo(ArrayList<InfreanVideoDTO> infreanVideoDTOS, TechnologyStackName techStack) {
        Long rank = 0L;
        for (InfreanVideoDTO infreanVideoDTO : infreanVideoDTOS) {
            videoRepository.save(infreanVideoDTO.toEntity(String.valueOf(Infrean),
                    String.valueOf(techStack), 0L, ++rank));
        }
    }

    public List<LectureResponseDTO> findLectureListByPlatformNameAndTechStack(
            String platformName, String techStack) {
        List<Videos> videos = videoRepository.findByPlatformNameAndTeckStack(platformName, techStack);
        return videos.stream()
                .map(video -> new LectureResponseDTO(video.getUrl(), video.getTitle(), video.getThumnail_url(), video.getPrice(), video.getPlatformName()))
                .collect(Collectors.toList());
    }

    public Videos findById(Long id) {
        return videoRepository.findById(id)
                .orElseThrow(VideoNotFounException::new);
    }
}

