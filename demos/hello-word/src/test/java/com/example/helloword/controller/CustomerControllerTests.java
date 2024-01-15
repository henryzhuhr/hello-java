package com.example.helloword.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTests {
    @Autowired
    private MockMvc mvc;

    @Test
    public void getIndex() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/hello")// 请求的 url, 请求的方法是 get
                .accept(MediaType.APPLICATION_JSON) // 请求的 accept 类型为 application/json
        )
                .andExpect(status().isOk())// 期望的返回状态为 200
                .andDo(MockMvcResultHandlers.print())// 打印出请求和相应的内容
                .andExpect(content().string(equalTo("Hello Spring Boot")));
    }
}