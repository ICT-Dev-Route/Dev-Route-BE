package com.teamdevroute.devroute.video.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
@Entity
@Getter
//@Setter
public class TechnologyStack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "technology_stack_id")
    private Long id;
    private String name;
    private Long count;

    public TechnologyStack() {
    }

    public Long getAddedCount() {
        return count + 1;
    }
}