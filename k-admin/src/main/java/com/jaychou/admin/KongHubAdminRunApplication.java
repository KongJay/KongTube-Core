package com.jaychou.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * ClassName: KongHubAdminRunApplication
 * Package: com.jaychou
 * Description:
 *
 * @Author: 红模仿
 * @Create: 2025/5/7 - 20:27
 * @Version: v1.0
 */
@SpringBootApplication(scanBasePackages = {"com.jaychou.admin"})
public class KongHubAdminRunApplication {
    public static void main(String[] args) {
        SpringApplication.run(KongHubAdminRunApplication.class, args);
    }
}
