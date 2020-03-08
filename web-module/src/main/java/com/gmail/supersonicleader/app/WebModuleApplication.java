package com.gmail.supersonicleader.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.gmail.supersonicleader.service.impl",
        "com.gmail.supersonicleader.repository.impl",
        "com.gmail.supersonicleader.app.controller"
})
public class WebModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebModuleApplication.class, args);
    }

}
