package com.teamdevroute.devroute.company.dto;

import com.teamdevroute.devroute.recruitment.domain.Recruitment;
import com.teamdevroute.devroute.user.enums.DevelopField;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CompanyDetailRecruitResponse {
    private String companyName;
    private DevelopField developField;
    private List<String> techStacks;
    private LocalDate dueDate;

    @Builder
    public CompanyDetailRecruitResponse(String companyName, DevelopField developField, List<String> techStacks, LocalDate dueDate) {
        this.companyName = companyName;
        this.developField = developField;
        this.techStacks = techStacks;
        this.dueDate = dueDate;
    }

    public static CompanyDetailRecruitResponse from(Recruitment recruitment) {
        return CompanyDetailRecruitResponse.builder()
                .companyName(recruitment.getCompany().getName())
                .developField(recruitment.getDevelopField())
                .techStacks(recruitment.getTechStacks())
                .dueDate(recruitment.getDueDate())
                .build();
    }
}
