package com.teamdevroute.devroute.roadmap.dto;

import lombok.Builder;

import java.util.List;

public record RoadmapResponeDTO(String name, boolean toggled,List<RoadmapDTO> children) {
    @Builder
    public RoadmapResponeDTO(String name,boolean toggled,List<RoadmapDTO> children){
        this.name = name;
        this.toggled=toggled;
        this.children = children;
    }
}
