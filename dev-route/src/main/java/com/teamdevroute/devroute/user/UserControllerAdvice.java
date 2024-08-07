package com.teamdevroute.devroute.user;

import com.teamdevroute.devroute.global.exception.DuplicateUserException;
import com.teamdevroute.devroute.global.exception.InvalidAccessException;
import com.teamdevroute.devroute.global.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserControllerAdvice {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity handleNotFoundUserException(UserNotFoundException e) {
        return ResponseEntity.badRequest().body("유저를 찾을 수 없습니다.");
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity handleBadCredentialsException(BadCredentialsException e) {
        return ResponseEntity.badRequest().body("비밀번호가 일치하지 않습니다.");
    }

    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity handleDuplicateUserException(DuplicateUserException e) {
        return ResponseEntity.badRequest().body("중복되는 유저 정보입니다.");
    }

    @ExceptionHandler(InvalidAccessException.class)
    public ResponseEntity handleInvalidAccessException(InvalidAccessException e) {
        return ResponseEntity.badRequest().body("접근 권한이 없습니다.");
    }

}
