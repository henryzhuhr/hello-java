package com.example.hello_java.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hello_java.dao.User;
import com.example.hello_java.exception.UserNotFoundException;
import com.example.hello_java.repository.UserRepository;
import com.example.hello_java.service.UserService;

import org.springframework.beans.factory.annotation.Value;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 默认用户名 通过 @Value 注解注入配置文件中的属性
     */
    @Value("${user.default.username}")
    private String defaultUsername;

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("user " + id + " not found"));
    }

    @Override
    public String addUser(User user) {
        // TODO: 需要根据 email 判断是否已经存在
        User existUser = userRepository.findByEmail(user.getEmail());
        if (existUser != null) {
            throw new IllegalStateException("email " + user.getEmail() + " already taken");
        }

        // 当你使用 @GeneratedValue(strategy = GenerationType.UUID) 注解时，Hibernate
        // 会在插入新记录时自动生成一个 UUID 作为主键。这意味着你不需要手动设置 id，Hibernate 会自动处理。
        user.setId(null);
        // TODO: 需要需要处理 uuid 存在的情况

        // 如果用户没有提供 username，则使用默认值
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            user.setUsername(defaultUsername);
        }

        try {
            User insertedUser = userRepository.save(user);
            return insertedUser.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}