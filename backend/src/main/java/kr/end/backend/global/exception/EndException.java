package kr.end.backend.global.exception;

import lombok.Getter;

@Getter

public class EndException extends RuntimeException {

    private final ErrorCode errorCode;

    public EndException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
