package ru.xpressed.customapijava;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "CustomAPI-Java", version = "2.2.8", contact = @Contact(name = "xPressed", url = "https://github.com/xPressed/customAPI-Java")))
public class CustomApiJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomApiJavaApplication.class, args);
    }

}
