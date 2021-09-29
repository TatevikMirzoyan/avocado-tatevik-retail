package com.avocado.tatevik.retail.common.exception.order;

import com.avocado.tatevik.retail.common.exception.DomainNotValidException;
import com.avocado.tatevik.retail.common.exception.response.ExceptionCode;
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
