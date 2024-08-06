package com.teamdevroute.devroute.bookmark;

import com.teamdevroute.devroute.company.service.CompanyService;
import com.teamdevroute.devroute.recruitment.service.RecruitmentService;
import com.teamdevroute.devroute.roadmap.RoadmapService;
import com.teamdevroute.devroute.user.UserService;
import com.teamdevroute.devroute.user.domain.User;
import com.teamdevroute.devroute.video.TechnologyStackService;
import com.teamdevroute.devroute.video.VideoService;
import com.teamdevroute.devroute.video.domain.TechnologyStack;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.ArrayList;

@Slf4j
@AllArgsConstructor
@Service
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;


    private final UserService userService;
    private final CompanyService companyService;
    private final RoadmapService roadmapService;
    private final VideoService videoService;
    private final TechnologyStackService technologyStackService;

    public void updateBookmark(Long userId, BookmarkUpdateRequest request) {
        User user = userService.findByUserId(userId);
        log.info("updateBookmark() : " + user.getEmail());

        if(user.getBookmark() == null) {
            user.initBookmark(
                    Bookmark.builder()
                        .techStacks(new ArrayList<>())
                        .videos(new ArrayList<>())
                        .companies(new ArrayList<>())
                        .roadmaps(new ArrayList<>())
                        .build()
            );
        }

        Bookmark bookmark = user.getBookmark();
        String type = request.getType();

        switch (type) {
            case "company":
                bookmark.addCompany(companyService.findById(request.getId()));
                break;
            case "video":
                bookmark.addVideo(videoService.findById(request.getId()));
                break;
            case "tech":
                bookmark.addTechStack(technologyStackService.findById(request.getId()));
                break;
            case "roadmap":
                bookmark.addRoadmap(roadmapService.findById(request.getId()));
                break;
        }

        bookmarkRepository.save(bookmark);
    }
}
