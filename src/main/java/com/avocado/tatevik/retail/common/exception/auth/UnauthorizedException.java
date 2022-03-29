package com.avocado.tatevik.retail.common.exception.auth;

import com.avocado.tatevik.retail.common.exception.enums.ExceptionCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnauthorizedException extends RuntimeException {

    private ExceptionCode code;

    private String message;

    public UnauthorizedException(ExceptionCode code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}