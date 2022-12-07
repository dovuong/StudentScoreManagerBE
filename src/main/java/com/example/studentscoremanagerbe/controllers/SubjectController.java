package com.example.studentscoremanagerbe.controllers;

import com.example.studentscoremanagerbe.payload.request.CreateSubjectRequest;
import com.example.studentscoremanagerbe.payload.request.UpdateSubjectRequest;
import com.example.studentscoremanagerbe.repositories.SubjectRepository;
import com.example.studentscoremanagerbe.services.SubjectService;
import io.sentry.Sentry;
import io.swagger.annotations.ApiOperation;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin()
@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/api/subject")
public class SubjectController {

    @Autowired
    SubjectService subjectService;
    @GetMapping("/get-all-subject")
    @ApiOperation(value = "12/05/2022 This is get getAllCourse")
    public ResponseEntity<?> getSubject() {
        MDC.put("requestURL","api/subject/get-all-subject");
        MDC.put("method","GET");
        try {
            return ResponseEntity.ok(subjectService.getAllSubject());
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }
    @PostMapping("/create-subject")
    @ApiOperation(value = "12/05/2022 This is get getAllCourse")
    public ResponseEntity<?> createSubject(@RequestBody CreateSubjectRequest createSubjectRequest) {
        MDC.put("requestURL","api/subject/create-subject");
        MDC.put("method","POST");
        try {
            return ResponseEntity.ok(subjectService.createSubject(createSubjectRequest));
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }
    @PutMapping("/update-subject")
    @ApiOperation(value = "12/05/2022 This is get getAllCourse")
    public ResponseEntity<?> updateSubject(@RequestBody UpdateSubjectRequest updateSubjectRequest) {
        MDC.put("requestURL","api/subject/update-subject");
        MDC.put("method","PUT");
        try {
            return ResponseEntity.ok(subjectService.updateSubject(updateSubjectRequest));
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok().body(e);
        }
    }

}
