package com.teamdevroute.devroute.user.dto;

import com.teamdevroute.devroute.bookmark.Bookmark;
import com.teamdevroute.devroute.bookmark.domain.BookmarkCompany;
import com.teamdevroute.devroute.bookmark.domain.BookmarkRoadmap;
import com.teamdevroute.devroute.bookmark.domain.BookmarkVideo;
import com.teamdevroute.devroute.user.domain.User;
import lombok.Builder;

import java.util.List;

public record UserMyPageResponse(
        List<BookmarkCompany> companies,
        List<BookmarkVideo> videos,
        List<BookmarkRoadmap> roadmaps
) {

    @Builder
    public UserMyPageResponse(List<BookmarkCompany> companies, List<BookmarkVideo> videos, List<BookmarkRoadmap> roadmaps) {
        this.companies = companies;
        this.videos = videos;
        this.roadmaps = roadmaps;
    }

    public static UserMyPageResponse of(User user){
        Bookmark bookmark = user.getBookmark();
        return UserMyPageResponse.builder()
                .companies(bookmark.getCompanies())
                .videos(bookmark.getVideos())
                .roadmaps(bookmark.getRoadmaps())
                .build();
    }
}
