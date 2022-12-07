package com.example.studentscoremanagerbe.services;

import com.example.studentscoremanagerbe.model.Course;
import com.example.studentscoremanagerbe.model.Subject;
import com.example.studentscoremanagerbe.model.User;
import com.example.studentscoremanagerbe.payload.request.CreateCourseRequest;
import com.example.studentscoremanagerbe.payload.request.UpdateCourseRequest;
import com.example.studentscoremanagerbe.repositories.CourseRepository;
import com.example.studentscoremanagerbe.repositories.SubjectRepository;
import com.example.studentscoremanagerbe.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j

public class CourseService {
    private final Logger logger = LoggerFactory.getLogger(SubjectService.class);
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SubjectRepository subjectRepository;
    // create course mapping teacher and subject
    public Course createCourse(CreateCourseRequest createCourseRequest) {
        Course course = new Course();
        Subject subject = subjectRepository.findById(createCourseRequest.getSubjectId()).get();
        User user = userRepository.findUserById(createCourseRequest.getTeacherId());
        course.setName(createCourseRequest.getName());
        course.setCreatedAt(new Date());
        course.setSubject(subject);
        course.setTeacher(user);
        logger.info(String.format("Create new course name = '%s' by admin successfully",createCourseRequest.getName()));
        MDC.clear();
        return courseRepository.save(course);
    }
    public Course updateCourse(UpdateCourseRequest updateCourseRequest) {
        Course course = courseRepository.findById(updateCourseRequest.getCourseId()).get();
        User user = userRepository.findUserById(updateCourseRequest.getTeacherId());
        course.setName(updateCourseRequest.getName());
        course.setUpdatedAt(new Date());
        course.setTeacher(user);
        logger.info(String.format("Update course id = '%s' by admin successfully ",updateCourseRequest.getCourseId()));
        MDC.clear();
        return courseRepository.save(course);
    }
    public List<Course> getAllCourse() {
        List<Course> courses = courseRepository.findAll();
        logger.info("Get all course successfully");
        MDC.clear();
        return courses;
    }
    public List<Course> getAllCourseByTeacherId(Integer teacherId) {
        List<Course> courses = courseRepository.findAllByTeacherId(teacherId);
        logger.info(String.format("Get all course by teacher id = '%s' " , teacherId));
        MDC.clear();
        return courses;
    }
    public List<Course> getAllCourseBySubjectId(Integer subjectId) {
        List<Course> courses = courseRepository.findAllBySubjectId(subjectId);
        logger.info(String.format("Get all course by subject id = '%s' " , subjectId));
        MDC.clear();
        return courses;
    }
    void deleteCourse() {

    }

}
