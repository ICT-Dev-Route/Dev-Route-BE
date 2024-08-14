package com.teamdevroute.devroute.bookmark;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookmarkServiceTest {

    @Autowired
    private BookmarkService bookmarkService;

    @Test
    void update_bookmark() {
        BookmarkUpdateRequest request = new BookmarkUpdateRequest(1L, "company");
    }
}
