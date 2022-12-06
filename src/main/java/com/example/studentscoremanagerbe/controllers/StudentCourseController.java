package com.example.studentscoremanagerbe.controllers;

import com.example.studentscoremanagerbe.payload.request.StudentCourseRequest;
import com.example.studentscoremanagerbe.payload.request.StudentPointRequest;
import com.example.studentscoremanagerbe.services.StudentPointService;
import io.sentry.Sentry;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin()
@RestController
@RequestMapping("/api/student-course")
@Api(tags = "student-course")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class StudentCourseController {
    Logger logger = LoggerFactory.getLogger(StudentCourseController.class);

    @Autowired
    StudentPointService studentPointService;

    // thiếu sentry,

    @GetMapping("/get-list-student-course-by-course/{id}")
    @ApiOperation(value = "05/12/2022 This is get list student's point by course")
    public ResponseEntity<?> getListPointByCourse(@PathVariable @Valid int id){
        try {
            return studentPointService.getListPointByCourse(id);
        }
        catch (Exception e){
            Sentry.captureException(e);
            return ResponseEntity.ok(e);
        }
    }
    @GetMapping("/get-student-course-by-id/{id}")
//    @ApiOperation(value = "05/12/2022 This is get student's point by id")
    public ResponseEntity<?> getPointById(@PathVariable @Valid int id){
        try {
            return studentPointService.getPointById(id);
        }
        catch (Exception e){
            Sentry.captureException(e);
            return ResponseEntity.ok(e);
        }
    }
    @PostMapping("/add-student-course")
//    @ApiOperation(value = "05/12/2022 This is create point by student in course ")
    public ResponseEntity<?> createPoint(@RequestBody StudentCourseRequest request){
        try {
            StudentPointRequest studentPointRequest = new StudentPointRequest();
            studentPointRequest.setStudent_id(request.getStudent_id());
            studentPointRequest.setPoint("");
            studentPointRequest.setCourse_id(request.getCourse_id());
            return studentPointService.createPoint(studentPointRequest);
        }
        catch (Exception e){
            Sentry.captureException(e);
            return ResponseEntity.ok(e);
        }
    }

    @PostMapping("/update-student-course")
    public ResponseEntity<?> updatePoint(@RequestBody StudentCourseRequest request){
        try {
            StudentPointRequest studentPointRequest = new StudentPointRequest();
            studentPointRequest.setStudent_id(request.getStudent_id());
            studentPointRequest.setPoint("");
            studentPointRequest.setCourse_id(request.getCourse_id());
            return studentPointService.updatePoint(studentPointRequest);
        }
        catch (Exception e){
            Sentry.captureException(e);
            return ResponseEntity.ok(e);
        }
    }
}