package com.teamdevroute.devroute.dataloader;

import com.teamdevroute.devroute.bookmark.Bookmark;
import com.teamdevroute.devroute.bookmark.BookmarkRepository;
import com.teamdevroute.devroute.user.UserRepository;
import com.teamdevroute.devroute.user.domain.User;
import com.teamdevroute.devroute.user.enums.DevelopField;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Profile("dev")
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

        Bookmark bookmark = null;
        if(bookmarkRepository.findById(1L).isEmpty()) {
            bookmark = bookmarkRepository.save(
                    Bookmark.builder()
                            .companies(new ArrayList<>())
                            .roadmaps(new ArrayList<>())
                            .videos(new ArrayList<>())
                            .build()
            );
        } else {
            bookmark = bookmarkRepository.findById(1L).get();
        }

        loadUserData("admin@admin.com", bookmark);
        companyDataLoader.loadCompanyData();
        recruitmentDataLoader.loadRecruitmentData();
        //videoDataLoader.loadVideoData();
        bookmarkDataLoader.loadBookmark(bookmark);
    }

    private void loadUserData(String email, Bookmark bookmark) {
        if(userRepository.findByEmail(email).isEmpty()) {
            userRepository.save(
                    User.builder()
                            .email(email)
                            .name("윤성원")
                            .userRole("USER")
                            .password(encoder.encode("1234"))
                            .developField(DevelopField.BACKEND)
                            .loginType("NORMAL")
                            .bookmark(bookmark)
                            .build());
        }
    }
}
