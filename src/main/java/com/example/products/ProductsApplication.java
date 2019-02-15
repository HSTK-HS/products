package com.example.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.products"})
public class ProductsApplication extends SpringBootServletInitializer {

    //用war包本地tomcat部署  要继承SpringBootServletInitializer
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ProductsApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductsApplication.class, args);
    }
}
