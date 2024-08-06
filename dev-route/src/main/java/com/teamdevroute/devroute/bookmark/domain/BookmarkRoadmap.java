package com.teamdevroute.devroute.bookmark.domain;

import com.teamdevroute.devroute.roadmap.domain.RoadmapStep;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookmarkRoadmap {
    private Long id;
    private String developmentField;
    private String name;
    private String brief_info;

    public static BookmarkRoadmap from(RoadmapStep roadmapStep) {
        return BookmarkRoadmap.builder()
                .id(roadmapStep.getId())
                .developmentField(roadmapStep.getDevelopmentField())
                .name(roadmapStep.getName())
                .brief_info(roadmapStep.getBrief_info())
                .build();
    }
}
