package com.teamdevroute.devroute.crawling;

import com.teamdevroute.devroute.company.domain.Company;
import com.teamdevroute.devroute.company.repository.CompanyRepository;
import com.teamdevroute.devroute.company.service.CompanyService;
import com.teamdevroute.devroute.crawling.dto.CrawledCompanyDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyCrawlingService {

    private final CompanyRepository companyRepository;
    private final CompanyService companyService;

    public CompanyCrawlingService(CompanyRepository companyRepository, CompanyService companyService) {
        this.companyRepository = companyRepository;
        this.companyService = companyService;
    }

    public void createCompany(CrawledCompanyDto crawledCompanyDto) {
        List<String> enterpriseNames = crawledCompanyDto.getEnterpriseNames();
        List<String> enterpriseSalaries = crawledCompanyDto.getEnterpriseSalaries();
        List<String> enterpriseGrades = crawledCompanyDto.getEnterpriseGrades();
        List<String> enterpriseLogo = crawledCompanyDto.getEnterpriseLogo();

        for(int i=0;i<enterpriseNames.size();i++) {
            if(!companyService.validateDuplicateCompany(enterpriseNames.get(i))) {
                companyRepository.save(
                        Company.builder()
                                .name(enterpriseNames.get(i))
                                .averageSalary(enterpriseSalaries.get(i))
                                .grade(Double.parseDouble(enterpriseGrades.get(i)))
                                .logoUrl(enterpriseLogo.get(i))
                                .build()
                );
            }
        }
    }
}
