
# RESTful Web 服务

- 传统的 **MVC 模式**开发会直接返回给客户端一个视图
- **RESTful Web 服务**一般会将返回的数据以 JSON 的形式返回，前后端分离开发模式


## 1. RESTful Web 服务的基本概念

- **REST**：Representational State Transfer，表现层状态转化
- **RESTful Web 服务**：符合 REST 设计风格的 Web 服务
- **RESTful Web 服务**的特点：
    - **资源**：RESTful Web 服务的核心是资源，资源是 Web 服务的核心
    - **URI**：每个资源都有一个 URI，通过 URI 来访问资源
    - **表现层**：资源的表现层，一般是 JSON 或者 XML
    - **状态转化**：客户端通过 HTTP 协议来操作资源，对资源的操作包括增删改查
    - **无状态**：客户端操作资源时，不需要保存客户端的状态，每次操作都是独立的，不依赖于之前的操作
    - **统一接口**：客户端和服务端之间的通信，通过 HTTP 协议，客户端通过 HTTP 协议来操作资源，服务端通过 HTTP 协议来返回资源的表现层
    - **缓存**：客户端可以缓存资源的表现层，提高性能
    - **按需编码**：服务端可以根据客户端的需求，返回不同的资源表现层
    - **分层系统**：客户端和服务端之间可以有多个中间层，每个中间层都不知道其他中间层的存在，只知道上一层和下一层

## Demo

### 依赖

1. Spring Boot Web

2. 使用 Lombok 简化代码
```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.10</version>
</dependency>
```

> 学习：[十分钟搞懂Java效率工具Lombok使用与原理](https://mp.weixin.qq.com/s?__biz=Mzg2OTA0Njk0OA==&mid=2247485385&idx=2&sn=a7c3fb4485ffd8c019e5541e9b1580cd&chksm=cea24802f9d5c1144eee0da52cfc0cc5e8ee3590990de3bb642df4d4b2a8cd07f12dd54947b9&token=1381785723&lang=zh_CN#rd)


### 项目结构


项目创建后，在启动类目录下创建 `controller` 和 `entity` 包，分别用于存放控制器和实体类
```shell
├── RestfulwebappApplication.java
├── controller
│   └── BookController.java
└── entity
    └── Book.java
```

书类 `Book.java`

```java
package com.example.restfulwebapp.entity;

public class Book {
    private String name;
    private String description;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
```

控制器 `BookController.java`

```java
package com.example.restfulwebapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.restfulwebapp.entity.Book;

@RestController
@RequestMapping("/api")
public class BookController {
    private List<Book> book_list;

    public List<Book> getBookList() {
        return book_list;
    }

    public void setBookList(List<Book> book_list) {
        this.book_list = book_list;
    }

    public boolean addBook(Book book) {
        if (book.getName() == null || book.getName().equals(""))
            return false;
        else {
            this.book_list.add(book);
            return true;
        }
    }

    public BookController() {
        this.book_list = new ArrayList<Book>();
    }

    /**
     * 添加书籍到书籍列表
     * 
     * @param book 书籍实例
     * @return 书籍列表
     */
    @PostMapping("/book")
    public ResponseEntity<List<Book>> apiAddBook(@RequestBody Book book) {
        if (this.addBook(book))
            return ResponseEntity.ok(this.book_list);
        else
            return ResponseEntity.badRequest().build();
    }

    /**
     * 根据书籍名称查询书籍
     * 
     * @param name
     * @return 书籍列表
     */
    @GetMapping("/book")
    public ResponseEntity<List<Book>> apiGetBookByName(@RequestParam("name") String name) {
        List<Book> results = book_list
                .stream() // 将 list 转换为 stream
                .filter(book -> book.getName().equals(name)) // 过滤出符合条件的元素
                .collect(Collectors.toList());// 将过滤出的元素收集到list中
        return ResponseEntity.ok(results);
    }

    /**
     * 根据书籍id删除书籍
     * 
     * @param id
     * @return
     */
    @DeleteMapping("/book/{id}")
    public ResponseEntity<List<Book>> apiDeleteBookById(@PathVariable("id") int id) {
        book_list.remove(id);
        return ResponseEntity.ok(book_list);
    }

}
```


- `@RestController` 注解表示该类是一个控制器，将**返回的数据以 JSON 的形式写入 HTTP 响应 (Response) 中**。
- `@RequestMapping("/api")` 注解表示该控制器的所有接口都以 `/api` 开头，该注解没有设置请求方法，表示该控制器的所有接口都支持所有的请求方法。
- `@PostMapping("/book")` 注解表示该接口的请求方法是 `POST`，请求路径是 `/api/book`
- `@PostMapping("/book")` 等价于 `@RequestMapping(value = "/book", method = RequestMethod.POST)`
- `@PathVariable` 取 url 地址中的参数
- `@RequestParam` url 的查询参数值
- `@RequestBody` 将请求体 (HttpRequest body) 中的 JSON 类型数据反序列化为合适的 Java 类型。
- `ResponseEntity` 表示 HTTP Response：状态码、响应头、响应体等信息，可以用来自定义 HTTP Response 的内容。


POST 请求

```shell
curl -X POST -H "Content-Type: application/json" -d '{"name":"Java 从入门到放弃", "description":"Java 从入门到放弃"}' http://localhost:8080/api/book
```

GET 请求获取书名为 `Java 从入门到放弃` 的书籍

```shell
curl -X GET http://localhost:8080/api/book?name=Java%20%E4%BB%8E%E5%85%A5%E9%97%A8%E5%88%B0%E6%94%BE%E5%BC%83
```

DELETE 请求删除 id 为 0 的书籍

```shell    
curl -X DELETE http://localhost:8080/api/book/0
```