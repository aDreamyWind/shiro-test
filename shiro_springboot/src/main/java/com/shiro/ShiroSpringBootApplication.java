package com.shiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 小韩
 * @Description
 * @Date 2023/2/20 20:56
 */
@SpringBootApplication
@MapperScan("com.shiro.mapper")
public class ShiroSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShiroSpringBootApplication.class, args);
    }
}
