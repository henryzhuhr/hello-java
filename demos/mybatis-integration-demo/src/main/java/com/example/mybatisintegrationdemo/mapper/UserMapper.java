package com.example.mybatisintegrationdemo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.mybatisintegrationdemo.pojo.User;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE id = #{id}")
    public User findById(Integer id);
}
