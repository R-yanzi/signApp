package com.sign.signin.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 根据json字符串和字节码文件转换成对应对象
     * @param jsonStr json字符串
     * @param clazz 字节码文件
     * @return 对象
     */
    public static <T> T toObject(String jsonStr, Class<T> clazz) throws JsonProcessingException {
        return new ObjectMapper().readValue(jsonStr, clazz);
    }
}
