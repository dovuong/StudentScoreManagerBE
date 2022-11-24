package com.example.studentscoremanagerbe.repositories;

import com.example.studentscoremanagerbe.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

/**
 * Some javadoc. // OK
 *
 * @author Vuong
 * @since 20/11/2022
 * @deprecated Some javadoc.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    public Role findRoleById(int id);
}
