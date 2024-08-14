package com.teamdevroute.devroute.bookmark;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@Controller
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @PostMapping("/bookmark/add")
    public ResponseEntity addBookmark(
            @RequestBody BookmarkUpdateRequest request
    ) {
        bookmarkService.updateBookmark(request);
        return ResponseEntity.ok("북마크가 추가되었습니다.");
    }

    @PostMapping("/bookmark/get")
    public ResponseEntity getBookmark(
            @RequestBody BookmarkFindRequest request
            ) {

        Bookmark bookmark = bookmarkService.findBookmarkByType(request.getUserId());
        if(bookmark == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bookmark);
    }

}
