package com.example.mybatisintegrationdemo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mybatisintegrationdemo.mapper.UserMapper;
import com.example.mybatisintegrationdemo.pojo.User;
import com.example.mybatisintegrationdemo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }
}
