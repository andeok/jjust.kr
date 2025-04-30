package kr.end.backend.common.exception;

import lombok.Getter;

@Getter

public class EndException extends RuntimeException {

  private final ErrorCode errorCode;

  public EndException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }
}
