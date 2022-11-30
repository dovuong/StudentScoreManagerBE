package com.example.studentscoremanagerbe.services;

import com.example.studentscoremanagerbe.model.ClassRoom;
import com.example.studentscoremanagerbe.model.Student;
import com.example.studentscoremanagerbe.model.User;
import com.example.studentscoremanagerbe.payload.request.*;
import com.example.studentscoremanagerbe.repositories.RoleRepository;
import com.example.studentscoremanagerbe.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    Logger logger = LoggerFactory.getLogger(ClassroomService.class);
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    public ResponseEntity<?> getTeacher()
    {
        List<User> users = userRepository.findAll();
        if (users == null)
        {
            logger.error("Get all teacher failed. Cause by list teacher are not found");
            return ResponseEntity.ok("List teacher empty");
        }
        else
        {
            logger.info("Get all teacher successfully ");
            return ResponseEntity.ok(users);
        }
    }
    public ResponseEntity<?> getTeacherByUsername(String username)
    {
        User users = userRepository.findUserByUsername(username);
        if (users == null)
        {
            logger.error("Get teacher failed. Cause by teacher are not found");
            return ResponseEntity.ok("Teacher empty");
        }
        else
        {
            logger.info("Get teacher successfully ");
            return ResponseEntity.ok(users);
        }
    }

    public ResponseEntity<?> createTeacher(CreateTeacherRequest createTeacherRequest)
    {
        User user = userRepository.findUserByUsername(createTeacherRequest.getNumberPhone());
        if (user != null)
        {
            logger.error(" create failed. Cause by teacher exist");
            return ResponseEntity.ok("teacher exist");
        }
        else
        {
            User user1 = new User();
            user1.setRole(roleRepository.findRoleById(2));
            user1.setName(createTeacherRequest.getName());
            user1.setBirthday(createTeacherRequest.getBirthday());
            user1.setStatus(true);
            user1.setNumberPhone(createTeacherRequest.getNumberPhone());
            user1.setUsername(createTeacherRequest.getNumberPhone());
            user1.setPassword(createTeacherRequest.getNumberPhone());
            userRepository.save(user1);
            logger.info("Create student name = '{}'", user1.getName());
            return ResponseEntity.ok("Create success");
        }
    }
    public ResponseEntity<?> updateTeacher(UpdateTeacherRequest updateTeacherRequest)
    {
        User user = userRepository.findUserById(updateTeacherRequest.getIdTeacher());
        if (user == null)
        {
            logger.error("update failed. Cause by teacher are not found");
            return ResponseEntity.ok("teacher empty");
        }
        else
        {
           User user1 =  userRepository.findUserByUsername(updateTeacherRequest.getNumberPhone());
            if (user1 == null || user1.getNumberPhone().equals(updateTeacherRequest.getNumberPhone())) {
                user.setPassword(updateTeacherRequest.getPassword());
                user.setName(updateTeacherRequest.getName());
                user.setBirthday(updateTeacherRequest.getBirthday());
                userRepository.save(user);
                logger.info("update student name = '{}'", user.getName());
                return ResponseEntity.ok("update success");
            }
            return ResponseEntity.ok("teacher doesn't exist!");
        }
    }
    public ResponseEntity<?> createListTeacher(ListTeacherRequest listTeacherRequest)
    {
        for (CreateTeacherRequest i: listTeacherRequest.getTeacherRequests())
        {
            return ResponseEntity.ok(createTeacher(i));
        }
        logger.error("List teacher empty");
        return ResponseEntity.ok("List teacher empty");
    }
    public ResponseEntity<?> updateListTeacher(ListUpdateTeacherRequest listUpdateTeacherRequest)
    {

        for (UpdateTeacherRequest i: listUpdateTeacherRequest.getTeacherRequestList())
        {
            return ResponseEntity.ok(updateTeacher(i));
        }
        logger.error("List teacher empty");
        return ResponseEntity.ok("List teacher empty");
    }
    public ResponseEntity<?> deleteTeacher(int idTeacher)
    {
       User user = userRepository.findUserById(idTeacher);
       if (user == null)
       {
           logger.error(" teacher empty");
           return ResponseEntity.ok("teacher empty");
       }
       else
       {
           user.setStatus(false);
           userRepository.save(user);
           logger.info("delete student name = '{}'", user.getName());
           return  ResponseEntity.ok("Delete success");
       }
    }


}
