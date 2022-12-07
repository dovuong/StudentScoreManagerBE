package com.example.studentscoremanagerbe.services;

import com.example.studentscoremanagerbe.model.User;
import com.example.studentscoremanagerbe.payload.request.*;
import com.example.studentscoremanagerbe.repositories.RoleRepository;
import com.example.studentscoremanagerbe.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j

public class TeacherService {
    Logger logger = LoggerFactory.getLogger(ClassroomService.class);
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    public ResponseEntity<?> getTeacher() {
        List<User> users = userRepository.findAll();
        if (users == null) {
            logger.error("Get all teacher failed. Cause by list teacher are not found");
            MDC.clear();
            return ResponseEntity.ok("List teacher empty");
        } else {
            logger.info("Get all teacher successfully ");
            MDC.clear();
            return ResponseEntity.ok(users);
        }
    }
    public ResponseEntity<?> getTeacherByUsername(String username) {
        User users = userRepository.findUserByUsername(username);
        if (users == null) {
            logger.error("Get teacher failed. Cause by user name = '{}' are not found" + username);
            MDC.clear();
            return ResponseEntity.ok("Teacher empty");
        } else {
            logger.info("Get teacher name ='{}' successfully " + username);
            MDC.clear();
            return ResponseEntity.ok(users);
        }
    }

    public ResponseEntity<?> createTeacher(CreateTeacherRequest createTeacherRequest) {
        User user = userRepository.findUserByUsername(createTeacherRequest.getNumberPhone());
        if (user != null) {
            logger.error(" Create teacher failed. Cause by user name ='{}' exist" + createTeacherRequest.getNumberPhone());
            MDC.clear();
            return ResponseEntity.ok("teacher exist");
        } else {
            User user1 = new User();
            user1.setRole(roleRepository.findRoleById(2));
            user1.setName(createTeacherRequest.getName());
            user1.setBirthday(createTeacherRequest.getBirthday());
            user1.setStatus(true);
            user1.setNumberPhone(createTeacherRequest.getNumberPhone());
            user1.setUsername(createTeacherRequest.getNumberPhone());
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String pass = encoder.encode(createTeacherRequest.getNumberPhone());
            user1.setPassword(pass);
            userRepository.save(user1);
            logger.info("Create teacher with user name = '{}' successfully", user1.getName());
            MDC.clear();
            return ResponseEntity.ok("Create success");
        }
    }
    public ResponseEntity<?> updateTeacher(UpdateTeacherRequest updateTeacherRequest) {
        User user = userRepository.findUserById(updateTeacherRequest.getIdTeacher());
        if (user == null) {
            logger.error("Update teacher failed. Cause by teacher id ='{}' are not found" + updateTeacherRequest.getIdTeacher());
            MDC.clear();
            return ResponseEntity.ok("teacher empty");
        } else {
           User user1 =  userRepository.findUserByUsername(updateTeacherRequest.getNumberPhone());
            if (user1 == null || user.getNumberPhone().equals(updateTeacherRequest.getNumberPhone())) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                String pass = encoder.encode(updateTeacherRequest.getNumberPhone());
                user.setPassword(pass);
                user.setName(updateTeacherRequest.getName());
                user.setBirthday(updateTeacherRequest.getBirthday());
                user.setUsername(updateTeacherRequest.getNumberPhone());
                user.setNumberPhone(updateTeacherRequest.getNumberPhone());
                userRepository.save(user);
                logger.info("Update teacher id = '{}'", user.getId());
                MDC.clear();
                return ResponseEntity.ok("update success");
            }
            return ResponseEntity.ok("teacher doesn't exist!");
        }
    }
    public ResponseEntity<?> createListTeacher(ListTeacherRequest listTeacherRequest) {
        for (CreateTeacherRequest i: listTeacherRequest.getTeacherRequests()) {
            return ResponseEntity.ok(createTeacher(i));
        }
        logger.error("Create list teacher failed. Cause by list request empty");
        MDC.clear();
        return ResponseEntity.ok("List teacher empty");
    }
    public ResponseEntity<?> updateListTeacher(ListUpdateTeacherRequest listUpdateTeacherRequest) {
        logger.error("Update list teacher.");
        MDC.clear();
        for (UpdateTeacherRequest i: listUpdateTeacherRequest.getTeacherRequestList()) {
            return ResponseEntity.ok(updateTeacher(i));
        }
        logger.error("Update list teacher failed. Cause by list request");
        MDC.clear();
        return ResponseEntity.ok("List teacher empty");
    }
    public ResponseEntity<?> deleteTeacher(int idTeacher) {
       User user = userRepository.findUserById(idTeacher);
       if (user == null) {
           logger.error("Delete teacher failed. Cause by teacher id ='{}' not found" + idTeacher);
           MDC.clear();
           return ResponseEntity.ok("teacher empty");
       } else {
           user.setStatus(false);
           userRepository.save(user);
           logger.info("Delete teacher id = '{}' successfully", idTeacher);
           MDC.clear();
           return  ResponseEntity.ok("Delete success");
       }
    }

    public ResponseEntity<?> getTeacherById(int id) {
        User user = userRepository.findUserById(id);
        if (user != null) {
            logger.info(String.format("Get teacher id = %s successfully", id));
            MDC.clear();
            return ResponseEntity.ok(user);
        } else {
            logger.error("Get teacher failed. Cause by teacher id = '{}' is not found" + id);
            MDC.clear();
            return ResponseEntity.ok("get teacher failed");
        }
    }




}
