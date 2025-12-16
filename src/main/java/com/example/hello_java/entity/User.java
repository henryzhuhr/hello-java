package com.example.hello_java.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Data
@Entity
@Getter
@Table(name = "user")
public class User {
    /**
     * 用户ID
     */
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    /**
     * 用户名
     */
    @Column(name = "username")
    private String username;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 用户状态 0: 禁用 1: 启用
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 用户时区
     */
    @Column(name = "time_zone")
    private String timeZone;

    /**
     * 国际化语言
     */
    @Column(name = "language")
    private String language;

    /**
     * 创建用户的时间
     */
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Date updateTime;
}
