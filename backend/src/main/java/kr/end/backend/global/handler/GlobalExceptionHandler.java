package kr.end.backend.global.handler;

import jakarta.servlet.http.HttpServletRequest;
import kr.end.backend.global.exception.JJUSTException;
import kr.end.backend.global.exception.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(JJUSTException.class)
  public ResponseEntity<ErrorResponse> handleBalanceTalkException(JJUSTException e,
      HttpServletRequest request) {

    ErrorResponse response = new ErrorResponse(
        e.getErrorCode().getHttpStatus(),
        request.getRequestURI(),
        e.getMessage());

    return ResponseEntity.status(response.httpStatus()).body(response);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException e,
      HttpServletRequest request) {

    ErrorResponse response = new ErrorResponse(
        (HttpStatus) e.getStatusCode(),
        request.getRequestURI(),
        e.getBindingResult().getFieldError().getDefaultMessage());

    return ResponseEntity.status(response.httpStatus()).body(response);
  }

}