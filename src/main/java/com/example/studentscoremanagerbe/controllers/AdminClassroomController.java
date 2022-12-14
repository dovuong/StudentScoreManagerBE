package com.example.studentscoremanagerbe.controllers;

import com.example.studentscoremanagerbe.payload.request.*;
import com.example.studentscoremanagerbe.services.ClassroomService;
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
public class AdminClassroomController {
    @Autowired
    ClassroomService classroomService;
    @PostMapping("/create-classroom")
    @ApiOperation(value = "11/29/2022 This is create new classroom")

    public ResponseEntity<?> createClassroom(@RequestBody ClassroomRequest classroomRequest) {
        MDC.put("requestURL", "api/create-classroom");
        MDC.put("method", "POST");
        try {
            return ResponseEntity.ok(classroomService.createClassRoom(classroomRequest));
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }
    @PutMapping("/update-classroom")
    @ApiOperation(value = "11/29/2022 This is update classroom")

    public ResponseEntity<?> updateClassroom(@RequestBody UpdateClassroomRequest updateclassroomRequest) {
        MDC.put("requestURL", "api/update-classroom");
        MDC.put("method", "PUT");
        try {
            return ResponseEntity.ok(classroomService.updateClassRoom(updateclassroomRequest));
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }
    @PostMapping("/create-list-classroom")
    @ApiOperation(value = "11/29/2022 This is create list classroom")

    public ResponseEntity<?> createListClassroom(@RequestBody ListClassroomRequest listClassroomRequest) {
        MDC.put("requestURL", "api/create-list-classroom");
        MDC.put("method", "POST");
        try {
            return ResponseEntity.ok(classroomService.createListClassRoom(listClassroomRequest));
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }
    @PutMapping("/update-list-classroom")
    @ApiOperation(value = "11/29/2022 This is update list classroom")

    public ResponseEntity<?> updateListClassroom(@RequestBody ListUpdateClassRequest listClassroomRequest) {
        MDC.put("requestURL", "api/update-list-classroom");
        MDC.put("method", "PUT");
        try {
            return ResponseEntity.ok(classroomService.updateListClassRoom(listClassroomRequest));
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }
    @GetMapping("/get-list-classroom")
    @ApiOperation(value = "11/29/2022 This is get list classroom")

    public ResponseEntity<?> getListClassroom() {
        MDC.put("requestURL", "api/get-list-classroom");
        MDC.put("method", "GET");
        try {
            return ResponseEntity.ok(classroomService.getClassRoom());
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }
    @GetMapping("/get-list-classroom-by-faculty/{id}")
    @ApiOperation(value = "11/29/2022 This is get list classroom by faculty")

    public ResponseEntity<?> getListClassroom(@PathVariable int id) {
        MDC.put("requestURL", String.format("api/get-list-classroom-by-faculty/%s" , id));
        MDC.put("method", "GET");
        try {
            return ResponseEntity.ok(classroomService.getClassRoomByFaculty(id));
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }

}
