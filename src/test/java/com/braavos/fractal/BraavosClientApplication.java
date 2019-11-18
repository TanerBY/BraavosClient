package com.braavos.fractal;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class BraavosClientApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(com.braavos.fractal.BraavosClientApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(com.braavos.fractal.BraavosClientApplication.class, args);
    }

    @Bean
    public RestTemplate getRestClient() {
        RestTemplate restClient = new RestTemplate();
        return restClient;
    }
}
