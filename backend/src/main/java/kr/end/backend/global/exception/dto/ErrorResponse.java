package kr.end.backend.global.exception.dto;

import org.springframework.http.HttpStatus;

public record ErrorResponse(HttpStatus httpStatus, String path ,String message) {

//    public static ErrorResponse from(final HttpStatus httpStatus, final String message) {
//        return new ErrorResponse(httpStatus, message);
//    }
}