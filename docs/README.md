# Java 学习日志

## Java 安装
JDK(Java Development Kit) 是 Java 语言的软件开发工具包(SDK)
- **Java SE** ： standard edition，标准版。包含了基础和核心的库，可用于控制台及桌面应用软件的开发。
- **Java EE** ： enterprise edition，企业版。主要用于企业级分布式网络应用的开发。
- **Java ME** ： micro edition，微型版。主要用于嵌入式设备和移动设备上的应用开发。


[JDK Development Kit](https://www.oracle.com/java/technologies/downloads)

[Java](https://www.oracle.com/java/technologies/downloads/archive/)

### Java 配置环境
#### Linux/MacOS 下环境配置

Linux 下编辑用户级的环境变量文件 `~/.bashrc` ， MacOS 下编辑用户级的环境变量文件 `~/.zshrc`
```sh
# EX: export JAVA_HOME="/usr/lib/jvm/jdk-17"
# EX: export JAVA_HOME="$HOME/program/jdk-21.0.1.jdk/Contents/Home"
export JAVA_HOME=<path_to_your_jdk_path>
export PATH=${JAVA_HOME}/bin
```

#### Windows 下环境配置
- `JAVA_HOME` : `...\Java\jdk-11.0.9` jdk的安装目录
- `CLASSPATH` : `%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%\lib\tools.jar` (JDK1.5以上可以不用配置CLASSPATH变量)
- 添加到Path
  - `%JAVA_HOME%\bin`
  - `%JAVA_HOME%\jre\bin`

测试
```bash
java
javac
java --version
```

### Maven 安装
按照 [maven.apache.org](https://maven.apache.org) 上的说明先进行安装
> 可以通过软件包管理器来安装：MacOS (Homebrew) `brew install maven`、 Ubuntu (apt) `sudo apt-get install maven` 。 Windows(Chocolatey) 运行 `choco install maven` 。

### Gradle
按照 [gradle.org](https://gradle.org) 上的说明进行安装。



### 插件安装
- [Language Support for Java](https://github.com/redhat-developer/vscode-java/wiki/JDK-Requirements#java.configuration.runtimes)

配置不同版本的java
```json
"java.configuration.runtimes": [
  {
    "name": "JavaSE-1.8",
    "path": "/path/to/jdk-8",
  },
  {
    "name": "JavaSE-11",
    "path": "/path/to/jdk-11",
  },
  {
    "name": "JavaSE-16",
    "path": "/path/to/jdk-16",
    "default": true
  },
]
```


- [Debugger for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-debug)

- [Maven for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-maven)

- [Test Runner for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-test)

- [Project Manager for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-dependency)

### 配置

settings.json
```json
{
    "java.home": "path-to-your-jdk"
}
```



## Java 程序执行过程

Java 程序执行过程如下：
```bash
.java (源文件)
  ↓ 编译 javac hello.java
.class (字节码文件)
  ↓ 执行 java HelloWorld
JVM (Java 虚拟机)
```

创建 `hello.java`
```java
class HelloWorld{
	public static void main(String[] args){
		System.out.println("Hello World!");
	}
}
```

使用 `javac` 编译 `hello.java` 生成「**字节码文件**」 `hello.class` 
```bash
javac hello.java
```

使用 `java` 运行 `hello.class` 得到输出
```bash
java HelloWorld
```


## 简单的 SpringBoot 项目

### Spring Initializr 初始化应用程序

![Spring Initializr](./images/spring-initializr-web.png)

访问 [Spring Initializr](https://start.spring.io) 官网，设置项目参数:
- Project: 构建工具，选 Maven，Gradle 比较新，但是 Maven 更加主流、成熟
- Language: 语言，选 Java
- Spring Boot: 版本可以在 [Spring Boot](https://spring.io/projects/spring-boot/#learn) 查看， 其中·`SNAPSHOT` 为快照版，`M` 为里程碑版，`RC` 为候选版，`RELEASE` 为正式版
- Project Metadata: 项目元数据
  - Group: 组名， `com.example`
  - Artifact:项目名， demo
  - Name:demo
  - Description:Demo project for Spring Boot
  - Package name:`com.example.demo`
  - Packaging:Jar
  - Java:17
- Dependencies: 选择项目的附加依赖，添加 Web 依赖：Spring Web、Spring Boot DevTools

> 同样的可以在 VScode 里快速创建，后续如果需要添加依赖，可以在 `pom.xml` 中添加依赖，然后在 VScode 中右键点击 `pom.xml` ，选择 `Add Maven Projects` ，即可添加依赖。

目录结构如下
```bash
-> % tree
.
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
└── src
    ├── main
    │   ├── java/com/example/demo
    │   │   └── DemoApplication.java
    │   └── resources
    │       └── application.properties
    └── test/java/com/example/demo
        └── DemoApplicationTests.java

13 directories, 7 files
```

- `src/main/java` 为项目的主要逻辑代码，`DemoApplication.java` 为项目入口文件
- `src/main/resources` 为项目的资源文件，`application.properties` 为项目的默认配置文件，可以修改为 `application.yml`。书写方式有所不同
- `src/test/java` 为项目的测试代码，`DemoApplicationTests.java` 为项目的测试入口文件

启动项目，进入项目文件夹 `demo` ，执行（或者在 VSCode 中点击 main 函数上的 `run` ）
```bash
mvn spring-boot:run
```
> spring-boot 在这里并不是项目名称。在 `mvn spring-boot:run` 命令中，spring-boot是Maven插件的一部分，而run是该插件的一个目标

启动成功后，访问 `http://localhost:8080` ，看到 `Whitelabel Error Page` 项目启动成功，整个过程不需要关心任何配置，相比 SpringMVC 更加简洁，这就是 SpringBoot **「约定大于配置」** 的强大之处，它让开发者能快速进入工程


`DemoApplication.java` 内容如下
```java
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
```
- `@SpringBootApplication` 负责启动引导应用程序，该注解包含了三个注解
  - `@Configuration`：标记类为应用程序上下文的Bean定义的源。
  - `@EnableAutoConfiguration`：告诉Spring Boot根据添加的jar依赖自动配置你的Spring应用。
  - `@ComponentScan`：让Spring去寻找其他组件、配置和服务，自动扫描包以找到其他的Spring组件。
- `SpringApplication.run(DemoApplication.class, args);` 启动引导应用程序，`DemoApplication.class` 为项目入口文件，`args` 为启动参数

### hello world

> 这里添加了一个依赖 [Hutool](https://github.com/dromara/hutool) ，

添加一个 `sayHello` 方法，访问 `http://localhost:8080` ，看到 `Hello, World!`
```java
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.hutool.core.util.StrUtil;

@SpringBootApplication
@RestController // 使得该类中的方法可以响应 HTTP 请求
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/hello")
	public String sayHello(@RequestParam(required = false, name = "who") String who) {
		if (StrUtil.isBlank(who)) {
			who = "World";
		}
		return StrUtil.format("Hello, {}!", who);
	}
}

```


### Properties 属性配置

> [最全面的SpringBoot配置文件详解](https://zhuanlan.zhihu.com/p/57693064)

默认全局配置文件为 `application.properties` 或者 `application.yml` ，书写方式有所不同，以修改端口为例：
`application.properties`
```properties
server.port=8080
```
`application.yml` 文件
```yaml
server:
  port: 8080
```


Spring Boot 属性配置，有两种方式：
- 通过 `@Value` 注解注入。这种方式适用于单个属性的注入
- 通过 `@ConfigurationProperties` 注解注入。这种方式适用于多个属性的注入，并且可以将属性分组

如果使用 `@Value` 注解，你可以直接在字段上使用这个注解，然后在括号中指定属性的名称
```java
@Component
public class ApplicationProperty {
    @Value("${application.name}")
    private String name;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
```

如果使用 `@ConfigurationProperties` 注解，你需要在 `application.properties` 文件中指定属性的前缀，然后在字段上使用这个注解，括号中指定属性的名称
```java
@ConfigurationProperties(prefix = "developer")
@Component
public class DeveloperProperty {
    private String name;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
```

自定义配置文件时，可以使用 `@Validated` 注解对注入的值进行一些简单的校验


#### 多配置文件

通过可以把一个 `yml` 文档分割为多个，并可以通过 `spring.profiles.active` 属性指定使用哪个配置文件

```yml
# application.yml 文件中
server:
  port: 8081
spring:
  profiles:
    active: prod #指定使用哪个环境
---
# application-dev.yml 文件中
server:
  port: 8080
spring:
  profiles: dev  #指定属于哪个环境
---
# application-prod.yml 文件中
spring:
  profiles: prod  #指定属于哪个环境
```

#### 配置文件加载顺序

Spring Boot 启动时会扫描以下位置的 `application.properties` 或者 `application.yml` 文件作为 Spring Boot 的默认配置文件
- 当前目录下的 `config` 子目录
- 当前目录
- classpath 下的 `config` 包
- classpath 根路径

优先级由高到低，高优先级的配置会覆盖低优先级的配置


### Actuator 应用监控

> 参考 https://zhuanlan.zhihu.com/p/92697416

**spring-boot 集成 spring-boot-starter-actuator 用于监控 spring-boot 的启动和运行状态**，文档详见 [Production-ready Features](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#actuator)

SpringBoot 中的 Actuator 是一个监控和管理生产环境的模块，可以通过 HTTP 或者 JMX 访问监控和管理端点，比如健康 (health) 检查、审计 (auditing)、统计 (metrics)、HTTP 跟踪 (httptrace)、配置属性 (configprops)、环境变量 (env)、日志 (loggers)、线程转储 (dump) 等等。

使用 Spring Boot Actuator 需要加入如下依赖：

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```
Actuator 并没有默认集成在自动配置中，当引入了上面的依赖后，相当于引入了 Actuator 相关的两个项目：
- `spring-boot-actuator-autoconfigure`：自动配置模块
- `spring-boot-actuator`：Actuator 核心模块

由于 SpringBoot Actuator 暴露了大量的监控端点，所以为了保证安全需要引入 Spring Security 依赖，这样在访问监控端点时需要进行认证，否则会出现 401 错误

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```
Spring Security 可以在 application 文件中配置用户名和密码，也可以通过代码配置，这里使用配置文件配置用户名和密码 （测试的时候可以不需要引入）


访问链接 http://localhost:8080/actuator 可以看到如下内容
```json
{"_links":{"self":{"href":"http://localhost:8080/actuator","templated":false},"health":{"href":"http://localhost:8080/actuator/health","templated":false},"health-path":{"href":"http://localhost:8080/actuator/health/{*path}","templated":true}}}
```

默认端点暴露地址（其他端点可以查看 [Endpoints](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#actuator)）：
- `/actuator`：显示所有可用的监控端点
- `/actuator/health`：显示应用的健康信息
- `/health/{component}/{instance}`：显示指定组件的健康信息
- `/health/{component}`：显示指定组件的健康信息
- `/actuator/info`：显示应用的信息

可以在 `application.yml` 文件中配置 Actuator 的端点
```yml
# 若要访问端点信息，需要配置用户名和密码
spring:
  security:
    user:
      name: user
      password: 123456
management:
    # 端点信息接口与应用程序端口分离
    server:
        port: 8081
  endpoints:
    web:
      exposure:
        # 开启全部监控
        include: "*"
        # exclude: health
        # 代表设置监控的基础路径为 `/monitor`，默认为 `/actuator`
        # base-path: /monitor
        
        # 配置跨域
        cors:
            # 设置允许所有域名调用
            allowed-origins: "*"
            allowed-methods: "*"

            # 设置允许来自 `https://www.bing.com` 域的 `GET` 和 `POST` 调用
            # allowed-origins: https://www.bing.com
            # allowed-methods: GET,POST

            allowed-headers: "*"
            allow-credentials: true
  endpoint:
        health:
            # 显示详细的健康信息，"always"可以显示硬盘使用情况和线程情况
            show-details: always
        shutdown:
            # 是否允许远程关闭应用
            enabled: true
```

### Admin 管控台的集成
Admin 管控台 与 Actuator 应用监控互相搭配使用