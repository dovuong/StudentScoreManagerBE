package com.example.studentscoremanagerbe.services;

import com.example.studentscoremanagerbe.model.Subject;
import com.example.studentscoremanagerbe.payload.request.CreateSubjectRequest;
import com.example.studentscoremanagerbe.payload.request.UpdateSubjectRequest;
import com.example.studentscoremanagerbe.repositories.SubjectRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j

public class SubjectService {
    private final Logger logger = LoggerFactory.getLogger(SubjectService.class);

    @Autowired
    SubjectRepository subjectRepository;
    public Subject createSubject(CreateSubjectRequest createSubjectRequest) {
        Subject subject = new Subject();
        subject.setName(createSubjectRequest.getName());
        subject.setCreatedAt(new Date());
        logger.info("create new subject " + createSubjectRequest.getName());
        return subjectRepository.save(subject);
    }
    public List<Subject> getAllSubject() {
        List<Subject> subjects = subjectRepository.findAll();
        logger.info("get all subject");
        return subjects;
    }
    public Subject updateSubject(UpdateSubjectRequest updateSubjectRequest) {
        Subject subject = subjectRepository.findById(updateSubjectRequest.getSubjectId()).get();
        subject.setName(updateSubjectRequest.getName());
        logger.info("update new subjectId " + updateSubjectRequest.getSubjectId());
        return subjectRepository.save(subject);
    }



}
