package com.teamdevroute.devroute;

import com.teamdevroute.devroute.company.domain.Company;
import com.teamdevroute.devroute.company.repository.CompanyRepository;
import com.teamdevroute.devroute.recruitment.domain.Recruitment;
import com.teamdevroute.devroute.recruitment.enums.Source;
import com.teamdevroute.devroute.recruitment.repository.RecruitmentRepository;
import com.teamdevroute.devroute.roadmap.RoadmapService;
import com.teamdevroute.devroute.user.UserRepository;
import com.teamdevroute.devroute.user.domain.User;
import com.teamdevroute.devroute.user.enums.DevelopField;
import com.teamdevroute.devroute.video.Repository.TechnologyStackRepository;
import com.teamdevroute.devroute.video.Repository.VideoRepository;
import com.teamdevroute.devroute.video.service.TechnologyStackService;
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
    @Autowired
    private RoadmapService roadmapService;

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

        createTech("htmlcss");
        createTech("python");
        createTech("java");
        createTech("javascript");
        createTech("spring");
        createTech("nodejs");
        createTech("react");
        createTech("Vuejs");
        createTech("Angular");

        createVideo(
                0L, "쉽게 배우는 HTML & CSS",
                "https://inflearn.co.kr/123332",
                "dummy thumnail_url",
                0L, "htmlcss", "Youtube"
        );
        createVideo(
                10000L, "HTML은 무엇인가?",
                "https://inflearn.co.kr/123332",
                "dummy thumnail_url",
                0L, "htmlcss", "Inflearn"
        );

        createVideo(
                20000L, "데이터분석을 위한 python",
                "https://Udemy.co.kr/2133",
                "dummy thumnail_url",
                0L, "python", "Inflearn"
        );
        createVideo(
                0L, "인공지능을 위한 python",
                "https://Youtube.com/dda22",
                "dummy thumnail_url",
                0L, "python", "Youtube"
        );
        createVideo(
                0L, "python은 개꿀언어입니다.",
                "https://Youtube.com/23232133",
                "dummy thumnail_url",
                0L, "python", "Youtube"
        );
        createVideo(
                0L, "java는 왜 쓰레기 언어인가?",
                "https://Youtube.co.kr/HrDAs23!dfA","dummy thumnail_url",

                0L, "java", "Youtube"
        );
        createVideo(
                0L, "1시간만에 자바 마스터",
                "https://Youtube.co.kr/ddaad!dfA",
                "dummy thumnail_url",
                0L, "java", "Youtube"
        );
        createVideo(
                0L, "자바 리펙토링",
                "https://Youtube.co.kr/fdfe432A",
                "dummy thumnail_url",
                0L, "java", "Youtube"
        );
        createVideo(
                0L, "java와 javascript",
                "https://Youtube.com/321321",
                "dummy thumnail_url",
                0L, "javascript", "Youtube"
        );
        createVideo(
                0L, "js 속성 강의",
                "https://Udemy.com/adasda",
                "dummy thumnail_url",
                0L, "javascript", "Udemy"
        );
        createVideo(
                0L, "javascript 2024 개정판",
                "https://inflearn.com/3213213",
                "dummy thumnail_url",
                0L, "javascript", "Inflearn"
        );

        createVideo(
                30000L, "토비의 spring framework",
                "https://inflearn.com/ddsad@dd",
                "dummy thumnail_url",
                0L, "spring", "Inflearn"
        );
        createVideo(
                54000L, "Spring 핵심 기술",
                "https://inflearn.com/ddggfgfgf",
                "dummy thumnail_url",
                0L, "spring", "Inflearn"
        );
        createVideo(
                0L, "무료 스프링 강의",
                "https://Youtube.com/dadwd22",
                "dummy thumnail_url",
                0L, "spring", "Inflearn"
        );
        createVideo(
                0L, "스프링 30분만에 이해하기",
                "https://Youtube.com/@@ㅇㅇ2",
                "dummy thumnail_url",
                0L, "spring", "Inflearn"
        );
        createVideo(
                0L, "이것도 모르면 스프링 쓰지 마세요",
                "https://Youtube.com/dfghhgjh",
                "dummy thumnail_url",
                0L, "spring", "Youtube"
        );

        createVideo(
                32000L, "What is Spring?",
                "https://Udemy.com/gfdghgh@",
                "dummy thumnail_url",
                0L, "spring", "Udemy"
        );
        createVideo(
                27000L, "Spring Boot Master",
                "https://Udemy.com/dadw@",
                "dummy thumnail_url",
                0L, "spring", "Udemy"
        );

        createVideo(
                0L, "Node.Js 와 Spring",
                "https://Youtube.com/dadw@22",
                "dummy thumnail_url",
                0L, "nodejs", "Youtube"
        );
        createVideo(
                0L, "노드의 종말이 시작됐다.",
                "https://Youtube.com/hghghg@22",
                "dummy thumnail_url",
                20L, "nodejs", "Youtube"
        );
        createVideo(
                0L, "나는 노드제이에스 마스터",
                "https://Youtube.com/gnfg2",
                "dummy thumnail_url",
                0L, "nodejs", "Youtube"
        );
        createVideo(
                0L, "nodeJS로 서버 구축하기",
                "https://inflearn.com/gnffdsfds",
                "dummy thumnail_url",
                0L, "nodejs", "Inflearn"
        );
        createVideo(
                33000L, "nodeJS 2024 최신판",
                "https://inflearn.com/gnfds",
                "dummy thumnail_url",
                0L, "nodejs", "Inflearn"
        );
        createVideo(
                25600L, "nodeJS 성능 최적화",
                "https://inflearn.com/gnffdds",
                "dummy thumnail_url",
                0L, "nodejs", "Inflearn"
        );
        createVideo(
                17000L, "미친 nodejs",
                "https://Udemy.com/fdsfdsfsdfsfsdf",
                "dummy thumnail_url",
                15L, "nodejs", "Udemy"
        );
        createVideo(
                12200L, "NodeJS Server",
                "https://Udemy.com/fdsfdsfsdf",
                "dummy thumnail_url",
                0L, "nodejs", "Udemy"
        );
        createVideo(
                27000L, "NodeJS SQL",
                "https://Udemy.com/fdsfdsdfsfsdf",
                "dummy thumnail_url",
                0L, "nodejs", "Udemy"
        );

        createVideo(
                0L, "React 배우기",
                "https://Youtube.com/hgfhgfdg",
                "dummy thumnail_url",
                0L, "react", "Youtube"
        );
        createVideo(
                0L, "React공화국",
                "https://Youtube.com/fhgfdg",
                "dummy thumnail_url",
                0L, "react", "Youtube"
        );
        createVideo(
                0L, "리액트의 미래",
                "https://Youtube.com/ggfdg",
                "dummy thumnail_url",
                0L, "react", "Youtube"
        );
        createVideo(
                32320L, "리액트 6시간 마스터",
                "https://inflearn.com/4521421",
                "dummy thumnail_url",
                0L, "react", "Inflearn"
        );
        createVideo(
                26660L, "리액트 기본만 하자",
                "https://inflearn.com/41421",
                "dummy thumnail_url",
                0L, "react", "Inflearn"
        );
        createVideo(
                0L, "리액트 입문 강의",
                "https://inflearn.com/1421",
                "dummy thumnail_url",
                0L, "react", "Inflearn"
        );

        createVideo(
                23000L, "How To Use React",
                "https://Udemy.com/1421",
                "dummy thumnail_url",
                0L, "react", "Udemy"
        );
        createVideo(
                3000L, "React World",
                "https://Udemy.com/122221",
                "dummy thumnail_url",
                0L, "react", "Udemy"
        );
        createVideo(
                53000L, "I like react",
                "https://Udemy.com/3123211",
                "dummy thumnail_url",
                0L, "react", "Udemy"
        );

        createVideo(
                0L, "뷰 제이에스란 무엇인가",
                "https://Youtube.com/54353",
                "dummy thumnail_url",
                13L, "Vuejs", "Youtube"
        );
        createVideo(
                0L, "나는야 Vuejs 마스터",
                "https://Youtube.com/5ㄹㄴㄷㄹㄴㄷ53",
                "dummy thumnail_url",
                11L, "Vuejs", "Youtube"
        );
        createVideo(
                0L, "Vuejs vs React",
                "https://Youtube.com/421414",
                "dummy thumnail_url",
                0L, "Vuejs", "Youtube"
        );

        createVideo(
                23000L, "Vuejs 3시간 마스터",
                "https://inflearn.com/534523",
                "dummy thumnail_url",
                2L, "Vuejs", "inflearn"
        );
        createVideo(
                33000L, "Vuejs 이것만 알자",
                "https://inflearn.com/523",
                "dummy thumnail_url",
                10L, "Vuejs", "inflearn"
        );
        createVideo(
                53000L, "이것만 알면 Vuejs 마스터",
                "https://inflearn.com/534523",
                "dummy thumnail_url",
                7L, "Vuejs", "inflearn"
        );

        createVideo(
                3000L, "How to use Vuejs",
                "https://Udemy.com/534523",
                "dummy thumnail_url",

                0L, "Vuejs", "Udemy"
        );
        createVideo(
                56000L, "vvvvvVuejs",
                "https://Udemy.com/h8h8",
                "dummy thumnail_url",
                0L, "Vuejs", "Udemy"
        );
        createVideo(
                77000L, "Vuejs Lecture",
                "https://Udemy.com/gfdgdf3",
                "dummy thumnail_url",
                0L, "Vuejs", "Udemy"
        );

        createVideo(
                0L, "이건 또 무슨 기술인가",
                "https://Youtube.com/321321",
                "dummy thumnail_url",
                0L, "Angular", "Youtube"
        );
        createVideo(
                0L, "앵귤라 앵귤라",
                "https://Youtube.com/43443321",
                "dummy thumnail_url",
                0L, "Angular", "Youtube"
        );
        createVideo(
                0L, "뭐라고 지어야하나",
                "https://Youtube.com/675321",
                "dummy thumnail_url",
                0L, "Angular", "Youtube"
        );

        createVideo(
                33330L, "Angular 4시간 마스터",
                "https://inflrean.com/675321",
                "dummy thumnail_url",
                0L, "Angular", "inflearn"
        );
        createVideo(
                5550L, "Angular 기본 문법 마스터",
                "https://inflrean.com/6543534",
                "dummy thumnail_url",
                3L, "Angular", "inflearn"
        );
        createVideo(
                100000L, "Angular 4시간 마스터",
                "https://inflrean.com/675321",
                "dummy thumnail_url",
                4L, "Angular", "inflearn"
        );

        createVideo(
                100000L, "Angular 4 hour master",
                "https://Udemy.com/675321",
                "dummy thumnail_url",
                0L, "Angular", "Udemy"
        );
        createVideo(
                1000L, "Angular is very fun",
                "https://Udemy.com/1213123121",
                "dummy thumnail_url",
                0L, "Angular", "Udemy"
        );
        createVideo(
                10000L, "Angular Lecture",
                "https://Udemy.com/123121",
                "dummy thumnail_url",
                0L, "Angular", "Udemy"
        );
        createAllRoadmaps();


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
        String thumnail_url,
        Long count,
        String techStack,
        String platform_name

    ) {
        Videos videos = Videos.builder()
                .price(price)
                .title(title)
                .url(url)
                .count(count)
                .thumnail_url(thumnail_url)
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
    private void createAllRoadmaps(){
        roadmapService.updateAllRoadmaps();
    }
}
