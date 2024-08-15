package com.teamdevroute.devroute.bookmark;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.teamdevroute.devroute.bookmark.exception.DuplicateBookmarkContentException.*;

@ControllerAdvice
public class BookmarkControllerAdvice {
    @ExceptionHandler(DuplicateCompanyException.class)
    public ResponseEntity handleDuplicateCompanyExceptionException(DuplicateCompanyException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("북마크하려는 기업이 이미 등록되어 있습니다.");
    }
    @ExceptionHandler(DuplicateVideoException.class)
    public ResponseEntity handleDuplicateVideoExceptionException(DuplicateVideoException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("북마크하려는 강의가 이미 등록되어 있습니다.");
    }
    @ExceptionHandler(DuplicateRoadmapException.class)
    public ResponseEntity handleDuplicateRoadmapExceptionException(DuplicateRoadmapException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("북마크하려는 로드맵이 이미 등록되어 있습니다.");
    }
}
