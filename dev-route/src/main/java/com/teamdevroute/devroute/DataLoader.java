package com.teamdevroute.devroute;

import com.teamdevroute.devroute.company.domain.Company;
import com.teamdevroute.devroute.company.repository.CompanyRepository;
import com.teamdevroute.devroute.recruitment.domain.Recruitment;
import com.teamdevroute.devroute.recruitment.enums.Source;
import com.teamdevroute.devroute.recruitment.repository.RecruitmentRepository;
import com.teamdevroute.devroute.user.UserRepository;
import com.teamdevroute.devroute.user.domain.User;
import com.teamdevroute.devroute.user.enums.DevelopField;
import com.teamdevroute.devroute.video.Repository.TechnologyStackRepository;
import com.teamdevroute.devroute.video.Repository.VideoRepository;
import com.teamdevroute.devroute.video.TechnologyStackService;
import com.teamdevroute.devroute.video.domain.TechnologyStack;
import com.teamdevroute.devroute.video.domain.Videos;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Profile("default")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private RecruitmentRepository recruitmentRepository;
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private TechnologyStackRepository technologyStackRepository;

    @Override
    public void run(String... args) throws Exception {
        User user1 = userRepository.save(
                User.builder()
                        .email("admin@example.com")
                        .name("윤성원")
                        .userRole("USER")
                        .password(encoder.encode("1234"))
                        .developField(DevelopField.BACKEND)
                        .loginType("NORMAL")
                        .build());

        User user2 = userRepository.save(
                User.builder()
                        .email("user1@example.com")
                        .name("성원윤")
                        .userRole("ADMIN")
                        .password(encoder.encode("1234"))
                        .developField(DevelopField.AI)
                        .loginType("NORMAL")
                        .build());

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

        createTech("html");
        createTech("css");
        createTech("python");
        createTech("java");
        createTech("javascript");
        createTech("spring");
        createTech("nodejs");
        createTech("react");
        createTech("vuejs");
        createTech("angular");


        createVideo(
                0L, "쉽게 배우는 HTML & CSS",
                "https://inflearn.co.kr/123332",
                0L, "html", "youtube"
        );
        createVideo(
                10000L, "HTML은 무엇인가?",
                "https://inflearn.co.kr/123332",
                0L, "html", "inflearn"
        );
        createVideo(
                0L, "css 30분만에 배우기",
                "https://youtube.com/dwaEW231D!4d",
                0L, "css", "youtube"
        );
        createVideo(
                20000L, "데이터분석을 위한 python",
                "https://udemy.co.kr/2133",
                0L, "python", "inflearn"
        );
        createVideo(
                0L, "인공지능을 위한 python",
                "https://youtube.com/dda22",
                0L, "python", "youtube"
        );
        createVideo(
                0L, "python은 개꿀언어입니다.",
                "https://youtube.com/23232133",
                0L, "python", "youtube"
        );
        createVideo(
                0L, "java는 왜 쓰레기 언어인가?",
                "https://youtube.co.kr/HrDAs23!dfA",
                0L, "java", "youtube"
        );
        createVideo(
                0L, "1시간만에 자바 마스터",
                "https://youtube.co.kr/ddaad!dfA",
                0L, "java", "youtube"
        );
        createVideo(
                0L, "자바 리펙토링",
                "https://youtube.co.kr/fdfe432A",
                0L, "java", "youtube"
        );
        createVideo(
                0L, "java와 javascript",
                "https://youtube.com/321321",
                0L, "javascript", "youtube"
        );
        createVideo(
                0L, "js 속성 강의",
                "https://udemy.com/adasda",
                0L, "javascript", "udemy"
        );
        createVideo(
                0L, "javascript 2024 개정판",
                "https://inflearn.com/3213213",
                0L, "javascript", "inflearn"
        );

        createVideo(
                30000L, "토비의 spring framework",
                "https://inflearn.com/ddsad@dd",
                0L, "spring", "inflearn"
        );
        createVideo(
                54000L, "Spring 핵심 기술",
                "https://inflearn.com/ddggfgfgf",
                0L, "spring", "inflearn"
        );
        createVideo(
                0L, "무료 스프링 강의",
                "https://youtube.com/dadwd22",
                0L, "spring", "youtube"
        );
        createVideo(
                0L, "스프링 30분만에 이해하기",
                "https://youtube.com/@@ㅇㅇ2",
                0L, "spring", "youtube"
        );
        createVideo(
                0L, "이것도 모르면 스프링 쓰지 마세요",
                "https://youtube.com/dfghhgjh",
                0L, "spring", "youtube"
        );

        createVideo(
                32000L, "What is Spring?",
                "https://udemy.com/gfdghgh@",
                0L, "spring", "udemy"
        );
        createVideo(
                27000L, "Spring Boot Master",
                "https://udemy.com/dadw@",
                0L, "spring", "udemy"
        );

        createVideo(
                0L, "Node.Js 와 Spring",
                "https://youtube.com/dadw@22",
                0L, "nodejs", "youtube"
        );
        createVideo(
                0L, "노드의 종말이 시작됐다.",
                "https://youtube.com/hghghg@22",
                0L, "nodejs", "youtube"
        );
        createVideo(
                0L, "나는 노드제이에스 마스터",
                "https://youtube.com/gnfg2",
                0L, "nodejs", "youtube"
        );
        createVideo(
                0L, "nodeJS로 서버 구축하기",
                "https://inflearn.com/gnffdsfds",
                0L, "nodejs", "inflearn"
        );
        createVideo(
                33000L, "nodeJS 2024 최신판",
                "https://inflearn.com/gnfds",
                0L, "nodejs", "inflearn"
        );
        createVideo(
                25600L, "nodeJS 성능 최적화",
                "https://inflearn.com/gnffdds",
                0L, "nodejs", "inflearn"
        );
        createVideo(
                17000L, "미친 nodejs",
                "https://udemy.com/fdsfdsfsdfsfsdf",
                0L, "nodejs", "udemy"
        );
        createVideo(
                12200L, "NodeJS Server",
                "https://udemy.com/fdsfdsfsdf",
                0L, "nodejs", "udemy"
        );
        createVideo(
                27000L, "NodeJS SQL",
                "https://udemy.com/fdsfdsdfsfsdf",
                0L, "nodejs", "udemy"
        );

        createVideo(
                0L, "React 배우기",
                "https://youtube.com/hgfhgfdg",
                0L, "react", "youtube"
        );
        createVideo(
                0L, "React공화국",
                "https://youtube.com/fhgfdg",
                0L, "react", "youtube"
        );
        createVideo(
                0L, "리액트의 미래",
                "https://youtube.com/ggfdg",
                0L, "react", "youtube"
        );
        createVideo(
                32320L, "리액트 6시간 마스터",
                "https://inflearn.com/4521421",
                0L, "react", "inflearn"
        );
        createVideo(
                26660L, "리액트 기본만 하자",
                "https://inflearn.com/41421",
                0L, "react", "inflearn"
        );
        createVideo(
                0L, "리액트 입문 강의",
                "https://inflearn.com/1421",
                0L, "react", "inflearn"
        );

        createVideo(
                23000L, "How To Use React",
                "https://udemy.com/1421",
                0L, "react", "udemy"
        );
        createVideo(
                3000L, "React World",
                "https://udemy.com/122221",
                0L, "react", "udemy"
        );
        createVideo(
                53000L, "I like react",
                "https://udemy.com/3123211",
                0L, "react", "udemy"
        );

        createVideo(
                0L, "뷰 제이에스란 무엇인가",
                "https://youtube.com/54353",
                0L, "vuejs", "youtube"
        );
        createVideo(
                0L, "나는야 vueJS 마스터",
                "https://youtube.com/5ㄹㄴㄷㄹㄴㄷ53",
                0L, "vuejs", "youtube"
        );
        createVideo(
                0L, "vueJS vs React",
                "https://youtube.com/421414",
                0L, "vuejs", "youtube"
        );

        createVideo(
                23000L, "vueJS 3시간 마스터",
                "https://inflearn.com/534523",
                0L, "vuejs", "inflearn"
        );
        createVideo(
                33000L, "vueJS 이것만 알자",
                "https://inflearn.com/523",
                0L, "vuejs", "inflearn"
        );
        createVideo(
                53000L, "이것만 알면 vueJS 마스터",
                "https://inflearn.com/534523",
                0L, "vuejs", "inflearn"
        );

        createVideo(
                3000L, "How to use vueJS",
                "https://udemy.com/534523",
                0L, "vuejs", "udemy"
        );
        createVideo(
                56000L, "vvvvvvueJS",
                "https://udemy.com/h8h8",
                0L, "vuejs", "udemy"
        );
        createVideo(
                77000L, "vueJS Lecture",
                "https://udemy.com/gfdgdf3",
                0L, "vuejs", "udemy"
        );

        createVideo(
                0L, "이건 또 무슨 기술인가",
                "https://youtube.com/321321",
                0L, "angular", "youtube"
        );
        createVideo(
                0L, "앵귤라 앵귤라",
                "https://youtube.com/43443321",
                0L, "angular", "youtube"
        );
        createVideo(
                0L, "뭐라고 지어야하나",
                "https://youtube.com/675321",
                0L, "angular", "youtube"
        );

        createVideo(
                33330L, "angular 4시간 마스터",
                "https://inflrean.com/675321",
                0L, "angular", "inflearn"
        );
        createVideo(
                5550L, "angular 기본 문법 마스터",
                "https://inflrean.com/6543534",
                0L, "angular", "inflearn"
        );
        createVideo(
                100000L, "angular 4시간 마스터",
                "https://inflrean.com/675321",
                0L, "angular", "inflearn"
        );

        createVideo(
                100000L, "angular 4 hour master",
                "https://udemy.com/675321",
                0L, "angular", "udemy"
        );
        createVideo(
                1000L, "angular is very fun",
                "https://udemy.com/1213123121",
                0L, "angular", "udemy"
        );
        createVideo(
                10000L, "angular Lecture",
                "https://udemy.com/123121",
                0L, "angular", "udemy"
        );




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

    private void createVideo(
        Long price,
        String title,
        String url,
        Long count,
        String techStack,
        String platform_name

    ) {
        Videos videos = Videos.builder()
                .price(price)
                .title(title)
                .url(url)
                .count(count)
                .teck_stack(techStack)
                .platform_name(platform_name)
                .build();
        videoRepository.save(videos);
    }

    private void createTech(
            String name
    ) {
        TechnologyStack technologyStack = TechnologyStack.builder()
                .name(name)
                .count(0L)
                .build();
        technologyStackRepository.save(technologyStack);
    }
}
