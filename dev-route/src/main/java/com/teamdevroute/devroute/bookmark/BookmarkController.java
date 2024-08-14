package com.teamdevroute.devroute.bookmark;

import com.teamdevroute.devroute.global.auth.LoginUserInfo;
import com.teamdevroute.devroute.user.domain.CustomUserDetails;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @PostMapping("/bookmark")
    public ResponseEntity addBookmark(
            @RequestBody BookmarkUpdateRequest request
    ) {
        bookmarkService.updateBookmark(request);
        return ResponseEntity.ok("북마크가 추가되었습니다.");
    }

    @GetMapping("/bookmark")
    public ResponseEntity getBookmark(
            @RequestParam(name = "userId") String id
    ) {

        Bookmark bookmark = bookmarkService.findBookmarkByType(Long.parseLong(id));
        if (bookmark == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bookmark);
    }

    @DeleteMapping("/bookmark")
    public ResponseEntity deleteBookmark(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody BookmarkDeleteRequest request
    ) {
        LoginUserInfo user = userDetails.getUser();
        bookmarkService.deleteBookmark(user.getId(), request);
        return ResponseEntity.ok("북마크가 삭제되었습니다.");
    }
}
