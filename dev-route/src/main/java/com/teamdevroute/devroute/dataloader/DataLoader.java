package com.teamdevroute.devroute.dataloader;

import com.teamdevroute.devroute.bookmark.Bookmark;
import com.teamdevroute.devroute.bookmark.BookmarkRepository;
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
import com.teamdevroute.devroute.video.domain.TechnologyStack;
import com.teamdevroute.devroute.video.domain.Videos;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Profile("default")
@Component
@Slf4j
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private CompanyDataLoader companyDataLoader;
    @Autowired
    private RecruitmentDataLoader recruitmentDataLoader;
    @Autowired
    private VideoDataLoader videoDataLoader;
    @Autowired
    private BookmarkRepository bookmarkRepository;
    @Autowired
    private BookmarkDataLoader bookmarkDataLoader;

    @Override
    public void run(String... args) throws Exception {

        Bookmark bookmark1 = bookmarkRepository.save(
                Bookmark.builder()
                        .companies(new ArrayList<>())
                        .roadmaps(new ArrayList<>())
                        .videos(new ArrayList<>())
                        .build()
        );

        User user1 = userRepository.save(
                User.builder()
                        .email("admin@example.com")
                        .name("윤성원")
                        .userRole("USER")
                        .password(encoder.encode("1234"))
                        .developField(DevelopField.BACKEND)
                        .loginType("NORMAL")
                        .bookmark(bookmark1)
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

        companyDataLoader.loadCompanyData();
        recruitmentDataLoader.loadRecruitmentData();
        videoDataLoader.loadVideoData();
        bookmarkDataLoader.loadBookmark(bookmark1);
    }
}
