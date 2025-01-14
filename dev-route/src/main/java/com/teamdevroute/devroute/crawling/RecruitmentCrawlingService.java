package com.teamdevroute.devroute.crawling;

import com.teamdevroute.devroute.company.domain.Company;
import com.teamdevroute.devroute.company.repository.CompanyRepository;
import com.teamdevroute.devroute.crawling.dto.CrawledRecruitmentDto;
import com.teamdevroute.devroute.recruitment.enums.Source;
import com.teamdevroute.devroute.recruitment.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class RecruitmentCrawlingService {

    private final RecruitmentRepository recruitmentRepository;
    private final CompanyRepository companyRepository;

    // TODO 채용공고 엔티티 수정 후 구현
    public void createRecruitment(List<CrawledRecruitmentDto> crawledRecruitmentDtoList) {

        for(CrawledRecruitmentDto dto : crawledRecruitmentDtoList) {
            log.info("company name:{}", dto.getCompanyName());
            Optional<Company> company = companyRepository.findByName(dto.getCompanyName());
            Source source = Source.JUMPIT;
            company.ifPresent(value -> recruitmentRepository.save(dto.toEntity(value, source)));
        }
    }
}
