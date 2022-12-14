package com.example.studentscoremanagerbe.controllers;

import com.example.studentscoremanagerbe.payload.request.*;
import com.example.studentscoremanagerbe.services.TeacherService;
import io.sentry.Sentry;
import io.swagger.annotations.ApiOperation;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin()
@RestController
@RequestMapping("/api")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminTeacherController {
    @Autowired
    TeacherService teacherService;
    @GetMapping("/get-teacher")
    @ApiOperation(value = "11/30/2022 This is get teacher")

    public ResponseEntity<?> getTeacher() {
        MDC.put("requestURL", "api/get-teacher");
        MDC.put("method", "GET");
        try {
            return ResponseEntity.ok(teacherService.getTeacher());
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }
    @GetMapping("/get-teacher-by-username/{username}")
    @ApiOperation(value = "11/30/2022 This is get teacher by username")

    public ResponseEntity<?> getTeacherByUserName(@PathVariable String username) {
        MDC.put("requestURL", String.format("api/get-teacher-by-username/%s" , username));
        MDC.put("method", "GET");
        try {
            return ResponseEntity.ok(teacherService.getTeacherByUsername(username));
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }
    @PostMapping("/create-teacher")
    @ApiOperation(value = "11/30/2022 This is create teacher")

    public ResponseEntity<?> createTeacher(@RequestBody CreateTeacherRequest createTeacherRequest) {
        MDC.put("requestURL", "api/create-teacher");
        MDC.put("method", "POST");
        try {
            return ResponseEntity.ok(teacherService.createTeacher(createTeacherRequest));
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }
    @PutMapping("/update-teacher")
    @ApiOperation(value = "11/30/2022 This is update teacher")

    public ResponseEntity<?> updateTeacher(@RequestBody UpdateTeacherRequest updateTeacherRequest) {
        MDC.put("requestURL", "api/update-teacher");
        MDC.put("method", "PUT");
        try {
            return ResponseEntity.ok(teacherService.updateTeacher(updateTeacherRequest));
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }
    @PostMapping("/create-list-teacher")
    @ApiOperation(value = "11/30/2022 This is create list teacher")

    public ResponseEntity<?> createListTeacher(@RequestBody ListTeacherRequest listTeacherRequest) {
        MDC.put("requestURL", "api/create-list-teacher");
        MDC.put("method", "POST");
        try {
            return ResponseEntity.ok(teacherService.createListTeacher(listTeacherRequest));
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }
    @PutMapping("/update-list-teacher")
    @ApiOperation(value = "11/30/2022 This is update list teacher")

    public ResponseEntity<?> updateListTeacher(ListUpdateTeacherRequest listUpdateTeacherRequest) {
        MDC.put("requestURL", "api/update-list-teacher");
        MDC.put("method", "PUT");
        try {
            return ResponseEntity.ok(teacherService.updateListTeacher(listUpdateTeacherRequest));
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }
    @DeleteMapping("/delete-teacher/{id}")
    @ApiOperation(value = "11/30/2022 This is delete list teacher")

    public ResponseEntity<?> deleteTeacher(@PathVariable int id) {
        MDC.put("requestURL", String.format("api/delete-teacher/%s" , id));
        MDC.put("method", "DELETE");
        try {
            return ResponseEntity.ok(teacherService.deleteTeacher(id));
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }

}
