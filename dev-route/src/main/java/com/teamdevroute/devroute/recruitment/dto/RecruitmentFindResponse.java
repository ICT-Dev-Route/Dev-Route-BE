package com.teamdevroute.devroute.recruitment.dto;

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
public class RecruitmentFindResponse {
    private String companyName;
    private DevelopField developField;
    private List<String> techStack;
    private LocalDateTime dueDate;

    @Builder
    public RecruitmentFindResponse(String companyName, DevelopField developField, List<String> techStack, LocalDateTime dueDate) {
        this.companyName = companyName;
        this.developField = developField;
        this.techStack = techStack;
        this.dueDate = dueDate;
    }

    public static RecruitmentFindResponse from(Recruitment recruitment) {
        return RecruitmentFindResponse.builder()
                .companyName(recruitment.getCompany().getName())
                .developField(recruitment.getDevelopField())
                .techStack(recruitment.getTechStacks())
                .dueDate(recruitment.getDueDate())
                .build();
    }
}
