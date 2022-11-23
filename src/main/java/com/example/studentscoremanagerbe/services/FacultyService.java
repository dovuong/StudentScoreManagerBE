package com.example.studentscoremanagerbe.services;

import com.example.studentscoremanagerbe.model.Faculty;
import com.example.studentscoremanagerbe.payload.request.FacultyRequest;
import com.example.studentscoremanagerbe.repositories.FacultyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class FacultyService {
    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    @Autowired
    FacultyRepository facultyRepository;

    public ResponseEntity<?> getAllFaculty(){
        List<Faculty> listFaculty = facultyRepository.findAll();
        if (listFaculty != null){
            logger.info("Get all faculties ");
            return ResponseEntity.ok(listFaculty);
        }
        else {
            logger.error("List faculties are not found");
            return ResponseEntity.ok("Faculties empty");
        }

    }

    public ResponseEntity<?> getFacultyById(int id)
    {
        Faculty faculty= facultyRepository.findById(id);
        if (faculty != null){
            logger.info("Get faculty id = {}",id);
            return ResponseEntity.ok(faculty);
        }
        else {
            logger.error("Faculty id = {} is not found",id);
            return ResponseEntity.ok("Faculty empty");
        }

    }
    public ResponseEntity<?> createFaculty(FacultyRequest facultyRequest)
    {
        Faculty faculty = facultyRepository.findByName(facultyRequest.getName());
        if (faculty == null){
            Faculty newFaculty = new Faculty();
            Date createDate = new Date();
            Date delDate = null;
            newFaculty.setName(facultyRequest.getName());
            newFaculty.setCreateAt(createDate);
            newFaculty.setDeletedAt(delDate);
            newFaculty.setUpdateAt(delDate);
            facultyRepository.save(newFaculty);
            logger.info("Create faculty name = '{}'", facultyRequest.getName());
            return ResponseEntity.ok("create new faculty successfully");

        }
        else{
            logger.error("Faculty name = {} is existed", facultyRequest.getName());
            return ResponseEntity.ok("Faculty existed");
        }
    }
}
