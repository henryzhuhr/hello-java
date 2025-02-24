package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Result;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @apiNote curl -X POST http://localhost:8080/user/register -d '{"name": "admin"}'
     * 
     * @param user
     * @return
     */
    @PostMapping("/register")
    public Result<Void> register(User user) {
        User u = userService.findUserByName(user.getName());
        if (u != null) {
            return Result.error(500, "用户名已存在");
        }
        return Result.success();
    }
}
