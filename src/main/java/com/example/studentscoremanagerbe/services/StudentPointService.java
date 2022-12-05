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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StudentPointService {
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
            return ResponseEntity.ok(studentPointList);
        }
        else return ResponseEntity.ok("Course is not found");
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
                    return ResponseEntity.ok("create point successfully");
                }
                else return ResponseEntity.ok("Point is existed");
            }
            else return ResponseEntity.ok("Student is not existed");
        }
        else return ResponseEntity.ok("Course is not existed");
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
                    return ResponseEntity.ok("update point successfully");
                }
                else return ResponseEntity.ok("Point is not existed");
            }
            else return ResponseEntity.ok("Student is not existed");
        }
        else return ResponseEntity.ok("Course is not existed");
    }

}
