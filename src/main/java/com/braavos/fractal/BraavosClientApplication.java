package com.braavos.fractal;




import com.braavos.fractal.service.FractalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;


@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class BraavosClientApplication extends SpringBootServletInitializer {

    @Autowired
    FractalService service;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BraavosClientApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(BraavosClientApplication.class, args);
    }

    @PostConstruct
    private void init() {
        service.readRawTransactions();
    }

    @Bean
    public RestTemplate getRestClient() {
        RestTemplate restClient = new RestTemplate();
        return restClient;
    }
}
