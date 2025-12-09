package com.example.hello_java.constants.regex;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class RegexConstantTest {

    @Test
    void testEmailRegex() {
        String emailRegex = RegexConstant.Common.EMAIL;
        Pattern pattern = Pattern.compile(emailRegex);

        // Valid emails
        assertTrue(pattern.matcher("test@example.com").matches(), "简单 email 应该匹配");
        assertTrue(pattern.matcher("test123@example.com").matches(), "简单 email 应该匹配");
        assertTrue(pattern.matcher("user.name@domain.co").matches(), "带点的 email 应该匹配");
        assertTrue(pattern.matcher("user123.name@domain.co").matches(), "带点的 email 应该匹配");
        assertTrue(pattern.matcher("user+name@domain.com").matches(), "带加号的 email 应该匹配");
        assertTrue(pattern.matcher("user123+name@domain.com").matches(), "带加号的 email 应该匹配");
        assertTrue(pattern.matcher("user_name@domain.com").matches(), "带下划线的 email 应该匹配");
        assertTrue(pattern.matcher("user123_name@domain.com").matches(), "带下划线的 email 应该匹配");
        assertTrue(pattern.matcher("123@domain.com").matches(), "带数字的 email 应该匹配");
        assertTrue(pattern.matcher("user@domain.museum").matches(), "长 TLD 应该匹配"); // .museum 是合法 TLD（7 字母）
        assertTrue(pattern.matcher("user@mail.google.com").matches(), "多级子域名应匹配");
        assertTrue(pattern.matcher("user@sub1.sub2.domain.co.uk").matches(), "多级子域名 + 多段 TLD 应匹配");

        // Invalid emails
        assertFalse(pattern.matcher("plainaddress").matches(), "普通文本不应该匹配");
        assertFalse(pattern.matcher("@example.com").matches(), "缺少用户名不应该匹配");
        assertFalse(pattern.matcher("user@").matches(), "缺少域名不应该匹配");
        assertFalse(pattern.matcher("user@.com").matches(), "缺少域名名称不应该匹配");
        assertFalse(pattern.matcher("user@domain").matches(), "缺少顶级域名不应该匹配");
        assertFalse(pattern.matcher("user@domain.").matches(), "顶级域名后缺少内容不应该匹配");
        // assertFalse(pattern.matcher("user@domain.a").matches(), "单字母 TLD 不应匹配（IANA 不分配）");
        assertFalse(pattern.matcher("user..name@domain.com").matches(), "连续点不应匹配");
        assertFalse(pattern.matcher("user--name@domain.com").matches(), "连续短横线不应匹配（域名中允许，但本地部分通常不允许）");
        assertFalse(pattern.matcher("user++name@domain.com").matches(), "连续加号不应匹配");
        // 如果你的正则不支持中文/Unicode，应拒绝
        assertFalse(pattern.matcher("用户@例子.中国").matches(), "中文邮箱应被拒绝（除非显式支持 IDN）");
        assertFalse(pattern.matcher("user@domain.côm").matches(), "带重音符号的域名应被拒绝");

        // 明确允许/禁止的符号
        // assertTrue(pattern.matcher("user.name-123_").matches(), "混合符号结尾应匹配（如果规则允许）");
        assertFalse(pattern.matcher("user$name").matches(), "包含 $ 的用户名不应匹配");
        assertFalse(pattern.matcher("user!name").matches(), "包含 ! 的用户名不应匹配");
        assertFalse(pattern.matcher("user(name)").matches(), "包含括号的用户名不应匹配");
    }

    @Test
    void testUsernameRegex() {
        String usernameRegex = RegexConstant.User.USERNAME;
        Pattern pattern = Pattern.compile(usernameRegex);

        // Valid usernames
        assertTrue(pattern.matcher("username").matches(), "简单用户名应该匹配");
        assertTrue(pattern.matcher("user123").matches(), "包含字母和数字的用户名应该匹配");
        assertTrue(pattern.matcher("user_name").matches(), "包含下划线的用户名应该匹配");
        assertTrue(pattern.matcher("user-name").matches(), "包含短线的用户名应该匹配");
        assertTrue(pattern.matcher("user.name").matches(), "包含点的用户名应该匹配");
        assertTrue(pattern.matcher("中文用户名").matches(), "包含中文字符的用户名应该匹配");
        assertTrue(pattern.matcher("user.name_123-中文").matches(), "包含混合字符的用户名应该匹配");
        assertTrue(pattern.matcher("user.name_123-中文").matches(), "包含混合字符的用户名应该匹配");

        // Boundary checks
        assertTrue(pattern.matcher("a").matches(), "1 个字符应该匹配");
        assertTrue(pattern.matcher("a".repeat(95)).matches(), "95 个字符应该匹配");

        // Invalid usernames
        assertFalse(pattern.matcher("").matches(), "空用户名不应该匹配");
        assertFalse(pattern.matcher("user@name").matches(), "包含 @ 的用户名不应该匹配");
        assertFalse(pattern.matcher("user name").matches(), "包含空格的用户名不应该匹配");
        assertFalse(pattern.matcher("a".repeat(96)).matches(), "96 个字符不应该匹配");
    }
}
