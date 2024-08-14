package com.teamdevroute.devroute.company.dto;

import com.teamdevroute.devroute.company.domain.Company;
import com.teamdevroute.devroute.recruitment.domain.Recruitment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CompanyDetailResponse {
    private String name;
    private String info;
    private Double grade;
    private String averageSalary;
    private String logoUrl;
    private List<CompanyDetailRecruitResponse> recruitments;

    @Builder
    public CompanyDetailResponse(String name, String info, Double grade, String averageSalary, String logoUrl, List<CompanyDetailRecruitResponse> recruitments) {
        this.name = name;
        this.info = info;
        this.grade = grade;
        this.averageSalary = averageSalary;
        this.logoUrl = logoUrl;
        this.recruitments = recruitments;
    }

    public static CompanyDetailResponse from(
            Company company,
            List<CompanyDetailRecruitResponse> recruitments
    ) {
        return CompanyDetailResponse.builder()
                .name(company.getName())
                .info(company.getInfo())
                .grade(company.getGrade())
                .averageSalary(company.getAverageSalary())
                .logoUrl(company.getLogoUrl())
                .recruitments(recruitments)
                .build();
    }
}
