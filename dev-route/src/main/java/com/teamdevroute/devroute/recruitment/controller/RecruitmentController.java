package com.teamdevroute.devroute.recruitment.controller;

import com.teamdevroute.devroute.recruitment.domain.Recruitment;
import com.teamdevroute.devroute.recruitment.dto.RecruitmentFindResponse;
import com.teamdevroute.devroute.recruitment.service.RecruitmentService;
import com.teamdevroute.devroute.recruitment.service.RecruitmentUpdateService;
import com.teamdevroute.devroute.user.enums.DevelopField;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RecruitmentController {

    private final RecruitmentUpdateService recruitmentUpdateService;
    private final RecruitmentService recruitmentService;

    @GetMapping("/fetch-jobs")
    public String fetchJobs() {
        recruitmentUpdateService.fetchJobs();
        return "Job fetching initiated";
    }

    @GetMapping("/recruit")
    public ResponseEntity searchRecruitmentByField(
            @RequestParam String type
    ) {
        List<RecruitmentFindResponse> response = recruitmentService.findByType(type);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/tech_stack/{type}")
    public ResponseEntity filterTechStackRate(@PathVariable("type") DevelopField type) {
        return ResponseEntity.ok(recruitmentService.filterTechStackRate(type));
    }
}