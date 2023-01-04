package com.customermanagement.cmbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CmBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmBackendApplication.class, args);
        /*SpringApplication application = new SpringApplication(CmBackendApplication.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);*/
    }

}
