package com.example.hello_java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hello_java.dao.User;
import com.example.hello_java.exception.UserNotFoundException;
import com.example.hello_java.response.ApiResponse;
import com.example.hello_java.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * test: curl http://localhost:8080/user/hello
     * 
     * @return
     */
    @RequestMapping("/hello")
    public String hello() {
        return "Hello, User!";
    }

    /**
     * test: curl http://localhost:8080/user/47bf8a21-9e95-40c9-a3b4-8eecc455f90d
     * 
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable("id") String id) {
        User user=userService.getUserById(id);
        if (user == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(HttpStatus.NOT_FOUND.value(), "User not found"));
        } else {
            return ResponseEntity.ok(ApiResponse.success(user));
        }
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
