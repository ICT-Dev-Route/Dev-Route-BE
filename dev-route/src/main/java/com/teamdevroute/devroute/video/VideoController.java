package com.teamdevroute.devroute.video;

import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VideoController {
    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }
    @ResponseBody
    @GetMapping("/fetch-and-save")
    public String  fetchAndSaveYoutubeVideo() throws IOException {
        videoService.fetchAndSaveVideo();
        return "!!";
    }

}
