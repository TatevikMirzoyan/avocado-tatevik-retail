package com.avocado.tatevik.retail.common.exception.auth;

import com.avocado.tatevik.retail.common.exception.response.ExceptionCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationTokenExpiredException  extends RuntimeException {

    private ExceptionCode code;

    private String message;

    public AuthenticationTokenExpiredException(ExceptionCode code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
