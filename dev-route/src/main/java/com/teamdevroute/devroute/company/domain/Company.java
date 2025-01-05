package com.teamdevroute.devroute.company.domain;

import com.teamdevroute.devroute.global.BaseTimeEntity;
import com.teamdevroute.devroute.recruitment.domain.Recruitment;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "company")
public class Company extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "logo_url")
    private String logoUrl;

    @Column(name = "click_count")
    private Long clickCount;

    @Column(name = "grade")
    private Double grade;

    @Column(name = "average_salary")
    private String averageSalary;

    @Column(name = "recruit_count")
    private Long recruitCount;

    @Column(name = "info", columnDefinition = "text")
    private String info;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Recruitment> recruitments = new ArrayList<>();

    @Builder
    public Company(String name, String logoUrl, Long clickCount, Double grade, String averageSalary, Long recruitCount, String info) {
        this.name = name;
        this.logoUrl = logoUrl;
        this.clickCount = clickCount;
        this.grade = grade;
        this.averageSalary = averageSalary;
        this.recruitCount = recruitCount;
        this.info = info;
    }


}
