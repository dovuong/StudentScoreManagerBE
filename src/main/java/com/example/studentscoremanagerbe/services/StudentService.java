package com.example.studentscoremanagerbe.services;

import com.example.studentscoremanagerbe.model.ClassRoom;
import com.example.studentscoremanagerbe.model.Faculty;
import com.example.studentscoremanagerbe.model.Student;
import com.example.studentscoremanagerbe.payload.request.*;
import com.example.studentscoremanagerbe.repositories.ClassroomRepository;
import com.example.studentscoremanagerbe.repositories.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    Logger logger = LoggerFactory.getLogger(ClassroomService.class);
    @Autowired
   ClassroomService classroomService;
    @Autowired
    StudentRepository studentRepository;
    public ResponseEntity<?> getStudent()
    {
        List<Student> Students =studentRepository.findAll();
        if(Students==null)
        {
            logger.error("Get all students failed. Cause by list students are not found");
            return ResponseEntity.ok("List students empty");
        }
        else
        {
            logger.info("Get all students successfully ");
            return ResponseEntity.ok(Students);
        }
    }
    public ResponseEntity<?> getStudentByClassroom(int idClassroom)
    {
        List<Student> Students =studentRepository.findAllByClassRoomId(idClassroom);
        if(Students==null)
        {
            logger.error("Get all students failed. Cause by list students are not found");
            return ResponseEntity.ok("List students empty");
        }
        else
        {
            logger.info("Get all students successfully ");
            return ResponseEntity.ok(Students);
        }
    }

    public ResponseEntity<?> createStudent(StudentRequest studentRequest)
    {
        ClassRoom classRoom= classroomService.getClassRoomById(studentRequest.getIdClassroom());
        if(classRoom==null)
        {
            logger.error(" create failed. Cause by classroom are not found");
            return ResponseEntity.ok("List  classroom  empty");
        }
        else
        {
           Student student= new Student();
           student.setName(studentRequest.getName());
           student.setBirthday(studentRequest.getBirthday());
           student.setClassRoom(classRoom);
           student.setNumberPhone(studentRequest.getNumberPhone());
           student.setStatus(true);
           studentRepository.save(student);
           logger.info("Create student name = '{}'", student.getName());
            return ResponseEntity.ok("Create success");
        }
    }
    public ResponseEntity<?> updateStudent(UpdateStudentRequest updateStudentRequest)
    {
     ClassRoom classRoom = classroomService.getClassRoomById(updateStudentRequest.getIdClassroom());
        if(classRoom==null)
        {
            logger.error("create failed. Cause by classroom are not found");
            return ResponseEntity.ok("classroom empty");
        }
        else
        {
            Student student= studentRepository.findStudentByIdAndClassRoomId(updateStudentRequest.getIdStudent(), updateStudentRequest.getIdClassroom());
            if(student!=null) {
                student.setName(updateStudentRequest.getName());
                student.setBirthday(updateStudentRequest.getBirthday());
                student.setClassRoom(classRoom);
                student.setNumberPhone(updateStudentRequest.getNumberPhone());
                studentRepository.save(student);
                logger.info("update student name = '{}'", student.getName());
                return ResponseEntity.ok("update success");
            }
            return ResponseEntity.ok("Student doesn't exist!");
        }
    }
    public ResponseEntity<?> createListStudent(ListStudentRequest listStudentRequest)
    {
        ClassRoom classRoom= classroomService.getClassRoomById(listStudentRequest.getIdClassroom());
        if(classRoom==null)
        {
            logger.error(" create failed. Cause by classroom are not found");
            return ResponseEntity.ok("List  classroom  empty");
        }
        else
        {
            for(InforStudentRequest i: listStudentRequest.getStudent()) {
                Student student = new Student();
                student.setName(i.getName());
                student.setBirthday(i.getBirthday());
                student.setClassRoom(classRoom);
                student.setNumberPhone(i.getNumberPhone());
                student.setStatus(true);
                studentRepository.save(student);
                logger.info("Create student name = '{}'", student.getName());

            }
            return ResponseEntity.ok("Create success");
        }
    }
    public ResponseEntity<?> updateListStudent(ListUpdateStudentRequest listUpdateStudentRequest)
    {
        ClassRoom classRoom = classroomService.getClassRoomById(listUpdateStudentRequest.getIdClassroom());
        if(classRoom==null)
        {
            logger.error("update failed. Cause by classroom are not found");
            return ResponseEntity.ok("classroom empty");
        }
        else
        {
            for(InforUpdateStudentRequest i: listUpdateStudentRequest.getStudent()) {
                Student student = studentRepository.findStudentByIdAndClassRoomId(i.getId(), listUpdateStudentRequest.getIdClassroom());
                if (student == null) continue;
                student.setName(i.getName());
                student.setBirthday(i.getBirthday());
                student.setClassRoom(classRoom);
                student.setNumberPhone(i.getNumberPhone());
                studentRepository.save(student);
                logger.info("update student name = '{}'", student.getName());
            }

                return ResponseEntity.ok("update success");

        }

    }
    public ResponseEntity<?> deleteStudent(DeleteStudentRequest deleteStudentRequest)
    {
        ClassRoom classRoom = classroomService.getClassRoomById(deleteStudentRequest.getIdClass());
        if(classRoom==null)
        {
            logger.error("delete failed. Cause by classroom are not found");
            return ResponseEntity.ok("classroom empty");
        }
        else
        {
            Student student= studentRepository.findStudentByIdAndClassRoomId(deleteStudentRequest.getIdStudent(),deleteStudentRequest.getIdClass());
            if(student!=null) {
               student.setStatus(false);
                studentRepository.save(student);
                logger.info("delete student name = '{}'", student.getName());
                return ResponseEntity.ok("delete success");
            }
            return ResponseEntity.ok("Student doesn't exist!");
        }
    }
    public ResponseEntity<?> DeleteListStudent(DeleteListStudentRequest deleteListStudentRequest)
    {
        ClassRoom classRoom = classroomService.getClassRoomById(deleteListStudentRequest.getIdClass());
        if(classRoom==null)
        {
            logger.error("delete failed. Cause by classroom are not found");
            return ResponseEntity.ok("classroom empty");
        }
        else
        {
            for(int i: deleteListStudentRequest.getIdStudents()) {
                Student student = studentRepository.findStudentByIdAndClassRoomId(i, deleteListStudentRequest.getIdClass());
                if (student == null) continue;
               student.setStatus(false);
                studentRepository.save(student);
                logger.info("delete student name = '{}'", student.getName());
            }

            return ResponseEntity.ok("delete success");

        }

    }

}
