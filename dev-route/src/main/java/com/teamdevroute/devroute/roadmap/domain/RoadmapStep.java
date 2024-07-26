package com.teamdevroute.devroute.roadmap.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class RoadmapStep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roadmap_step_id")
    private Long id;
    @Column(name="devlopment_field")
    private String developmentField;
    private String name;
    private String brief_info;
    @Builder
    public RoadmapStep(String developmentField,String name,String
                       brief_info)
    {
    this.developmentField=developmentField;
        this.name = name;
        this.brief_info = brief_info;
    }

    public RoadmapStep() {

    }
}
