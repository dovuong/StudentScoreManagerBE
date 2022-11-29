package com.example.studentscoremanagerbe.repositories;

import com.example.studentscoremanagerbe.model.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomRepository extends JpaRepository<ClassRoom, Integer> {
public List<ClassRoom> findAllByFacultyId(int id);
public ClassRoom findClassRoomByName(String name);
public ClassRoom findClassRoomById(int id);
public ClassRoom findClassRoomByNameAndFacultyId(String name, int fac);

}
