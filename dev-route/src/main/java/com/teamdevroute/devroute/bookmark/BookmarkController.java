package com.teamdevroute.devroute.bookmark;

import com.teamdevroute.devroute.global.aop.timetrace.TimeTrace;
import com.teamdevroute.devroute.global.auth.LoginUserInfo;
import com.teamdevroute.devroute.user.domain.CustomUserDetails;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @TimeTrace
    @PostMapping("/bookmark/add")
    public ResponseEntity addBookmark(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody BookmarkUpdateRequest request
    ) {
        LoginUserInfo user = userDetails.getUser();
        bookmarkService.updateBookmark(request, user.getId());
        return ResponseEntity.ok("북마크가 추가되었습니다.");
    }

    @TimeTrace
    @GetMapping("/bookmark/get")
    public ResponseEntity getBookmark(
            @AuthenticationPrincipal CustomUserDetails userDetails
            ) {
        LoginUserInfo user = userDetails.getUser();
        Bookmark bookmark = bookmarkService.findBookmarkByType(user.getId());
        if(bookmark == null) {
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
