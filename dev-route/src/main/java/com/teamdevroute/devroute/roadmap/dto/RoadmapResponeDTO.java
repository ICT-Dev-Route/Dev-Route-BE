package com.teamdevroute.devroute.roadmap.dto;

import lombok.Builder;

import java.util.List;

public record RoadmapResponeDTO(String name, List<RoadmapDTO> roadmapDTOS) {
    @Builder
    public RoadmapResponeDTO(String name,List<RoadmapDTO> roadmapDTOS){
        this.name = name;
        this.roadmapDTOS = roadmapDTOS;
    }
}
