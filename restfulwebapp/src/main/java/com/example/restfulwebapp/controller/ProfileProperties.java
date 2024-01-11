package com.example.restfulwebapp.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import jakarta.validation.constraints.Email; // 之前是 javax ，现在是 jakarta
import jakarta.validation.constraints.NotEmpty; // 添加依赖 spring-boot-starter-validation

@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "profile")
@Validated
public class ProfileProperties {
    @NotEmpty
    private String name;

    @NotEmpty
    @Email
    private String email;

    private Boolean handsome = Boolean.TRUE;// 如果配置文件中没有读取到属性，就使用默认值

    public String ToString() {
        return "ProfileProperties [name=" + name + ", email=" + email + ", handsome=" + handsome + "]";
    }
}
