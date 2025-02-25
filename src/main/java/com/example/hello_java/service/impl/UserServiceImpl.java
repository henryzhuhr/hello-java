package com.example.hello_java.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hello_java.dao.User;
import com.example.hello_java.dao.UserRepository;
import com.example.hello_java.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public User addUser(User user) {
        // 当你使用 @GeneratedValue(strategy = GenerationType.UUID) 注解时，Hibernate
        // 会在插入新记录时自动生成一个 UUID 作为主键。这意味着你不需要手动设置 id，Hibernate 会自动处理。
        user.setId(null);
        // TODO: 需要需要处理 uuid 存在的情况
        // TODO: 需要根据 email 判断是否已经存在
        
        try {
            User insertedUser = userRepository.save(user);
            return insertedUser;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}