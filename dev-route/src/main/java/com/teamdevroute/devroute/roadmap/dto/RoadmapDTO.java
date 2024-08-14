package com.teamdevroute.devroute.roadmap.dto;

import lombok.Builder;

public record RoadmapDTO(Long id, String name) {

    @Builder
    public RoadmapDTO(Long id, String name){
        this.id=id;
        this.name = name;

    }
}
