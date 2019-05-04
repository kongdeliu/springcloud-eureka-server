package com.deepexi.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot admin server端。<br/>
 *
 */
@SpringBootApplication
@EnableAdminServer
public class AdminServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminServerApplication.class, args);
        System.out.println("【【【【【【 SpringBoot Admin Server master v0.8 again 1111dddd1】】】】】】已启动.");

    }
}
