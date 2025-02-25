package com.example.hello_java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hello_java.dao.User;
import com.example.hello_java.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * test curl: curl http://localhost:8080/user/hello
     * 
     * @return
     */
    @RequestMapping("/hello")
    public String hello() {
        return "Hello, User!";
    }

    /**
     * test: curl http://localhost:8080/user/1
     * 
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") String id) {
        return userService.getUserById(id);
    }

    /**
     * test: curl -X POST http://localhost:8080/user/add -H "Content-Type:application/json" -d '{"username": "Jane Doe", "email": "jane.doe@example.com","password": "password"}'
     * 
     * @param user
     * @return
     */
    @PostMapping("/user/add")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
        // return true;
    }
}
