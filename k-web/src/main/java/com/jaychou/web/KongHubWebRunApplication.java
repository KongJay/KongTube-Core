package com.jaychou.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * ClassName: KongHubWebRunApplication
 * Package: com.jaychou
 * Description:
 *
 * @Author: 红模仿
 * @Create: 2025/5/7 - 20:27
 * @Version: v1.0
 */
@SpringBootApplication(scanBasePackages = {"com.jaychou"})
@MapperScan("com.jaychou.mappers")
@EnableTransactionManagement
public class KongHubWebRunApplication {
    public static void main(String[] args) {
        SpringApplication.run(KongHubWebRunApplication.class, args);
    }
}
