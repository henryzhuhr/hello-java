package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE) // 私有化全参构造函数
public class Result<T> {
    private Integer code; // 状态码
    private String message; // 消息
    private T data; // 数据

    // 创建成功的 Result 对象（带数据）
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "success", data);
    }

    // 创建成功的 Result 对象（不带数据）
    public static <T> Result<T> success() {
        return new Result<>(200, "success", null);
    }

    // 创建失败的 Result 对象
    public static <T> Result<T> error(Integer code, String message) {
        if (code == null || code < 0) {
            throw new IllegalArgumentException("状态码必须是非负整数");
        }
        if (message == null || message.isEmpty()) {
            throw new IllegalArgumentException("消息不能为空");
        }
        return new Result<>(code, message, null);
    }
}