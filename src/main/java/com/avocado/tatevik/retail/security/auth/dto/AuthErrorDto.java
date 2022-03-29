package com.avocado.tatevik.retail.security.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthErrorDto {

    private Integer status;

    private String message;

    @Override
    public String toString() {
        return "{" +
                "status=" + status +
                ", \nmessage='" + message + '\'' +
                '}';
    }
}
