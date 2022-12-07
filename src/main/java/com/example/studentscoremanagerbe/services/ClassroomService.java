package com.example.studentscoremanagerbe.services;

import com.example.studentscoremanagerbe.model.ClassRoom;
import com.example.studentscoremanagerbe.model.Faculty;
import com.example.studentscoremanagerbe.payload.request.*;
import com.example.studentscoremanagerbe.repositories.ClassroomRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j

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
            MDC.clear();
            return ResponseEntity.ok("List classroom empty");
        }
        else
        {
            logger.info("Get all classroom successfully ");
            MDC.clear();
            return ResponseEntity.ok(classRooms);
        }
    }
    public ClassRoom getClassRoomById(int id)
    {
        ClassRoom classRooms = classRoomRepository.findClassRoomById(id);
        if (classRooms == null)
        {
            logger.error(String.format("Get classroom failed. Cause by classroom id = '%s' are not found",id));
            MDC.clear();
            return null;
        }
        else
        {
            logger.info(String.format("Get classroom id = '%s' successfully",id));
            MDC.clear();
            return classRooms;
        }
    }
    public ResponseEntity<?> getClassRoomByFaculty(int idFaculty)
    {
        List<ClassRoom> classRooms = classRoomRepository.findAllByFacultyId(idFaculty);
        if (classRooms == null)
        {
            logger.error(String.format("Get list classroom by faculty failed. Cause by faculty id = '%s' are not found",idFaculty));
            MDC.clear();
            return ResponseEntity.ok("List classroom empty");
        }
        else
        {
            logger.info(String.format("Get list classroom by faculty id = '%s' successfully",idFaculty));
            MDC.clear();
            return ResponseEntity.ok(classRooms);
        }
    }
    public ResponseEntity<?> createClassRoom(ClassroomRequest classRoomRequest)
    {
        Faculty  faculty = facultyService.getFacultyById1(classRoomRequest.getIdFaculty());
        if (faculty == null)
        {
            logger.error("Create classroom failed. Cause by faculty are not found");
            MDC.clear();
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
               logger.info(String.format("Create classroom name = '%s' successfully", classRoom2.getName()));
               MDC.clear();
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
            logger.error("Update classroom failed. Cause by faculty are not found");
            MDC.clear();
            return ResponseEntity.ok("List faculty empty");
        }
        else
        {
            ClassRoom classRoom1 = classRoomRepository.findClassRoomByIdAndFacultyId(updateClassroomRequest.getIdClass(), updateClassroomRequest.getIdFaculty());
            ClassRoom classRoom = classRoomRepository.findClassRoomByNameAndFacultyId(updateClassroomRequest.getNameClassRoom(), updateClassroomRequest.getIdFaculty());

            if (classRoom1 != null && classRoom == null)
            {
                classRoom1.setName(updateClassroomRequest.getNameClassRoom());
                classRoom1.setFaculty(faculty);
                ClassRoom classRoom2 =  classRoomRepository.save(classRoom1);
                logger.info(String.format("Update classroom name = '%s' successfully", classRoom2.getName()));
                MDC.clear();
                return ResponseEntity.ok("update new classroom successfully");
            }
            return ResponseEntity.ok("Classroom doesn't exist or name class exist");
        }
    }
    public ResponseEntity<?> createListClassRoom(ListClassroomRequest listclassRoomRequest) {
        Faculty faculty = facultyService.getFacultyById1(listclassRoomRequest.getIdFaculty());
        if (faculty == null)
        {
            logger.error("Create list classroom failed. Cause by faculty are not found");
            MDC.clear();
            return ResponseEntity.ok("List faculty empty");
        } else {
            logger.info("Create list classroom ");
            MDC.clear();
            for (NameCreateRequest i : listclassRoomRequest.getNameClassroom()) {
                ClassRoom classRoom = classRoomRepository.findClassRoomByName(i.getNameClassroom());
                if (classRoom != null) continue;
                ClassRoom classRoom1 = new ClassRoom();
                classRoom1.setName(i.getNameClassroom());
                classRoom1.setFaculty(faculty);
                ClassRoom classRoom2 = classRoomRepository.save(classRoom1);
                logger.info(String.format("Create classroom name = '%s' successfully", classRoom2.getName()));
            }
        }

        return ResponseEntity.ok("create list classRoom success!");
    }
    public ResponseEntity<?> updateListClassRoom(ListUpdateClassRequest listclassRoomRequest) {
        Faculty faculty = facultyService.getFacultyById1(listclassRoomRequest.getIdFaculty());
        if (faculty == null)
        {
            logger.error("Update list classroom failed. Cause by faculty are not found");
            MDC.clear();
            return ResponseEntity.ok("List faculty empty");
        } else {
            logger.info("Update list class room ");
            MDC.clear();
            for (NameRequest i : listclassRoomRequest.getNameClassroom()) {
                ClassRoom classRoom = classRoomRepository.findClassRoomByIdAndFacultyId(i.getIdClass(), listclassRoomRequest.getIdFaculty());
                ClassRoom classRoom1 = classRoomRepository.findClassRoomByNameAndFacultyId(i.getNameClassroom(), listclassRoomRequest.getIdFaculty());
                if (classRoom == null) continue;
                if (classRoom1 != null) continue;
                classRoom.setName(i.getNameClassroom());
                classRoom.setFaculty(faculty);
                ClassRoom classRoom2 = classRoomRepository.save(classRoom);
                logger.info(String.format("Update classroom name = '%s' successfully", classRoom2.getName()));
            }
        }

        return ResponseEntity.ok("update list classRoom success!");
    }

}
