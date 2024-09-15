package com.teamdevroute.devroute.dataloader;


import com.teamdevroute.devroute.roadmap.RoadmapService;
import com.teamdevroute.devroute.video.Repository.TechnologyStackRepository;
import com.teamdevroute.devroute.video.Repository.VideoRepository;
import com.teamdevroute.devroute.video.domain.TechnologyStack;
import com.teamdevroute.devroute.video.domain.Videos;
import com.teamdevroute.devroute.video.enums.PlatformName;
import com.teamdevroute.devroute.video.enums.TechnologyStackName;
import com.teamdevroute.devroute.video.service.VideoService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.teamdevroute.devroute.video.enums.PlatformName.Inflearn;

@Getter
@Component
public class VideoDataLoader {
    @Autowired
    private  VideoRepository videoRepository;
    @Autowired
    private TechnologyStackRepository technologyStackRepository;
    @Autowired
    private RoadmapService roadmapService;

    private final List<Videos> videosList = new ArrayList<>();

    public void loadVideoData(){
        for (TechnologyStackName value : TechnologyStackName.values()) {
            createTech(String.valueOf(value));
        }

        Long count=0L;
        for (PlatformName platformname : PlatformName.values()) {
            for (TechnologyStackName value : TechnologyStackName.values()) {
                switch (platformname) {
                    case Inflearn:
                        if(count%3==0)
                        createVideo(
                                0L, platformname+"의 유명한 "+value+" 비디오",
                                "https://www.inflearn.com/course/%EA%B9%A1%EC%83%98%EC%9D%98-%EC%8C%A9%EC%B4%88%EB%B3%B4-%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-part5?attributionToken=ChM1ODg3MTk1NTc2ODEzOTQ3ODQwEA0aI3JlY29tbWVuZGVfcmVjb21tZW5kZV8xNzAyNTI2NDM0MDY2IhdyZWNvbW1lbmRlZC1mb3IteW91LWN2cigAOij38dES-vHRErTx0RKy8dESqfLREq3y0RKP8tESjPLREr7y0RK78tES"
                                ,
                                "https://cdn.inflearn.com/public/courses/334276/cover/87c0dc98-efe3-47ab-82ce-225a45c2e7a7/334276.png?w=420 ",
                                count, String.valueOf(value), String.valueOf(platformname)
                        );
                        if(count%3==1)
                            createVideo(
                                    0L, platformname+"의 유명한 "+value+" 비디오",
                                    "https://www.inflearn.com/course/vue-3-%EC%8B%9C%EC%9E%91%ED%95%98%EA%B8%B0?attributionToken=ChQxNDU5NjE4NDYyMDA0NTA0Mzc3NBANGiNyZWNvbW1lbmRlX3JlY29tbWVuZGVfMTcwMjUyNjQzNDA2NiIXcmVjb21tZW5kZWQtZm9yLXlvdS1jdnIoADoo-vHRErTx0RKy8dESu_LREozy0RL38dESj_LREqny0RKt8tESvvLREg"
                                    ,
                                    "https://cdn.inflearn.com/public/courses/332010/cover/fffd02eb-685e-44ab-aa0d-6788349338c5/332010-eng.png?w=420",
                                    count, String.valueOf(value), String.valueOf(platformname)
                            );
                        if(count%3==2)
                            createVideo(
                                    0L, platformname+"의 유명한 "+value+" 비디오",
                                    "https://www.inflearn.com/course/amazing-javascript-%EC%9E%85%EB%AC%B8?attributionToken=ChQxNDU5NjE4NDYyMDA0NTA0Mzc3NBANGiNyZWNvbW1lbmRlX3JlY29tbWVuZGVfMTcwMjUyNjQzNDA2NiIXcmVjb21tZW5kZWQtZm9yLXlvdS1jdnIoADoo-vHRErTx0RKy8dESu_LREozy0RL38dESj_LREqny0RKt8tESvvLREg",
                                  "https://cdn.inflearn.com/public/courses/334104/cover/8eafc0d0-1be5-426a-be30-38bb90ccec21/334104.png?w=420"        ,
                                    count, String.valueOf(value), String.valueOf(platformname)
                          );

                    case Youtube:
                        if(count%3==0)
                        createVideo(
                                0L, platformname+"의 유명한 "+value+" 비디오","https://www.youtube.com/watch?v=lYmLUiBAqqM"
                                ,"https://i.ytimg.com/vi/lYmLUiBAqqM/default.jpg",
                                count, String.valueOf(value), String.valueOf(platformname)
                        );
                        if(count%3==1)
                            createVideo(
                                    0L, platformname+"의 유명한 "+value+" 비디오","https://www.youtube.com/watch?v=lYmLUiBAqqM"
                                    ,"https://i.ytimg.com/vi/lYmLUiBAqqM/default.jpg",
                                    count, String.valueOf(value), String.valueOf(platformname)
                            );
                        if(count%3==2)
                            createVideo(
                                    0L, platformname+"의 유명한 "+value+" 비디오","https://www.youtube.com/watch?v=lYmLUiBAqqM"
                                    ,"https://i.ytimg.com/vi/lYmLUiBAqqM/default.jpg",
                                    count, String.valueOf(value), String.valueOf(platformname)
                            );
                    case Udemy:
                        if(count%3==0)
                        createVideo(
                                0L, platformname+"의 유명한 "+value+" 비디오","https://www.udemy.com/course/javascript-zero-to-expert-the-complete-modern-guide-build-real-apps/"
                                ,"https://img-c.udemycdn.com/course/125_H/3429704_e81d_4.jpg",
                                count, String.valueOf(value), String.valueOf(platformname)
                        );
                        if(count%3==1)
                            createVideo(
                                    0L, platformname+"의 유명한 "+value+" 비디오","https://www.udemy.com/course/complete-angular-14-course-learn-frontend-development/"
                                    ,"https://img-c.udemycdn.com/course/125_H/4844324_2d2b_4.jpg",
                                    count, String.valueOf(value), String.valueOf(platformname)
                            );
                        if(count%3==2)
                            createVideo(
                                    0L, platformname+"의 유명한 "+value+" 비디오","https://www.udemy.com/course/html-css-for-beginer/ "
                                    ,"https://img-c.udemycdn.com/course/125_H/3426600_1577.jpg",
                                    count, String.valueOf(value), String.valueOf(platformname)
                            );
                        count += 1;
                }
            }

        }

    }


    private void createVideo(
            Long price,
            String title,
            String url,
            String thumnail_url,
            Long count,
            String techStack,
            String platform_name

    ) {
        Videos videos = Videos.builder()
                .price(price)
                .title(title)
                .url(url)
                .count(count)
                .thumnail_url(thumnail_url)
                .teck_stack(techStack)
                .platform_name(platform_name)
                .build();
        videoRepository.save(videos);
        videosList.add(videos);
    }

    private void createTech(
            String name
    ) {
        TechnologyStack technologyStack = TechnologyStack.builder()
                .name(name)
                .count(0L)
                .build();
        technologyStackRepository.save(technologyStack);
    }

}

