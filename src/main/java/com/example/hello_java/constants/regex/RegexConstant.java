package com.example.hello_java.constants.regex;

/**
 * 正则表达式常量
 */
public interface  RegexConstant {
    interface Common{
        /**
         * 邮箱  正则表达式
         */
        // String EMAIL="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        String EMAIL = "^$|^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        // String EMAIL="^$|^\\w+([\\+\\.-]\\w+)*@\\w+([\\.-]\\w+)*\\.\\w+([\\.-]\\w+)*$";
    }
    interface User{
        /**
         * 用户名正则表达式：支持中文字符、英文字母、数字、短线、下划线和点，不能超过95个字符
         */
        String USERNAME="^[-A-Za-z0-9()（）_.\\u4e00-\\u9fa5]{1,95}$";
    }
}
