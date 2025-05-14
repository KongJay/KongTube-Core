package com.jaychou.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * ClassName: KongHubAdminRunApplication
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
@EnableScheduling
public class KongHubAdminRunApplication {
    public static void main(String[] args) {
        SpringApplication.run(KongHubAdminRunApplication.class, args);
    }
}
