package com.example.studentscoremanagerbe.repositories;
import com.example.studentscoremanagerbe.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Some javadoc. // OK
 *
 * @author Linh
 * @since 20/11/2022
 * @deprecated Some javadoc.
 */
@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
    public List<Faculty> findAll();
    public Faculty findById(int id);

    public Faculty findByName(String name);

}
