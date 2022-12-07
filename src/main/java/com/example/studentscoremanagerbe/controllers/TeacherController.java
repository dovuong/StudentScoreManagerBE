package com.example.studentscoremanagerbe.controllers;

import com.example.studentscoremanagerbe.payload.request.UpdateTeacherRequest;
import com.example.studentscoremanagerbe.services.CustomUserDetailsService;
import com.example.studentscoremanagerbe.services.TeacherService;
import io.sentry.Sentry;
import io.swagger.annotations.ApiOperation;
import org.slf4j.MDC;
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
        MDC.put("requestURL", "api/teacher/get-teacher");
        MDC.put("method", "GET");
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
        MDC.put("requestURL", "api/teacher/update-teacher");
        MDC.put("method", "POST");
        try {
            return ResponseEntity.ok(teacherService.updateProfile(request));
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }
}
