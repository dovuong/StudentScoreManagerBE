package com.example.studentscoremanagerbe.services;

import com.example.studentscoremanagerbe.model.Course;
import com.example.studentscoremanagerbe.model.Student;
import com.example.studentscoremanagerbe.model.StudentPoint;
import com.example.studentscoremanagerbe.payload.request.StudentPointRequest;
import com.example.studentscoremanagerbe.payload.response.StudentPointResponse;
import com.example.studentscoremanagerbe.repositories.CourseRepository;
import com.example.studentscoremanagerbe.repositories.StudentPointRepository;
import com.example.studentscoremanagerbe.repositories.StudentRepository;
import com.example.studentscoremanagerbe.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j

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

    public ResponseEntity<?> getListPointByCourse(int courseId){
        Course course = courseRepository.findById(courseId);
        if (course != null) {
            List<StudentPointResponse> studentPointResponses = new ArrayList<>();
            List<StudentPoint> studentPointList = studentPointRepository.findAllByCourseId(courseId);
            logger.info(String.format("Get list student's point by course id= '%s'", courseId));
            MDC.clear();
            int totalStudent = studentPointList.size();

            for (StudentPoint studentPoint: studentPointList) {
                studentPointResponses.add(new StudentPointResponse(studentPoint, totalStudent));
            }

            logger.info(String.format("Get list student's point by course id= '%s'", courseId));
            MDC.clear();
            return ResponseEntity.ok(studentPointResponses);

        }
        else {

            logger.error("Get list student's point failed. Cause by course id '{}' not found" + courseId);
            MDC.clear();
            return ResponseEntity.ok("Course is not found");
        }
    }

    public ResponseEntity<?> getPointById(int id){
        StudentPoint point = studentPointRepository.findStudentPointById(id);
        if (point != null) {
            logger.info(String.format("Get student's point id = '%s' ", id));
            MDC.clear();
            return ResponseEntity.ok(new StudentPointResponse(point, 0));
        }
        else {
            logger.error("Get student's point failed. Cause by student's point id= '{}' not found" + id);
            MDC.clear();
            return ResponseEntity.ok("Get student's point failed");
        }
    }

    public ResponseEntity<?> createPoint(StudentPointRequest request){
        Course course = courseRepository.findById(request.getCourse_id());
        Student student = studentRepository.findStudentById(request.getStudent_id());
        if (course != null) {
            if (student != null){
                StudentPoint studentPoint = studentPointRepository.findStudentPointByCourse_IdAndAndStudentId(request.getCourse_id(), request.getStudent_id());
                if (studentPoint == null){
                    StudentPoint point = new StudentPoint();
                    point.setPoint(request.getPoint());
                    point.setStudent(student);
                    point.setCourse(course);
                    point.setCreatedAt(new Date());
                    studentPointRepository.save(point);
                    logger.info("Create point of student id = '{}' successfully" + student.getId());
                    MDC.clear();
                    return ResponseEntity.ok("create point successfully");
                }
                else {
                    logger.error("Create point of student id = '{}' failed. Cause by point is existed" + student.getId());
                    MDC.clear();
                    return ResponseEntity.ok("Point is existed");
                }
            }
            else {
                logger.error("Create student's point failed. Cause by student id = '{}' is not existed", student.getId());
                MDC.clear();
                return ResponseEntity.ok("Student is not existed");
            }
        }
        else {
            logger.error("Create student's point failed. Cause by course id = '{}' is not existed", course.getId());
            MDC.clear();
            return ResponseEntity.ok("Course is not existed");
        }
    }
    public ResponseEntity<?> updatePoint(StudentPointRequest request){
        Course course = courseRepository.findById(request.getCourse_id());
        Student student = studentRepository.findStudentById(request.getStudent_id());
        if (course != null) {
            if (student != null) {
                StudentPoint point = studentPointRepository.findStudentPointByCourse_IdAndAndStudentId(request.getCourse_id(), request.getStudent_id());
                if (point != null) {
                    point.setPoint(request.getPoint());
                    point.setStudent(student);
                    point.setCourse(course);
                    point.setUpdatedAt(new Date());
                    studentPointRepository.save(point);
                    logger.info(String.format("Update student's point id = '%s' successfully", point.getId()));
                    MDC.clear();
                    return ResponseEntity.ok("update point successfully");
                }
                else {
                    logger.error("Update student's point failed. Cause by point id = '{}' is not existed", point.getId());
                    MDC.clear();
                    return ResponseEntity.ok("Point is not existed");
                }
            }
            else {
                logger.error("Update student's point failed. Cause by student id ='{}' is not existed", student.getId());
                MDC.clear();
                return ResponseEntity.ok("Student is not existed");
            }
        }
        else {
            logger.error("Update student's point failed. Cause by course id ='{}' is not existed" , course.getId());
            MDC.clear();
            return ResponseEntity.ok("Course is not existed");
        }
    }
    public ResponseEntity<?> createListPoint(List<StudentPointRequest> requestList){
        logger.info("Create list student's point");
        MDC.clear();
        for (StudentPointRequest request: requestList) {
            Course course = courseRepository.findById(request.getCourse_id());
            Student student = studentRepository.findStudentById(request.getStudent_id());
            if (course != null) {
                if (student != null) {
                    StudentPoint studentPoint = studentPointRepository.findStudentPointByCourse_IdAndAndStudentId(request.getCourse_id(), request.getStudent_id());
                    if (studentPoint == null) {
                        StudentPoint point = new StudentPoint();
                        point.setPoint(request.getPoint());
                        point.setStudent(student);
                        point.setCourse(course);
                        point.setCreatedAt(new Date());
                        studentPointRepository.save(point);
                        logger.info("Create student's point successfully");
                        MDC.clear();
                    }
                    else {
                        logger.error("Create student's point failed. Cause by point id ='{}' is existed" + studentPoint.getId());
                        MDC.clear();
                        return ResponseEntity.ok("Point is existed");
                    }
                }
                else {

                    logger.error("Create student's point failed. Cause by student id = '{}' is not existed" + student.getId());
                    MDC.clear();
                    return ResponseEntity.ok("Student is not existed");
                }
            }
            else {
                logger.error("Create student's point failed. Cause by course id= '{}' is not existed" + course.getId());
                MDC.clear();
                return ResponseEntity.ok("Course is not existed");
            }
        }
        return ResponseEntity.ok("create list point successfully");

    }



}
