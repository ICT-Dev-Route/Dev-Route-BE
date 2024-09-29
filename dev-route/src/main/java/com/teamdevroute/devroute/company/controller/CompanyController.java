package com.teamdevroute.devroute.company.controller;

import com.teamdevroute.devroute.company.dto.CompanyDetailResponse;
import com.teamdevroute.devroute.company.dto.CompanyResponse;
import com.teamdevroute.devroute.company.service.CompanyService;
import com.teamdevroute.devroute.crawling.CompanyRecruitmentCrawling;
import com.teamdevroute.devroute.global.aop.TimeTrace;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Controller
public class CompanyController {

    private CompanyService companyService;
    private CompanyRecruitmentCrawling recruitmentCrawling;

    @GetMapping("/recruit/enterprise")
    public ResponseEntity getAllCompanies() {
        List<CompanyResponse> response = companyService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/recruit/company/{companyId}")
    public ResponseEntity getCompanyDetail(@PathVariable("companyId") long companyId) {
        CompanyDetailResponse response = companyService.findCompanyDetail(companyId);
        return ResponseEntity.ok(response);
    }

    @TimeTrace
    @ResponseBody
    @GetMapping("/recruit/crwal")
    public String crwalRecruitmentTest(){
recruitmentCrawling.companyAndRecruitmentCrawling();
        return "successfull crawl";
    }
}
