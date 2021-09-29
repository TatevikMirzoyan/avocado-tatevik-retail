package com.avocado.tatevik.retail.common.exception.response;

import lombok.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.util.Assert.notNull;

@Getter
@Setter
//@NoArgsConstructor
public class ErrorResponseListDto {

    private final Map<ExceptionType, List<ErrorResponseDto>> exceptions = new HashMap<>();

    public void add(ErrorResponseDto error) {
        notNull(error, "Error should not be null");
        notNull(error.getCode(), "Error code should not be null");
        ExceptionType type = error.getCode().getType();

        List<ErrorResponseDto> value = exceptions.get(type);
        if (value == null) {
            value = new ArrayList<>();
        }
        value.add(error);
        exceptions.put(type, value);
    }
}
