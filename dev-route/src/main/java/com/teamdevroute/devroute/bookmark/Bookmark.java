package com.teamdevroute.devroute.bookmark;

import com.teamdevroute.devroute.bookmark.domain.BookmarkCompany;
import com.teamdevroute.devroute.bookmark.domain.BookmarkRoadmap;
import com.teamdevroute.devroute.bookmark.domain.BookmarkVideo;
import com.teamdevroute.devroute.bookmark.exception.DuplicateBookmarkContentException;
import com.teamdevroute.devroute.bookmark.json.CompanyListConverter;
import com.teamdevroute.devroute.bookmark.json.RoadmapListConverter;
import com.teamdevroute.devroute.bookmark.json.VideoListConverter;
import com.teamdevroute.devroute.company.domain.Company;
import com.teamdevroute.devroute.global.BaseTimeEntity;
import com.teamdevroute.devroute.roadmap.domain.RoadmapStep;
import com.teamdevroute.devroute.video.domain.Videos;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "bookmark")
public class Bookmark extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(columnDefinition = "TEXT")
    @Convert(converter = CompanyListConverter.class)
    private List<BookmarkCompany> companies;

    @Column(columnDefinition = "TEXT")
    @Convert(converter = VideoListConverter.class)
    private List<BookmarkVideo> videos;

    @Column(columnDefinition = "TEXT")
    @Convert(converter = RoadmapListConverter.class)
    private List<BookmarkRoadmap> roadmaps;

    @Builder
    public Bookmark(Long id, List<BookmarkCompany> companies, List<BookmarkVideo> videos, List<BookmarkRoadmap> roadmaps) {
        this.id = id;
        this.companies = companies;
        this.videos = videos;
        this.roadmaps = roadmaps;
    }

    public void addCompany(Company company) {
        BookmarkCompany bookmarkCompany = BookmarkCompany.from(company);
        boolean exists = companies.stream()
                .anyMatch(existingCompany -> existingCompany.getId().equals(bookmarkCompany.getId()));
        if (!exists) {
            companies.add(bookmarkCompany);
        } else {
            throw new DuplicateBookmarkContentException.DuplicateCompanyException();
        }
    }

    public void addVideo(Videos video) {
        BookmarkVideo bookmarkVideo = BookmarkVideo.from(video);

        boolean exists = videos.stream()
                .anyMatch(existingVideo -> existingVideo.getId().equals(bookmarkVideo.getId()));

        if (!exists) {
            videos.add(bookmarkVideo);
        } else {
            throw new DuplicateBookmarkContentException.DuplicateVideoException();
        }
    }

    public void addRoadmap(RoadmapStep roadmapStep) {
        BookmarkRoadmap bookmarkRoadmap = BookmarkRoadmap.from(roadmapStep);

        boolean exists = roadmaps.stream()
                .anyMatch(existingRoadmap -> existingRoadmap.getId().equals(bookmarkRoadmap.getId()));

        if (!exists) {
            roadmaps.add(bookmarkRoadmap);
        } else {
            throw new DuplicateBookmarkContentException.DuplicateRoadmapException();
        }
    }

    public void removeCompany(Long companyId) {
        companies.removeIf(bookmarkCompany -> bookmarkCompany.getId().equals(companyId));
    }

    public void removeVideo(Long videoId) {
        videos.removeIf(bookmarkVideo -> bookmarkVideo.getId().equals(videoId));
    }

    public void removeRoadmap(Long roadmapId) {
        roadmaps.removeIf(bookmarkRoadmap -> bookmarkRoadmap.getId().equals(roadmapId));
    }
}
