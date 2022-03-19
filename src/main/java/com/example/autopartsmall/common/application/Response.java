package com.example.autopartsmall.common.application;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {
    public static final int MSG_CODE_SUCCESS = 0;

    private int code;

    private String msg;

    private T data;

    public static <T> Response withData(T data) {
        return Response.builder()
                .code(MSG_CODE_SUCCESS)
                .data(data)
                .build();
    }

    public static Response withError(int errCode, String message) {
        return Response.builder()
                .code(errCode)
                .msg(message)
                .build();
    }

    public static Response withError(String message) {
        return Response.builder()
                .msg(message)
                .build();
    }
}

