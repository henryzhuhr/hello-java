package com.example.hello_java.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hello_java.dao.User;
import com.example.hello_java.exception.UserNotFoundException;
import com.example.hello_java.repository.UserRepository;
import com.example.hello_java.service.UserService;

import org.springframework.beans.factory.annotation.Value;

@Service
public class UserServiceImpl implements UserService {

    /**
     * private static final 声明的日志记录器的原因：
     * 1. 线程安全性
     * - static:
     * 使日志记录器成为类级别的变量，而不是实例级别的变量。这意味着所有该类的实例共享同一个日志记录器实例，从而避免了多个日志记录器实例的开销和潜在的混乱。
     * - final : 确保日志记录器实例一旦初始化后就不能被重新赋值。这有助于保持日志记录器的一致性和稳定性，避免在运行时意外更改日志记录器实例。
     * 2. 性能优化
     * - 创建日志记录器实例是一个相对耗时的操作。通过将日志记录器声明为 static，可以确保每个类只有一个日志记录器实例，减少了不必要的对象创建和内存消耗。
     * 3. 代码清晰性和一致性
     * - 使用 private static final 是一种常见的最佳实践，使得日志记录器的声明和使用更加统一和清晰。
     * - private 修饰符确保日志记录器只能在当前类内部访问，避免外部类对日志记录器进行不必要的操作或修改。
     * 4. 避免误用
     * - 通过将日志记录器声明为 final，可以防止在代码的其他部分意外地重新赋值或修改日志记录器实例，从而减少潜在的错误和维护难度。
     */
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

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
            logger.error(" email " + user.getEmail() + " already taken");
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
            logger.info("User inserted: " + insertedUser);
            return insertedUser.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}