package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.pojo.User;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User findUserByName(String name) {
        // 模拟从数据库中查找用户
        if ("admin".equals(name)) {
            return new User(); // 假设用户名 "admin" 已存在
        }
        return null; // 用户名不存在
    }

}
