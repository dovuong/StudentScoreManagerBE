package com.example.studentscoremanagerbe;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableAdminServer
@SpringBootApplication
public class StudentScoreManagerBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentScoreManagerBeApplication.class, args);
    }

}
