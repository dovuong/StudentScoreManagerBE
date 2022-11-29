package com.example.studentscoremanagerbe.services;

import com.example.studentscoremanagerbe.model.ClassRoom;
import com.example.studentscoremanagerbe.model.Faculty;
import com.example.studentscoremanagerbe.payload.request.ClassroomRequest;
import com.example.studentscoremanagerbe.payload.request.ListClassroomRequest;
import com.example.studentscoremanagerbe.payload.request.NameRequest;
import com.example.studentscoremanagerbe.payload.request.UpdateClassroomRequest;
import com.example.studentscoremanagerbe.repositories.ClassroomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomService {
    Logger logger = LoggerFactory.getLogger(ClassroomService.class);
    @Autowired
    ClassroomRepository classRoomRepository;
    @Autowired
    FacultyService facultyService;
    public ResponseEntity<?> getClassRoom()
    {
        List<ClassRoom> classRooms = classRoomRepository.findAll();
        if (classRooms == null)
        {
            logger.error("Get all classroom failed. Cause by list classroom are not found");
            return ResponseEntity.ok("List classroom empty");
        }
        else
        {
            logger.info("Get all classroom successfully ");
            return ResponseEntity.ok(classRooms);
        }
    }
    public ClassRoom getClassRoomById(int id)
    {
        ClassRoom classRooms = classRoomRepository.findClassRoomById(id);
        if (classRooms == null)
        {
            logger.error("Get classroom failed. Cause by list classroom are not found");
            return null;
        }
        else
        {
            logger.info("Get  classroom successfully ");
            return classRooms;
        }
    }
    public ResponseEntity<?> getClassRoomByFaculty(int idFaculty)
    {
        List<ClassRoom> classRooms = classRoomRepository.findAllByFacultyId(idFaculty);
        if (classRooms == null)
        {
            logger.error("Get all classroom failed. Cause by list classroom are not found");
            return ResponseEntity.ok("List classroom empty");
        }
        else
        {
            logger.info("Get all classroom successfully ");
            return ResponseEntity.ok(classRooms);
        }
    }
    public ResponseEntity<?> createClassRoom(ClassroomRequest classRoomRequest)
    {
        Faculty  faculty = facultyService.getFacultyById1(classRoomRequest.getIdFaculty());
        if (faculty == null)
        {
            logger.error("Get create failed. Cause by list faculty are not found");
            return ResponseEntity.ok("List  faculty  empty");
        }
        else
        {
           ClassRoom classRoom = classRoomRepository.findClassRoomByName(classRoomRequest.getNameClassRoom());
           if (classRoom == null)
           {
               ClassRoom classRoom1 = new ClassRoom();
               classRoom1.setName(classRoomRequest.getNameClassRoom());
               classRoom1.setFaculty(faculty);
               ClassRoom classRoom2 =  classRoomRepository.save(classRoom1);
               logger.info("Create classroom name = '{}'", classRoom2.getName());
               return ResponseEntity.ok("create new classroom successfully");
           }
           return ResponseEntity.ok("Classroom exist");
        }
    }
    public ResponseEntity<?> updateClassRoom(UpdateClassroomRequest updateClassroomRequest)
    {
        Faculty  faculty = facultyService.getFacultyById1(updateClassroomRequest.getIdFaculty());
        if (faculty == null)
        {
            logger.error("update failed. Cause by list faculty are not found");
            return ResponseEntity.ok("List faculty empty");
        }
        else
        {
            ClassRoom classRoom1 = classRoomRepository.findClassRoomByIdAndFacultyId(updateClassroomRequest.getIdClass(), updateClassroomRequest.getIdFaculty());
            if (classRoom1 != null)
            {
                classRoom1.setName(updateClassroomRequest.getNameClassRoom());
                classRoom1.setFaculty(faculty);
                ClassRoom classRoom2 =  classRoomRepository.save(classRoom1);
                logger.info("update  classroom name = '{}'", classRoom2.getName());
                return ResponseEntity.ok("update new classroom successfully");
            }
            return ResponseEntity.ok("Classroom doesn't exist");
        }
    }
    public ResponseEntity<?> createListClassRoom(ListClassroomRequest listclassRoomRequest) {
        Faculty faculty = facultyService.getFacultyById1(listclassRoomRequest.getIdFaculty());
        if (faculty == null)
        {
            logger.error("Get create failed. Cause by list faculty are not found");
            return ResponseEntity.ok("List faculty empty");
        }
        for (NameRequest i : listclassRoomRequest.getNameClassroom()) {
            ClassRoom classRoom = classRoomRepository.findClassRoomByName(i.getNameClassroom());
            if (classRoom != null) continue;
            ClassRoom classRoom1 = new ClassRoom();
            classRoom1.setName(i.getNameClassroom());
            classRoom1.setFaculty(faculty);
            ClassRoom classRoom2 = classRoomRepository.save(classRoom1);
            logger.info("Create classroom name = '{}'", classRoom2.getName());

        }
        return ResponseEntity.ok("create list classRoom success!");
    }
    public ResponseEntity<?> updateListClassRoom(ListClassroomRequest listclassRoomRequest) {
        Faculty faculty = facultyService.getFacultyById1(listclassRoomRequest.getIdFaculty());
        if (faculty == null)
        {
            logger.error("update failed. Cause by list faculty are not found");
            return ResponseEntity.ok("List faculty empty");
        }
        for (NameRequest i : listclassRoomRequest.getNameClassroom()) {
            ClassRoom classRoom = classRoomRepository.findClassRoomByIdAndFacultyId(i.getIdClass(), listclassRoomRequest.getIdFaculty());
            if (classRoom == null) continue;
            classRoom.setName(i.getNameClassroom());
            classRoom.setFaculty(faculty);
            ClassRoom classRoom2 = classRoomRepository.save(classRoom);
            logger.info("update classroom name = '{}'", classRoom2.getName());

        }
        return ResponseEntity.ok("update list classRoom success!");
    }

}
