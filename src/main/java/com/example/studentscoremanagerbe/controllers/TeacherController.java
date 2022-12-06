package com.example.studentscoremanagerbe.controllers;

import com.example.studentscoremanagerbe.payload.request.UpdateTeacherRequest;
import com.example.studentscoremanagerbe.services.CustomUserDetailsService;
import com.example.studentscoremanagerbe.services.TeacherService;
import io.sentry.Sentry;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin()
@RestController
@PreAuthorize("hasRole('ROLE_TEACHER')")
@RequestMapping("/api/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @GetMapping("/get-teacher")
    @ApiOperation(value = "05/12/2022 This is get teacher")
    public ResponseEntity<?> getTeacher(Principal principal) {
        try {
            return ResponseEntity.ok(teacherService.getTeacherById(customUserDetailsService
                    .loadUserIdByUsername(principal.getName())));
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }

    @PostMapping("/update-teacher")
    @ApiOperation(value = "06/12/2022 This is update information of teacher")
    public ResponseEntity<?> updateTeacher(@RequestBody UpdateTeacherRequest request){
        try {
            return ResponseEntity.ok(teacherService.updateTeacher(request));
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }
}
