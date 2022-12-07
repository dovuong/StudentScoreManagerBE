package com.example.studentscoremanagerbe.controllers;

import com.example.studentscoremanagerbe.payload.request.*;
import com.example.studentscoremanagerbe.services.StudentService;
import io.sentry.Sentry;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin()
@RestController
@RequestMapping("/api")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminStudentController {
    Logger logger = LoggerFactory.getLogger(AdminStudentController.class);
    @Autowired
    StudentService studentService;
    @PostMapping("/create-student")
    @ApiOperation(value = "11/29/2022 This is create new student")

    public ResponseEntity<?> createStudent(@RequestBody StudentRequest studentRequest) {
        MDC.put("requestURL","api/create-student");
        MDC.put("method","POST");
        try {
            return ResponseEntity.ok(studentService.createStudent(studentRequest));
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }
    @PostMapping("/create-list-student")
    @ApiOperation(value = "11/29/2022 This is create new list student")

    public ResponseEntity<?> createListStudent(@RequestBody ListStudentRequest listStudentRequest) {
        MDC.put("requestURL","api/create-list-student");
        MDC.put("method","POST");
        try {
            return ResponseEntity.ok(studentService.createListStudent(listStudentRequest));
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }
    @PutMapping("/update-list-student")
    @ApiOperation(value = "11/29/2022 This is update list student")

    public ResponseEntity<?> updateListStudent(@RequestBody ListUpdateStudentRequest listStudentRequest) {
        MDC.put("requestURL","api/update-list-student");
        MDC.put("method","PUT");
        try {
            return ResponseEntity.ok(studentService.updateListStudent(listStudentRequest));
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }
    @PutMapping("/update-student")
    @ApiOperation(value = "11/29/2022 This is update student")

    public ResponseEntity<?> updateStudent(@RequestBody UpdateStudentRequest updateStudentRequest) {
        MDC.put("requestURL","api/update-student");
        MDC.put("method","PUT");
        try {
            return ResponseEntity.ok(studentService.updateStudent(updateStudentRequest));
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }
    @DeleteMapping("/delete-student")
    @ApiOperation(value = "11/29/2022 This is delete student")

    public ResponseEntity<?> deleteStudent(@RequestBody DeleteStudentRequest deleteStudentRequest) {
        MDC.put("requestURL","api/delete-student");
        MDC.put("method","DELETE");
        try {
            return ResponseEntity.ok(studentService.deleteStudent(deleteStudentRequest));
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }
    @DeleteMapping("/delete-list-student")
    @ApiOperation(value = "11/29/2022 This is delete list student")

    public ResponseEntity<?> deleteListStudent(@RequestBody DeleteListStudentRequest deleteListStudentRequest) {
        MDC.put("requestURL","api/delete-list-student");
        MDC.put("method","DELETE");
        try {
            return ResponseEntity.ok(studentService.DeleteListStudent(deleteListStudentRequest));
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }
    @GetMapping("/get-list-student")
    @ApiOperation(value = "11/29/2022 This is get list student")

    public ResponseEntity<?> getListStudent() {
        MDC.put("requestURL","api/get-list-student");
        MDC.put("method","GET");
        try {
            return ResponseEntity.ok(studentService.getStudent());
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }
    @GetMapping("/get-list-student-by-class/{id}")
    @ApiOperation(value = "11/29/2022 This is get list student by class")

    public ResponseEntity<?> getListStudentByClass(@PathVariable int id) {
        MDC.put("requestURL",String.format("api/get-list-student-by-class/%s",id));
        MDC.put("method","GET");
        try {
            return ResponseEntity.ok(studentService.getStudentByClassroom(id));
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }
}
