package com.teamdevroute.devroute.roadmap.dto;

import lombok.Builder;

import java.util.List;

public record DetailedRoadmapResponseDTO(String brief_info,String description,
                                         List<String>  related_tecks ) {
    @Builder
    public DetailedRoadmapResponseDTO( String brief_info,String description,List<String>  related_tecks

                                     ){
        this.brief_info = brief_info;
        this.description = description;
        this.related_tecks = related_tecks;

    }

}
