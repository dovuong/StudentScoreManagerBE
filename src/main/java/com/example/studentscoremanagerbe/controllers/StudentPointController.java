package com.example.studentscoremanagerbe.controllers;

import com.example.studentscoremanagerbe.payload.request.StudentPointRequest;
import com.example.studentscoremanagerbe.services.StudentPointService;
import io.sentry.Sentry;
import io.swagger.annotations.ApiOperation;
import org.slf4j.MDC;
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
        MDC.put("requestURL",String.format("api/student-point/get-list-points-by-course/%s",id));
        MDC.put("method","GET");
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
        MDC.put("requestURL",String.format("api/student-point/get-point-by-id/%s",id));
        MDC.put("method","GET");
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

        MDC.put("requestURL","api/student-point/create-point");
        MDC.put("method","POST");
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

        MDC.put("requestURL","api/student-point/create-list-point");
        MDC.put("method","POST");
        try {
            return studentPointService.createListPoint(request);
        }
        catch (Exception e){
            Sentry.captureException(e);
            return ResponseEntity.ok(e);
        }
    }

    @PutMapping("/update-point")
    @ApiOperation(value = "05/12/2022 This is update point of student in course ")
    public ResponseEntity<?> updatePoint(@RequestBody StudentPointRequest request){

        MDC.put("requestURL","api/student-point/update-point");
        MDC.put("method","PUT");
        try {
            return studentPointService.updatePoint(request);
        }
        catch (Exception e){
            Sentry.captureException(e);
            return ResponseEntity.ok(e);
        }
    }
}
