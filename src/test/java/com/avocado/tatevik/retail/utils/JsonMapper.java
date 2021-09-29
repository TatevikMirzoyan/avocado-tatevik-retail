package com.avocado.tatevik.retail.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMapper<T> {

    private static final ObjectMapper mapper = new ObjectMapper();
//            .configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false)
//            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    // TODO: 8/27/21 Write normal exception handler
    public static <T> T getObjectFromJson(String json, Class<T> clazz) throws JsonProcessingException {
        return mapper.readValue(json, clazz);
    }

    public static String mapToJson(Object o) throws JsonProcessingException {
        return mapper.writeValueAsString(o);
    }
}
