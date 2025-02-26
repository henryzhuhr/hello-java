package com.example.hello_java.response;

import org.springframework.http.HttpStatus;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ApiResponse<T> {
    
    private int code;// 状态码
    private String message; // 消息
    private T data;// 数据


    public static <E> ApiResponse<E> success(E data) {
        return ApiResponse.<E>builder()
                .code(HttpStatus.OK.value())
                .message("success")
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> error(int code, String message) {
        return ApiResponse.<T>builder()
                .code(code)
                .message(message)
                .data(null)
                .build();
    }
}
