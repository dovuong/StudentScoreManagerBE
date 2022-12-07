package com.example.studentscoremanagerbe.services;

import com.example.studentscoremanagerbe.model.Faculty;
import com.example.studentscoremanagerbe.payload.request.FacultyRequest;
import com.example.studentscoremanagerbe.repositories.FacultyRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

/**
 * Some javadoc. // OK
 *
 * @author Vuong
 * @since 20/11/2022
 * @deprecated Some javadoc.
 */
@Service
@Slf4j

public class FacultyService {
    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    @Autowired
    FacultyRepository facultyRepository;

    public ResponseEntity<?> getAllFaculty(){
        List<Faculty> listFaculty = facultyRepository.findAll();
        if (listFaculty != null){
            logger.info("Get all faculties successfully ");
            MDC.clear();
            return ResponseEntity.ok(listFaculty);
        }

        logger.error("Get all faculties failed. Cause by list faculties are not found");
        MDC.clear();
        return ResponseEntity.ok("Faculties empty");


    }

    public ResponseEntity<?> getFacultyById(int id) {
        Faculty faculty = facultyRepository.findById(id);
        MDC.clear();
        if (faculty != null){
            logger.info("Get faculty id = '{}'", id);
            return ResponseEntity.ok(faculty);
        }

        logger.error("Get faculty failed. Cause by faculty id = '{}' is not found", id);
        MDC.clear();
        return ResponseEntity.ok("Faculty empty");


    }
    public Faculty getFacultyById1(int id) {
        Faculty faculty = facultyRepository.findById(id);
        if (faculty != null){
            logger.info("Get faculty id = '{}'", id);
            MDC.clear();
            return faculty;
        }

        logger.error("Get faculty failed. Cause by faculty id = '{}' is not found", id);
        MDC.clear();
        return null;


    }
    public ResponseEntity<?> createFaculty(FacultyRequest facultyRequest) {
        Faculty faculty = facultyRepository.findByName(facultyRequest.getName());
        if (faculty == null){
            Faculty newFaculty = new Faculty();
            newFaculty.setName(facultyRequest.getName());
            facultyRepository.save(newFaculty);
            logger.info("Create faculty name = '{}'", facultyRequest.getName());
            MDC.clear();
            return ResponseEntity.ok("create new faculty successfully");
        }
        logger.error("Create faculty failed. Cause by faculty name = '{}' is existed", facultyRequest.getName());
        MDC.clear();
        return ResponseEntity.ok("Faculty existed");

    }
}
