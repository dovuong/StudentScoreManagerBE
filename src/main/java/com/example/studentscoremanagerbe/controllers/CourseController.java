package com.example.studentscoremanagerbe.controllers;

import com.example.studentscoremanagerbe.payload.request.CreateCourseRequest;
import com.example.studentscoremanagerbe.payload.request.UpdateCourseRequest;
import com.example.studentscoremanagerbe.repositories.UserRepository;
import com.example.studentscoremanagerbe.services.CourseService;
import com.example.studentscoremanagerbe.services.CustomUserDetailsService;
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
@RequestMapping("/api/course")
public class CourseController {
    @Autowired
    CourseService courseService;
    @Autowired
    CustomUserDetailsService customUserDetailsService;


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/get-course")
    @ApiOperation(value = "12/05/2022 This is get getAllCourse")
    public ResponseEntity<?> getCourse() {
        MDC.put("requestURL", "api/course/get-course");
        MDC.put("method", "GET");
        try {
            return ResponseEntity.ok(courseService.getAllCourse());
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @GetMapping("/get-course-teacher")
    @ApiOperation(value = "12/05/2022 This is get getCourseByTeacher")
    public ResponseEntity<?> getCourseByTeacher(Principal principal) {
        MDC.put("requestURL", "api/course/get-course-teacher");
        MDC.put("method", "GET");
        try {
            return ResponseEntity.ok(courseService
                    .getAllCourseByTeacherId
                            (customUserDetailsService
                            .loadUserIdByUsername(principal.getName())));
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/get-course-teacher/{id}")
    @ApiOperation(value = "12/05/2022 This is get getCourseByTeacherId")
    public ResponseEntity<?> getCourseByTeacherId(@PathVariable int id) {
        MDC.put("requestURL", String.format("api/course/get-course-teacher/%s", id));
        MDC.put("method", "GET");
        try {
            return ResponseEntity.ok(courseService
                    .getAllCourseByTeacherId(id));
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/get-course-by-subject/{id}")
    @ApiOperation(value = "12/05/2022 This is get getAllCourse")
    public ResponseEntity<?> getCourseBySubject(@PathVariable int id) {
        MDC.put("requestURL", String.format("api/course/get-course-by-subject/%s" , id));
        MDC.put("method", "GET");
        try {
            return ResponseEntity.ok(courseService.getAllCourseBySubjectId(id));
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create-course")
    @ApiOperation(value = "12/05/2022 This is get getAllCourse")
    public ResponseEntity<?> createCourse(@RequestBody CreateCourseRequest createCourseRequest) {
        MDC.put("requestURL", "api/course/create-course");
        MDC.put("method", "POST");
        try {
            return ResponseEntity.ok(courseService.createCourse(createCourseRequest));
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update-course")
    @ApiOperation(value = "12/05/2022 This is get getAllCourse")
    public ResponseEntity<?> updateCourse(@RequestBody UpdateCourseRequest updateCourseRequest) {
        MDC.put("requestURL", "api/course/update-course");
        MDC.put("method", "PUT");
        try {
            return ResponseEntity.ok(courseService.updateCourse(updateCourseRequest));
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }

}
