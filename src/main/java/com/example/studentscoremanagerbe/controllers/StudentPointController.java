package com.example.studentscoremanagerbe.controllers;

import com.example.studentscoremanagerbe.payload.request.CreateStudentPointRequest;
import com.example.studentscoremanagerbe.services.StudentPointService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin()
@RestController
@PreAuthorize("hasRole('ROLE_TEACHER')")
@RequestMapping("/api/student-point")
public class StudentPointController {
    @Autowired
    StudentPointService studentPointService;

    @GetMapping("/get-list-points-by-course/{id}")
    @ApiOperation(value = "12/05/2022 This is get list student's point by course")
    public ResponseEntity<?> getListPointByCourse(@PathVariable @Valid int id){
        try{
            return ResponseEntity.ok(studentPointService.getListPointByCourse(id));
        }
        catch (Exception e){
            return ResponseEntity.ok(e);
        }
    }

    @PostMapping("/create-point")
    @ApiOperation(value = "05/12/2022 This is create point by student in course ")
    public ResponseEntity<?> createPoint(@RequestBody CreateStudentPointRequest request){
        try{
            return ResponseEntity.ok(studentPointService.createPoint(request));
        }
        catch (Exception e){
            return ResponseEntity.ok(e);
        }
    }

    @PostMapping("/update-point")
    @ApiOperation(value = "05/12/2022 This is update point of student in course ")
    public ResponseEntity<?> updatePoint(@RequestBody CreateStudentPointRequest request){
        try{
            return ResponseEntity.ok(studentPointService.updatePoint(request));
        }
        catch (Exception e){
            return ResponseEntity.ok(e);
        }
    }
}
