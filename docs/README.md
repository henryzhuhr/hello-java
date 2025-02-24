# Java 学习日志

## Java 安装
JDK(Java Development Kit) 是 Java 语言的软件开发工具包(SDK)
- **Java SE** ： standard edition，标准版。包含了基础和核心的库，可用于控制台及桌面应用软件的开发。
- **Java EE** ： enterprise edition，企业版。主要用于企业级分布式网络应用的开发。
- **Java ME** ： micro edition，微型版。主要用于嵌入式设备和移动设备上的应用开发。


[JDK Development Kit](https://www.oracle.com/java/technologies/downloads)

[Java](https://www.oracle.com/java/technologies/downloads/archive/)

参考 [*如何在 Ubuntu 24.04 LTS 上安装 Java - 系统极客*](https://www.sysgeek.cn/install-java-ubuntu/)

### Java 配置环境
#### Linux/MacOS 下环境配置

Linux 下编辑用户级的环境变量文件 `~/.bashrc` ， MacOS下下编辑用户级的环境变量文件 `~/.zshrc`
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
- Spring Boot: 版本
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
- `src/main/resources` 为项目的资源文件，`application.properties` 为项目的配置文件
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


### 修改端口号

修改默认端口号，修改 `application.properties` 文件，并重启项目
```properties
server.port=8081
```
如果你使用的是 `application.yml` 文件，修改如下
```yaml
server:
  port: 8081
```

