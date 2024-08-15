package com.teamdevroute.devroute.roadmap.dto;

import lombok.Builder;

import java.util.List;

public record RoadmapResponseDTO(String name, List<RoadmapDTO> RoadmapDTOs) {
    @Builder
    public RoadmapResponseDTO(String name, List<RoadmapDTO> RoadmapDTOs){
        this.name = name;
        this.RoadmapDTOs = RoadmapDTOs;
    }
}
