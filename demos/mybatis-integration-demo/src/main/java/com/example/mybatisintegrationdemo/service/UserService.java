package com.example.mybatisintegrationdemo.service;

import com.example.mybatisintegrationdemo.pojo.User;

public interface UserService {
    public User findById(Integer id);
}
