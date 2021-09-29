package com.avocado.tatevik.retail.common.exception.response;

import lombok.*;

@Getter
@Setter
public class ErrorResponseDto {

    private ExceptionCode code;

    private String message;

    public ErrorResponseDto(String message, ExceptionCode code) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String toString() {
        return "Error Response{" +
                "\ncode=" + code +
                ", \nmessage='" + message + '\'' +
                '}';
    }
}
