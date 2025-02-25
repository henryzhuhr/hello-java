package com.example.hello_java.service;

import com.example.hello_java.dao.User;

public interface UserService {
    /**
     * 根据id获取用户
     * 
     * @param id
     * @return
     */
    public User getUserById(String id);

    public User addUser(User user);
}
