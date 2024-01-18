package com.example.helloword.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.helloword.entity.User;

@RestController // Controller 里面的方法都以 json 格式输出，不用再写什么 jackjson 配置的了！
public class CustomerController {
    @RequestMapping("/hello")
    public String index() {
        return "Hello Spring Boot";
    }

    @RequestMapping("/getUser")
    public User getUser() {
        User user = new User();
        user.setId(2000L);
        user.setUserName("小明");
        user.setPassWord("123456");
        user.setEmail("123456@gamil.com");
        user.setNickName("小小明");
        user.setRegistrationTime("2024-12-1");


        return user;
    }
}
