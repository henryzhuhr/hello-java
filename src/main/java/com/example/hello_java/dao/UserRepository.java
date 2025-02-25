package com.example.hello_java.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserRepository
 * @see String 是 User 的主键类型
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
}