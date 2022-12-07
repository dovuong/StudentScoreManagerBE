package com.example.studentscoremanagerbe.controllers;

import com.example.studentscoremanagerbe.payload.request.FacultyRequest;
import com.example.studentscoremanagerbe.services.FacultyService;
import io.sentry.Sentry;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
/**
 * Some javadoc. // OK
 *
 * @author Linh
 * @since 20/11/2022
 * @deprecated Some javadoc.
 */
@CrossOrigin()
@RestController
@RequestMapping("/api")
@Api (tags = "Faculty")
public class FacultyController {
    Logger logger = LoggerFactory.getLogger(FacultyController.class);

    @Autowired
    FacultyService facultyService;

    @GetMapping("/all-faculty")
    @ApiOperation(value = "11/23/2022 This is get all faculties")
    public ResponseEntity<?> getAllFaculty() {
        MDC.put("requestURL","api/all-faculty");
        MDC.put("method","GET");
        try {
            return ResponseEntity.ok().body(facultyService.getAllFaculty());
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }

    @GetMapping("/get-faculty-by-id/{id}")
    @ApiOperation(value = "11/23/2022 This is get faculty by id")

    public ResponseEntity<?> getFacultyById(@PathVariable @Valid int id) {
        MDC.put("requestURL",String.format("api/get-faculty-by-id/%s",id));
        MDC.put("method","GET");
        try {
            return ResponseEntity.ok().body(facultyService.getFacultyById(id));
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }

    @PostMapping("/create-faculty")
    @ApiOperation(value = "11/23/2022 This is create new faculty")

    public ResponseEntity<?> createFaculty(@RequestBody FacultyRequest facultyRequest) {
        MDC.put("requestURL","api/create-faculty");
        MDC.put("method","POST");
        try {
            return ResponseEntity.ok(facultyService.createFaculty(facultyRequest));
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }
}
