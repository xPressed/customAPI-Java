package ru.xpressed.customapijava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class CustomApiJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomApiJavaApplication.class, args);
    }

}
