package com.example.studentscoremanagerbe.controllers;

import com.example.studentscoremanagerbe.payload.request.*;
import com.example.studentscoremanagerbe.services.TeacherService;
import io.sentry.Sentry;
import io.swagger.annotations.ApiOperation;
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
        try {
            return ResponseEntity.ok(teacherService.createTeacher(createTeacherRequest));
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }
    @PostMapping("/update-teacher")
    @ApiOperation(value = "11/30/2022 This is update teacher")

    public ResponseEntity<?> updateTeacher(@RequestBody UpdateTeacherRequest updateTeacherRequest) {
        try {
            return ResponseEntity.ok(teacherService.updateTeacher(updateTeacherRequest));
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }
    @PostMapping("/create-list-teacher")
    @ApiOperation(value = "11/30/2022 This is create list teacher")

    public ResponseEntity<?> createListTeacher(@RequestBody ListTeacherRequest listTeacherRequest)
    {
        try {
            return ResponseEntity.ok(teacherService.createListTeacher(listTeacherRequest));
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }
    @PostMapping("/update-list-teacher")
    @ApiOperation(value = "11/30/2022 This is update list teacher")

    public ResponseEntity<?> updateListTeacher(ListUpdateTeacherRequest listUpdateTeacherRequest)
    {
        try {
            return ResponseEntity.ok(teacherService.updateListTeacher(listUpdateTeacherRequest));
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }
    @PostMapping("/delete-teacher/{id}")
    @ApiOperation(value = "11/30/2022 This is delete list teacher")

    public ResponseEntity<?> deleteTeacher(@PathVariable int id)
    {
        try {
            return ResponseEntity.ok(teacherService.deleteTeacher(id));
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }

}
