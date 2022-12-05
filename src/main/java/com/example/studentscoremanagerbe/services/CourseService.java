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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
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
        logger.info("create new course by admin");
        return courseRepository.save(course);
    }
    public Course updateCourse(UpdateCourseRequest updateCourseRequest) {
        Course course = courseRepository.findById(updateCourseRequest.getCourseId()).get();
        User user = userRepository.findUserById(updateCourseRequest.getTeacherId());
        course.setName(updateCourseRequest.getName());
        course.setUpdatedAt(new Date());
        course.setTeacher(user);
        logger.info("update course by admin courseId: " + updateCourseRequest.getCourseId());
        return courseRepository.save(course);
    }
    public List<Course> getAllCourse() {
        List<Course> courses = courseRepository.findAll();
        logger.info("get all course");
        return courses;
    }
    public List<Course> getAllCourseByTeacherId(Integer teacherId) {
        List<Course> courses = courseRepository.findAllByTeacherId(teacherId);
        logger.info("getAllCourseByTeacherId: " + teacherId);
        return courses;
    }
    public List<Course> getAllCourseBySubjectId(Integer subjectId) {
        List<Course> courses = courseRepository.findAllBySubjectId(subjectId);
        logger.info("getAllCourseBySubjectId: " + subjectId);
        return courses;
    }
    void deleteCourse() {

    }

}