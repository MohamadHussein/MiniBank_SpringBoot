package com.finance.minibank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class MiniBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniBankApplication.class, args);

    }

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/accounts").allowedOrigins("*");
//                registry.addMapping("/accounts/**").allowedOrigins("*");
//                registry.addMapping("/accounts").allowedMethods("*");
//
//                registry.addMapping("/transactions").allowedOrigins("*");
//                registry.addMapping("/transactions/**").allowedOrigins("*");
//                registry.addMapping("/transactions").allowedMethods("*");
//
//                registry.addMapping("/customers").allowedOrigins("*");
//                registry.addMapping("/customers/**").allowedOrigins("*");
//                registry.addMapping("/customers").allowedMethods("*");
//            }
//        };
//    }
//    @Configuration
//    @EnableWebMvc
//    public class WebConfig implements WebMvcConfigurer {
//
//        @Override
//        public void configurePathMatch(PathMatchConfigurer configurer) {
//            configurer.setUseTrailingSlashMatch(true);
//            }
//        }
    }
