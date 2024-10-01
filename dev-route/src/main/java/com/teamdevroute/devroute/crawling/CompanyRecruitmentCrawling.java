package com.teamdevroute.devroute.crawling;

import com.teamdevroute.devroute.crawling.dto.CrawledCompanyDto;
import com.teamdevroute.devroute.crawling.dto.CrawledRecruitmentDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class CompanyRecruitmentCrawling {

    private final CompanyCrawling companyCrawling;
    private final RecruitmentCrawling recruitmentCrawling;
    private final RecruitmentCrawlingService recruitmentCrawlingService;

    public CompanyRecruitmentCrawling(CompanyCrawling companyCrawling, RecruitmentCrawling recruitmentCrawling, RecruitmentCrawlingService recruitmentCrawlingService) {
        this.companyCrawling = companyCrawling;
        this.recruitmentCrawling = recruitmentCrawling;
        this.recruitmentCrawlingService = recruitmentCrawlingService;
    }

    @Scheduled(cron = "0 0 0 1 * *", zone = "Asia/Seoul")
    public void companyAndRecruitmentCrawling() {
        CrawledCompanyDto crawledCompanyDto = companyCrawling.getCompanyThreePage();
        log.info(crawledCompanyDto.toString());
        List<CrawledRecruitmentDto> list = recruitmentCrawling.crawlingJUMPIT(crawledCompanyDto.getEnterpriseNames());
        recruitmentCrawlingService.createRecruitment(list);
    }
}
