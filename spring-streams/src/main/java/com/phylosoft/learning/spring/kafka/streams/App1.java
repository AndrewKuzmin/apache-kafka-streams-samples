package com.phylosoft.learning.spring.kafka.streams;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Andrew Kuzmin on 11/25/2018.
 */
@SpringBootApplication
public class App1 {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(App1.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(App1.class, args);
    }

}
