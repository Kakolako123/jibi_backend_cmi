package com.example.jibi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;*/

//@EnableWebSecurity
@SpringBootApplication
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class JibiApplication {

    public static void main(String[] args) {
        SpringApplication.run(JibiApplication.class, args);
    }

}
