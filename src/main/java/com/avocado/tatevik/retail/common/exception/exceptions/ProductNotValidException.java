package com.avocado.tatevik.retail.common.exception.exceptions;

import com.avocado.tatevik.retail.common.exception.enums.ExceptionCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNotValidException extends DomainNotValidException {

    private ExceptionCode code;

    private String message;

    public ProductNotValidException(ExceptionCode code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
