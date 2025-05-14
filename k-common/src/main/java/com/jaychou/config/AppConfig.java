package com.jaychou.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: AppConfig
 * Package: com.jaychou.config
 * Description:
 *
 * @Author: 红模仿
 * @Create: 2025/5/13 - 16:19
 * @Version: v1.0
 */
@Configuration
@Data
public class AppConfig {
    @Value("${profect.folder:}")
    private String profectFolder;
    @Value("${admin.account:}")
    private String account;
    @Value("${admin.password:}")
    private String password;
}
