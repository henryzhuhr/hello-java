package com.example.readpropertiesdemo;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.example.readpropertiesdemo.config.UserConfig;

@SpringBootApplication
@EnableConfigurationProperties(UserConfig.class)
public class ReadPropertiesDemoApplication implements InitializingBean {

    private UserConfig userConfig;

    public ReadPropertiesDemoApplication(UserConfig userConfig) {
        this.userConfig = userConfig;
    }

    public static void main(String[] args) {
        SpringApplication.run(ReadPropertiesDemoApplication.class, args);
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println(this.userConfig.toString());
    }

}
