package com.example.hello_java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hello_java.dao.User;

/**
 * UserRepository
 * @see String 是 User 的主键类型
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
}