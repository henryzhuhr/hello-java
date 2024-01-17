package com.example.mybatisintegrationdemo.controoler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mybatisintegrationdemo.pojo.User;
import com.example.mybatisintegrationdemo.service.UserService;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/findById")
    public User findById(Integer id) {
        return userService.findById(id);
    }
}
