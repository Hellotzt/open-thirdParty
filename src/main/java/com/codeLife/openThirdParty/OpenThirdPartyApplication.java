package com.codeLife.openThirdParty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * CodeLife-开放第三方平台
 */
@EnableFeignClients
@SpringBootApplication
public class OpenThirdPartyApplication {
    public static void main(String[] args) {
        SpringApplication.run(OpenThirdPartyApplication.class, args);
    }
}