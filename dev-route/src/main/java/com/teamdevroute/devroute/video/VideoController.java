package com.teamdevroute.devroute.video;

import java.io.IOException;
import java.util.List;

import com.teamdevroute.devroute.video.Repository.TechnologyStackRepository;
import com.teamdevroute.devroute.video.domain.TechnologyStack;
import com.teamdevroute.devroute.video.dto.LectureResponseDTO;
import com.teamdevroute.devroute.video.enums.TechnologyStackName;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VideoController {
    private final VideoService videoService;
    private final TechnologyStackService technologyStackService;

    //굳이 service단 까지 안가고 controller에서 처리함.
    private final TechnologyStackRepository technologyStackRepository;

    public VideoController(VideoService videoService, TechnologyStackService technologyStackService, TechnologyStackRepository technologyStackRepository) {
        this.videoService = videoService;
        this.technologyStackService = technologyStackService;
        this.technologyStackRepository = technologyStackRepository;
    }
    @ResponseBody
    @GetMapping("/fetch-and-save")
    public String  fetchAndSaveYoutubeVideo() throws IOException {
        videoService.fetchAndSaveVideo();
        if(technologyStackRepository.count()==0)
            technologyStackService.initializeTechnologyStack();
        return "Successfull FetchAndSave Videos!!";
    }
    @ResponseBody
    @GetMapping("/lecture")
    public List<LectureResponseDTO> getRecommendLectureList(@RequestParam("platform_name") String platform_name
    ,@RequestParam("tech_name")String tech_name){
        addCountTechnologyStackByStackName(tech_name);
        return videoService.findLectureListByPlatformNameAndTechStack(platform_name, tech_name);
    }

    private void addCountTechnologyStackByStackName(String tech_name) {
        TechnologyStack technologyStack = technologyStackRepository.findByName(tech_name)
                .orElseThrow(()-> new RuntimeException("Technology stack not found: " + tech_name));
        technologyStack.setAddedCount();
        technologyStackRepository.save(technologyStack);

    }




}
