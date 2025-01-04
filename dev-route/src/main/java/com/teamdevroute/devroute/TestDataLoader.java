package com.teamdevroute.devroute;

import com.teamdevroute.devroute.company.domain.Company;
import com.teamdevroute.devroute.company.repository.CompanyRepository;
import com.teamdevroute.devroute.recruitment.domain.Recruitment;
import com.teamdevroute.devroute.recruitment.enums.Source;
import com.teamdevroute.devroute.recruitment.repository.RecruitmentRepository;
import com.teamdevroute.devroute.user.repository.UserRepository;
import com.teamdevroute.devroute.user.domain.User;
import com.teamdevroute.devroute.user.enums.DevelopField;
import com.teamdevroute.devroute.user.enums.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Profile("test")
@Slf4j
@Component
@RequiredArgsConstructor
public class TestDataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final CompanyRepository companyRepository;
    private final RecruitmentRepository recruitmentRepository;

    @Override
    public void run(String... args) throws Exception {
        final User user1 = userRepository.save(User.builder()
                .id(1L)
                .name("윤성원")
                .email("admin@email.com")
                .password(encoder.encode("password"))
                .developField(DevelopField.BACKEND)
                .userRole(UserRole.ADMIN.name())
                .goal_info("목표정보")
                .introduce_info("소개말")
                .build());
        final User user2 = userRepository.save(User.builder()
                .id(2L)
                .name("누군가")
                .email("user@email.com")
                .password(encoder.encode("password"))
                .developField(DevelopField.AI)
                .userRole(UserRole.USER.name())
                .goal_info("목표정보")
                .introduce_info("소개말")
                .build());

        log.info("테스트 코드 - 유저 더미데이터 완료");

        Company company1 = createCompany(
                "카카오", "2034", "카카오는 이런저런 회사입니다.",
                5L, "https://kakao.com", 3.4
        );

        Company company2 = createCompany(
                "네이버", "5090", "네이버는 제가 가고싶은 회사입니다.",
                2L, "https://naver.com", 2.4
        );

        Company company3 = createCompany(
                "티몬", "-5000", "이 회사는 망한 회사입니다.",
                0L, "https://timon.com", 0.1
        );

        Company company4 = createCompany(
                "삼성", "4000", "이재용이 대빵인 회사입니다.",
                1L, "https://samsung.com", 4.0
        );

        log.info("테스트 코드 - 회사 더미데이터 완료");

        createRecruitment(
                company1, Arrays.asList("JAVA", "SPRING"),
                "4년차", LocalDate.now(),
                Source.JUMPIT, "https://kakao.com/backend",
                DevelopField.AI
        );
        createRecruitment(
                company1, Arrays.asList("HTML", "REACT"),
                "2년차", LocalDate.now(),
                Source.JUMPIT, "https://kakao.com/frontend",
                DevelopField.DATA_SCIENCE
        );

        createRecruitment(
                company2, Arrays.asList("PHP", "GIT"),
                "경력무관", LocalDate.now(),
                Source.SARAMIN, "https://naver.com",
                DevelopField.BACKEND
        );

        createRecruitment(
                company4, Arrays.asList("AWS", "Docker"),
                "10년", LocalDate.now(),
                Source.SARAMIN, "https://samsung.com",
                DevelopField.MOBILE
        );

        log.info("테스트 코드 - 채용공고 더미데이터 완료");
    }

    private Company createCompany(
            String name, String averageSalary,
            String info, Long recruitCount,
            String logoUrl, Double grade
    ) {
        Company company = Company.builder()
                .name(name)
                .averageSalary(averageSalary)
                .grade(grade)
                .clickCount(0L)
                .info(info)
                .recruitCount(recruitCount)
                .logoUrl(logoUrl)
                .build();
        companyRepository.save(company);
        return company;
    }

    private Recruitment createRecruitment(
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
        return recruitment;
    }
}
