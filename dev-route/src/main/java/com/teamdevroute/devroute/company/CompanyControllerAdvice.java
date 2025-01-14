package com.teamdevroute.devroute.company;

import com.teamdevroute.devroute.global.error.exception.CompanyNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CompanyControllerAdvice {

    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity handleCompanyNotFoundExceptionException(CompanyNotFoundException e) {
        return ResponseEntity.badRequest().body("기업을 찾을 수 없습니다.");
    }
}
