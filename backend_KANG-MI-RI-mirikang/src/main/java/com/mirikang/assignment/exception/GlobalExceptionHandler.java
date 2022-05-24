package com.mirikang.assignment.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public Error errorHandler(Exception e) {
        log.error(e.getMessage(), e);
        return new Error(-500, "시스템 오류가 발생하였습니다. 잠시 후, 다시 시도해주세요.");
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Error remainedVacationHandler(NoRemainedVacationCountException e) {
        log.error(e.getMessage(), e);
        return new Error(-400, "남은 연차일수가 존재하지 않습니다.");
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
    public Error validPeriodVacationHandler(NotValidPeriodException e) {
        log.error(e.getMessage(), e);
        return new Error(-406, "날짜 설정이 잘못 되었습니다.");
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
    public Error validRequestDateHandler(NotValidRequestDateException e) {
        log.error(e.getMessage(), e);
        return new Error(-406, "휴가 신청일이 공휴일입니다.");
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Error cancelVacationHandler(UnableToCancelException e) {
        log.error(e.getMessage(), e);
        return new Error(-400, "이미 사용한 휴가 일자입니다.");
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public Error resourceFoundHandler(ResourceNotFoundException e) {
        log.error(e.getMessage(), e);
        return new Error(-404, "데이터가 존재하지 않습니다.");
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Error resourceFoundHandler(BadCredentialsException e) {
        log.error(e.getMessage(), e);
        return new Error(-400, "사용자 인증에 실패하였습니다.");
    }
}
