package com.example.studentscoremanagerbe.controllers;

import com.example.studentscoremanagerbe.payload.request.ClassroomRequest;
import com.example.studentscoremanagerbe.payload.request.FacultyRequest;
import com.example.studentscoremanagerbe.payload.request.ListClassroomRequest;
import com.example.studentscoremanagerbe.payload.request.UpdateClassroomRequest;
import com.example.studentscoremanagerbe.services.ClassroomService;
import io.sentry.Sentry;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin()
@RestController
@RequestMapping("/api")
public class ClassroomController {
    @Autowired
    ClassroomService classroomService;
    @PostMapping("/create-classroom")
    @ApiOperation(value = "11/29/2022 This is create new faculty")

    public ResponseEntity<?> createClassroom(@RequestBody ClassroomRequest classroomRequest) {
        try {
            return ResponseEntity.ok(classroomService.createClassRoom(classroomRequest));
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }
    @PostMapping("/update-classroom")
    @ApiOperation(value = "11/29/2022 This is create new faculty")

    public ResponseEntity<?> updateClassroom(@RequestBody UpdateClassroomRequest updateclassroomRequest) {
        try {
            return ResponseEntity.ok(classroomService.updateClassRoom(updateclassroomRequest));
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }
    @PostMapping("/create-list-classroom")
    @ApiOperation(value = "11/29/2022 This is create new faculty")

    public ResponseEntity<?> createListClassroom(@RequestBody ListClassroomRequest listClassroomRequest) {
        try {
            return ResponseEntity.ok(classroomService.createListClassRoom(listClassroomRequest));
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }
    @PostMapping("/create-list-classroom")
    @ApiOperation(value = "11/29/2022 This is create new faculty")

    public ResponseEntity<?> updateListClassroom(@RequestBody ListClassroomRequest listClassroomRequest) {
        try {
            return ResponseEntity.ok(classroomService.updateListClassRoom(listClassroomRequest));
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }
    @GetMapping("/get-list-classroom")
    @ApiOperation(value = "11/29/2022 This is create new faculty")

    public ResponseEntity<?> getListClassroom() {
        try {
            return ResponseEntity.ok(classroomService.getClassRoom());
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }
    @GetMapping("/get-list-classroom-by-faculty/{id}")
    @ApiOperation(value = "11/29/2022 This is create new faculty")

    public ResponseEntity<?> getListClassroom(@PathVariable int id) {
        try {
            return ResponseEntity.ok(classroomService.getClassRoomByFaculty(id));
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }

}
