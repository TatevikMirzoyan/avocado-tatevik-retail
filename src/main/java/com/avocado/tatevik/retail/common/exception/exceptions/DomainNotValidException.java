package com.avocado.tatevik.retail.common.exception.exceptions;

import com.avocado.tatevik.retail.common.exception.enums.ExceptionCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class DomainNotValidException extends RuntimeException {

    private ExceptionCode code;

    private String message;

    public DomainNotValidException(String message) {
        super(message);
    }
}
