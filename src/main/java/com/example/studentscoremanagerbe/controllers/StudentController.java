package com.example.studentscoremanagerbe.controllers;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin()
@RestController
@RequestMapping("/api")
@Api(tags = "Student")
public class StudentController {
    Logger logger = LoggerFactory.getLogger(StudentController.class);
}
