package com.teamdevroute.devroute.recruitment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamdevroute.devroute.company.domain.Company;
import com.teamdevroute.devroute.company.repository.CompanyRepository;
import com.teamdevroute.devroute.global.aop.crawl.Crawling;
import com.teamdevroute.devroute.recruitment.domain.Recruitment;
import com.teamdevroute.devroute.recruitment.enums.SearchKeyWord;
import com.teamdevroute.devroute.recruitment.enums.Source;
import com.teamdevroute.devroute.recruitment.repository.RecruitmentRepository;
import com.teamdevroute.devroute.user.enums.DevelopField;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecruitmentUpdateService {

    @Value("${saramin.access-key}")
    private String accessKey;
    private String API_URL = "https://oapi.saramin.co.kr/job-search?access-key=";

    private final RecruitmentRepository recruitmentRepository;
    private final CompanyRepository companyRepository;

    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul") // 매일 자정 업데이트
    public void fetchJobs() {
        for (SearchKeyWord keyword : SearchKeyWord.values()) {
            fetchAndSaveJobs(keyword.name());
        }
    }

    private void fetchAndSaveJobs(String keyword) {

        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

        String initialUrl = API_URL + accessKey + "&keywords=" + keyword + "&start=0&count=100";
        String initialResponse = restTemplate.getForObject(initialUrl, String.class);

        System.out.println(initialResponse);
        try {
            if(isMaxApiRequest(objectMapper,initialResponse))
                return;
            int total = objectMapper.readTree(initialResponse).path("jobs").path("total").asInt();
            for (int i = 0; i <= total / 100; i++) {
                String url = API_URL + accessKey + "&keywords=" + keyword + "개발자" + "&start=" + i + "&count=100";
                String response = restTemplate.getForObject(url, String.class);
                JsonNode saraminResponses = objectMapper.readTree(response).path("jobs").path("job");
                parseAndStoreRecruitment(saraminResponses, keyword);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isMaxApiRequest(ObjectMapper objectMapper,String initialResponse) throws JsonProcessingException {
        return objectMapper.readTree(initialResponse).path("message").asText().equals("일일 최대 요청 가능 횟수가 초과되었습니다.");
    }

    private void parseAndStoreRecruitment(JsonNode saraminResponses, String keyword) {
        List<Recruitment> recruitments = new ArrayList<>();

        for (JsonNode saraminResponse : saraminResponses) {
            Recruitment recruitment = parseResponse(saraminResponse, keyword);

            if (!recruitmentRepository.existsByUrl(recruitment.getUrl())) {
                recruitments.add(recruitment);
            }
        }
        recruitmentRepository.saveAll(recruitments);
    }

    private Recruitment parseResponse(JsonNode jobNode, String keyword) {
        String url = jobNode.path("url").asText();
        String companyName = jobNode.path("company").path("detail").path("name").asText();
        String experienceLevel = jobNode.path("position").path("experience-level").path("name").asText();
        long expirationTimestamp = jobNode.path("expiration-timestamp").asLong();
        LocalDate expirationDate = LocalDate.ofInstant(Instant.ofEpochSecond(expirationTimestamp), ZoneOffset.UTC);
        String techStacks = jobNode.path("position").path("job-code").path("name").asText();

        Company company = companyRepository.findByName(companyName).orElseGet(() -> {
            Company newCompany = Company.builder()
                    .name(companyName)
                    .recruitCount(0L)
                    .clickCount(0L)
                    .build();

            return companyRepository.save(newCompany);
        });

        return Recruitment.builder()
                .techStacks(List.of(techStacks.split(",")))
                .annual(experienceLevel)
                .dueDate(expirationDate)
                .url(url)
                .company(company)
                .source(Source.SARAMIN)
                .developField(DevelopField.toEnumBySearchKeyWord(keyword))
                .build();
    }
}
