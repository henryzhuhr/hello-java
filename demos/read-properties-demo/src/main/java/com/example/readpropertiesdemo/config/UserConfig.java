package com.example.readpropertiesdemo.config;

import org.hibernate.validator.constraints.Length;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 通过对两个文件的比对，我们发现，加了@Data注解的类，编译后会自动给我们加上下列方法：
 * 
 * 所有属性的get和set方法
 * toString 方法
 * hashCode方法
 * equals方法
 */
@Validated
@Data
@Component
@ConfigurationProperties(prefix = "user")
public class UserConfig {

    @Length(min = 6, max = 12, message = "appId长度必须位于6到12之间")
    private String appId;

    @NotBlank(message = "名字为必填项")
    private String name;

    @Email(message = "请填写正确的邮箱地址")
    private String email;

    @NotEmpty(message = "级别不能为空")
    private String level;

    public String toString() {
        return String.format("user[.appId=%s .name=%s .email=%s .level=%s]", appId, name, email, level);
    }
}
