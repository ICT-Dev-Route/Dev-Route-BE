package com.teamdevroute.devroute.recruitment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamdevroute.devroute.company.repository.CompanyRepository;
import com.teamdevroute.devroute.recruitment.repository.RecruitmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import static com.teamdevroute.devroute.recruitment.enums.SearchKeyWord.백엔드;

@SpringBootTest
public class RecruitmentUpdateServiceTest {
    @Value("${saramin.access-key}")
    private String accessKey;
    private String API_URL = "https://oapi.saramin.co.kr/job-search?access-key=";

    @Autowired
    private  RecruitmentRepository recruitmentRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Test
    @DisplayName("사람인 사이트에서 response를 잘 가지고 오는지 확인한다")
    public void isRightResponse(){
        //given
        RestTemplate restTemplate = new RestTemplate();

        //when
        String initialUrl = API_URL + accessKey + "&keywords=" + 백엔드 + "&start=0&count=100";
        String initialResponse = restTemplate.getForObject(initialUrl, String.class);
        //then
        Assertions.assertNotNull(initialResponse);
        System.out.println(initialResponse);
    }
}
