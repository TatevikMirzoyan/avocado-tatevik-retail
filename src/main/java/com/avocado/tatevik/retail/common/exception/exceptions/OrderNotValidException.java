package com.avocado.tatevik.retail.common.exception.exceptions;

import com.avocado.tatevik.retail.common.exception.enums.ExceptionCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderNotValidException extends DomainNotValidException {

    private ExceptionCode code;

    private String message;

    public OrderNotValidException(String message, ExceptionCode code) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
