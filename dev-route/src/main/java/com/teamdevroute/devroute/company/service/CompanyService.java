package com.teamdevroute.devroute.company.service;

import com.teamdevroute.devroute.company.domain.Company;
import com.teamdevroute.devroute.company.dto.CompanyDetailResponse;
import com.teamdevroute.devroute.company.repository.CompanyRepository;
import com.teamdevroute.devroute.company.dto.CompanyResponse;
import com.teamdevroute.devroute.global.exception.CompanyNotFoundException;
import com.teamdevroute.devroute.recruitment.domain.Recruitment;
import com.teamdevroute.devroute.recruitment.repository.RecruitmentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class CompanyService {

    private CompanyRepository companyRepository;
    private RecruitmentRepository recruitmentRepository;

    public List<CompanyResponse> findAll() {
        List<Company> companyList = companyRepository.findAll();
        return companyList.stream().map(CompanyResponse::of).toList();
    }

    public Company findById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(CompanyNotFoundException::new);
    }

    public CompanyDetailResponse findCompanyDetail(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(CompanyNotFoundException::new);
        List<Recruitment> recruitments = recruitmentRepository.findByCompany(company);
        return CompanyDetailResponse.from(company, recruitments);
    }
}
