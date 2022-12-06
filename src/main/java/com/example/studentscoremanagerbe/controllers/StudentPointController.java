package com.example.studentscoremanagerbe.controllers;

import com.example.studentscoremanagerbe.payload.request.StudentPointRequest;
import com.example.studentscoremanagerbe.services.StudentPointService;
import io.sentry.Sentry;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin()
@RestController
@PreAuthorize("hasRole('ROLE_TEACHER')")
@RequestMapping("/api/student-point")
public class StudentPointController {
    @Autowired
    StudentPointService studentPointService;

    @GetMapping("/get-list-points-by-course/{id}")
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
    @GetMapping("/get-point-by-id/{id}")
    @ApiOperation(value = "05/12/2022 This is get student's point by id")
    public ResponseEntity<?> getPointById(@PathVariable @Valid int id){
        try {
            return studentPointService.getPointById(id);
        }
        catch (Exception e){
            Sentry.captureException(e);
            return ResponseEntity.ok(e);
        }
    }
    @PostMapping("/create-point")
    @ApiOperation(value = "05/12/2022 This is create point by student in course ")
    public ResponseEntity<?> createPoint(@RequestBody StudentPointRequest request){
        try {
            return studentPointService.createPoint(request);
        }
        catch (Exception e){
            Sentry.captureException(e);
            return ResponseEntity.ok(e);
        }
    }

    @PostMapping("/create-list-point")
    @ApiOperation(value = "05/12/2022 This is create point by student in course ")
    public ResponseEntity<?> createListPoint(@RequestBody List<StudentPointRequest> request){
        try {
            return studentPointService.createListPoint(request);
        }
        catch (Exception e){
            Sentry.captureException(e);
            return ResponseEntity.ok(e);
        }
    }

    @PostMapping("/update-point")
    @ApiOperation(value = "05/12/2022 This is update point of student in course ")
    public ResponseEntity<?> updatePoint(@RequestBody StudentPointRequest request){
        try {
            return studentPointService.updatePoint(request);
        }
        catch (Exception e){
            Sentry.captureException(e);
            return ResponseEntity.ok(e);
        }
    }
}
