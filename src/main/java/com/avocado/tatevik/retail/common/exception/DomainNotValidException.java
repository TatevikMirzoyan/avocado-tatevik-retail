package com.avocado.tatevik.retail.common.exception;

import com.avocado.tatevik.retail.common.exception.response.ExceptionCode;
import lombok.*;

@Getter
@Setter
public abstract class DomainNotValidException extends RuntimeException {

    private ExceptionCode code;

    private String message;

    public DomainNotValidException(String message) {
        super(message);
    }
}
