package com.avocado.tatevik.retail.common.exception.product;

import com.avocado.tatevik.retail.common.exception.DomainNotValidException;
import com.avocado.tatevik.retail.common.exception.response.ExceptionCode;
import lombok.*;

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
