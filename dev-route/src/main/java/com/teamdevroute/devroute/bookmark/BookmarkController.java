package com.teamdevroute.devroute.bookmark;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@AllArgsConstructor
@Controller
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @PostMapping("/bookmark")
    public ResponseEntity addBookmark(
            @RequestParam(name = "userId") String id,
            @RequestBody BookmarkUpdateRequest request
    ) {

        bookmarkService.updateBookmark(Long.parseLong(id), request);
        return ResponseEntity.ok("북마크가 추가되었습니다.");
    }
}
