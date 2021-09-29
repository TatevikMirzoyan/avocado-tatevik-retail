package com.avocado.tatevik.retail.common.exception.shop;

import com.avocado.tatevik.retail.common.exception.DomainNotValidException;
import com.avocado.tatevik.retail.common.exception.response.ExceptionCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopNotValidException extends DomainNotValidException {

    private ExceptionCode code;

    private String message;

    public ShopNotValidException(ExceptionCode code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
