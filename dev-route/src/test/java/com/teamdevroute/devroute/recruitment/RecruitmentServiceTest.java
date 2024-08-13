package com.teamdevroute.devroute.recruitment;

import com.teamdevroute.devroute.recruitment.domain.Recruitment;
import com.teamdevroute.devroute.recruitment.dto.RecruitmentFindResponse;
import com.teamdevroute.devroute.recruitment.service.RecruitmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class RecruitmentServiceTest {

    @Autowired
    private RecruitmentService recruitmentService;

    @Test
    void search_develop_recruit() {
        List<RecruitmentFindResponse> res = recruitmentService.findByType("backend");
        assertThat(res.size()).isEqualTo(1);
    }
}
