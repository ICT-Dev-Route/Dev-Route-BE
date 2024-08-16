package com.teamdevroute.devroute.dataloader;

import com.teamdevroute.devroute.company.domain.Company;
import com.teamdevroute.devroute.recruitment.domain.Recruitment;
import com.teamdevroute.devroute.recruitment.enums.Source;
import com.teamdevroute.devroute.recruitment.repository.RecruitmentRepository;
import com.teamdevroute.devroute.user.enums.DevelopField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class RecruitmentDataLoader {

    @Autowired
    private RecruitmentRepository recruitmentRepository;

    @Autowired
    private CompanyDataLoader  companyDataLoader;

    public void loadRecruitmentData() {
        List<Company> companies = companyDataLoader.getCompanyList();

        createRecruitment(
                companies.get(0), Arrays.asList("JAVA", "SPRING"),
                "4년차", LocalDate.now(),
                Source.JUMPIT, "https://kakao.com/backend",
                DevelopField.AI
        );
        createRecruitment(
                companies.get(0), Arrays.asList("HTML", "REACT"),
                "2년차", LocalDate.now(),
                Source.JUMPIT, "https://kakao.com/frontend",
                DevelopField.DATA_SCIENCE
        );

        createRecruitment(
                companies.get(1), Arrays.asList("PHP", "GIT"),
                "경력무관", LocalDate.now(),
                Source.SARAMIN, "https://naver.com",
                DevelopField.BACKEND
        );

        createRecruitment(
                companies.get(3), Arrays.asList("AWS", "Docker"),
                "10년", LocalDate.now(),
                Source.SARAMIN, "https://samsung.com",
                DevelopField.MOBILE
        );
    }


    private void createRecruitment(
            Company company,
            List<String> techStacks,
            String annual,
            LocalDate dueDate,
            Source source,
            String url,
            DevelopField developField
    ) {
        Recruitment recruitment = Recruitment.builder()
                .company(company)
                .developField(developField)
                .url(url)
                .techStacks(techStacks)
                .annual(annual)
                .dueDate(dueDate)
                .source(source)
                .build();
        recruitmentRepository.save(recruitment);
    }

}
