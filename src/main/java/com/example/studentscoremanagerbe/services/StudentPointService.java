package com.example.studentscoremanagerbe.services;

import com.example.studentscoremanagerbe.model.Course;
import com.example.studentscoremanagerbe.model.Student;
import com.example.studentscoremanagerbe.model.StudentPoint;
import com.example.studentscoremanagerbe.model.User;
import com.example.studentscoremanagerbe.payload.request.CreateStudentPointRequest;
import com.example.studentscoremanagerbe.repositories.CourseRepository;
import com.example.studentscoremanagerbe.repositories.StudentPointRepository;
import com.example.studentscoremanagerbe.repositories.StudentRepository;
import com.example.studentscoremanagerbe.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StudentPointService {
    Logger logger = LoggerFactory.getLogger(StudentPointService.class);

    @Autowired
    StudentPointRepository studentPointRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    public ResponseEntity<?> getListPointByCourse( int couseId){
        Course course = courseRepository.findById(couseId);
        if(course != null){
            List<StudentPoint> studentPointList = studentPointRepository.findAllByCourseId(couseId);
            logger.info(String.format("Get list student's point by course id= %s", couseId));
            return ResponseEntity.ok(studentPointList);
        }
        else{
            logger.error("Get list student's point failed. Cause by course not found");
            return ResponseEntity.ok("Course is not found");
        }
    }

    public ResponseEntity<?> getPointById(int id){
        StudentPoint point = studentPointRepository.findStudentPointById(id);
        if(point != null){
            logger.info(String.format("Get student's point id = %s ", id));
            return ResponseEntity.ok(point);
        }
        else{
            logger.error("Get student's point failed. Cause by point is not existed");
            return ResponseEntity.ok("Get student's point failed");
        }
    }

    public ResponseEntity<?> createPoint(CreateStudentPointRequest request){
        Course course = courseRepository.findById(request.getCourse_id());
        Student student = studentRepository.findStudentById(request.getStudent_id());
        if(course != null){
            if (student != null){
                StudentPoint studentPoint = studentPointRepository.findStudentPointByCourse_IdAndAndStudentId(request.getCourse_id(), request.getStudent_id());
                if (studentPoint ==null){
                    StudentPoint point = new StudentPoint();
                    point.setPoint(request.getPoint());
                    point.setStudent(student);
                    point.setCourse(course);
                    point.setCreatedAt(new Date());
                    studentPointRepository.save(point);
                    logger.info("Create student's point successfully");
                    return ResponseEntity.ok("create point successfully");
                }
                else {
                    logger.error("Create student's point failed. Cause by point is existed");
                    return ResponseEntity.ok("Point is existed");
                }
            }
            else {
                logger.error("Create student's point failed. Cause by student is not existed");
                return ResponseEntity.ok("Student is not existed");
            }
        }
        else {
            logger.error("Create student's point failed. Cause by course is not existed");
            return ResponseEntity.ok("Course is not existed");
        }
    }
    public ResponseEntity<?> updatePoint(CreateStudentPointRequest request){
        Course course = courseRepository.findById(request.getCourse_id());
        Student student = studentRepository.findStudentById(request.getStudent_id());
        if(course != null){
            if (student != null){
                StudentPoint point = studentPointRepository.findStudentPointByCourse_IdAndAndStudentId(request.getCourse_id(), request.getStudent_id());
                if (point !=null){
                    point.setPoint(request.getPoint());
                    point.setStudent(student);
                    point.setCourse(course);
                    point.setUpdatedAt(new Date());
                    studentPointRepository.save(point);
                    logger.info(String.format("Update student's point id = %s successfully", point.getId()));
                    return ResponseEntity.ok("update point successfully");
                }
                else {
                    logger.error("Update student's point failed. Cause by point is not existed");
                    return ResponseEntity.ok("Point is not existed");
                }
            }
            else {
                logger.error("Update student's point failed. Cause by student is not existed");
                return ResponseEntity.ok("Student is not existed");
            }
        }
        else {
            logger.error("Update student's point failed. Cause by course is not existed");
            return ResponseEntity.ok("Course is not existed");
        }
    }

}
