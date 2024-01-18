package com.example.helloword.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Email;

// Java Persistence API
/**
 * 作为规范，Java Persistence API关注持久性，它将Java对象的创建过程和具体的创建形式解耦。
 * 并非所有Java对象都需要持久化，但大多数应用程序都会保留关键业务对象。
 * JPA规范允许您定义应该保留哪些对象，以及如何在Java应用程序中保留这些对象。
 * 
 *  对象持久化就是把结构化数据存储起来的过程，并且可以从存储文件恢复为原来的结构化数据。
 * 
 * 对象持久化是指将内存中的对象保存到可永久保存的存储设备中（如磁盘）的一种技术
 * 
 * 1。这样做的目的是使数据可以长期存在。在JAVA中，我们可以把JAVA对象直接保存在文件中，在需要使用的时候，
 * 直接从文件中读取，这也是对象持久化的一种方式
 * 
 * 2。总的来说，对象持久化就是把结构化数据存储起来的过程，并且可以从存储文件恢复为原来的结构化数据
 * 
 */
import jakarta.persistence.Column;
import jakarta.persistence.Entity; // from JPA
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity // 只要是在 @Entity 的实体里面被注解标注的字段，都会被映射到数据库
public class User {
    @Getter
    @Setter
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    @Getter
    @Setter
    private String userName;

    @Column(nullable = false)
    @Getter
    @Setter
    private String passWord;

    @Email
    @Column(nullable = false, unique = true)
    @Getter
    @Setter
    private String email;

    @Column(nullable = true, unique = true, length = 20)
    @Getter
    @Setter
    private String nickName; // 昵称

    @Column(nullable = false)
    @Getter
    @Setter
    private String registrationTime; // 注册时间

    public User() {
        super();
    }
}
