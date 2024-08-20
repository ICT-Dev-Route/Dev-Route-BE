package com.teamdevroute.devroute.company;

import com.teamdevroute.devroute.company.dto.CompanyResponse;
import com.teamdevroute.devroute.company.service.CompanyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ActiveProfiles("test")
public class CompanyServiceTest {

    @Autowired
    private CompanyService companyService;

    @DisplayName("모든 회사 정보를 조회합니다.")
    @Test
    void get_all_company() {
        List<CompanyResponse> result = companyService.findAll();
        assertThat(result.size()).isEqualTo(4);
    }
}
