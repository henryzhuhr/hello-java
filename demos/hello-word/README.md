

## 初始化项目目录
```sh
-> % tree
.
├── HelloWordApplication.java
├── controller
│   └── CustomerController.java
├── model
│   ├── Customer.java
│   └── CustomerRepository.java
└── service
    └── CustomerService.java

4 directories, 5 files
```

1、Application.java 建议放到根目录下面,主要用于做一些框架配置
2、model 目录主要用于实体与数据访问层（Repository）
3、service 层主要是业务类代码
4、controller 负责页面访问控制


## 引入 web 模块

### 1. pom.xml 中添加支持web的模块

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
pom.xml 文件中默认有两个模块：

- `spring-boot-starter` ：核心模块，包括自动配置支持、日志和 YAML，如果引入了 `spring-boot-starter-web` web 模块可以去掉此配置，因为 `spring-boot-starter-web` 自动依赖了 `spring-boot-starter`。
- `spring-boot-starter-test` ：测试模块，包括 JUnit、Hamcrest、Mockito。

### 2. 编写 Controller 内容：
```java
@RestController
public class HelloWorldController {
    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }
}
```
`@RestController` 的意思就是 Controller 里面的方法都以 json 格式输出，不用再写什么 jackjson 配置

启动主程序，打开浏览器访问 http://localhost:8080/hello，就可以看到效果了

## 单元测试

打开的 `src/test/` 下的测试入口，编写简单的 http 请求来测试；使用 mockmvc 进行，利用 `MockMvcResultHandlers.print()` 打印出执行结果。
```java
@RunWith(SpringRunner.class)
@WebMvcTest(HelloWorldController.class)
public class HelloTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/hello")
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(content()
                                .string(equalTo("Hello World")));
    }

}
```
开发环境的调试

热启动在正常开发项目中已经很常见了吧，虽然平时开发web项目过程中，改动项目启重启总是报错；但springBoot对调试支持很好，修改之后可以实时生效，需要添加以下的配置：
```xml
 <dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>

<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                <fork>true</fork>
            </configuration>
        </plugin>
</plugins>
</build>
```
该模块在完整的打包环境下运行的时候会被禁用。如果你使用 java -jar 启动应用或者用一个特定的 classloader 启动，它会认为这是一个“生产环境”。
