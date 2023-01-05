package com.habibian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.File;

import static com.habibian.constant.FileConstant.USER_FOLDER;

@SpringBootApplication
public class EslAppSpringBootRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EslAppSpringBootRestApiApplication.class, args);

        new File(USER_FOLDER).mkdirs();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
