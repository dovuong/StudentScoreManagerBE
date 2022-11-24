package com.example.studentscoremanagerbe.services;

import com.example.studentscoremanagerbe.model.Role;
import com.example.studentscoremanagerbe.model.User;
import com.example.studentscoremanagerbe.repositories.RoleRepository;
import com.example.studentscoremanagerbe.repositories.UserRepository;
import common.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

/** Some javadoc. // OK
 * @author Vuong
 * @since 20/11/2022
 * @deprecated Some javadoc.
 */
@SuppressWarnings({"checkstyle:Indentation"})
@Service
public class CustomUserDetailsService implements UserDetailsService {
    Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
    @Autowired
    UserRepository userRepo;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return UserDetailsImpl.build(user.getId(), user.getUsername(), user.getPassword(), user.getRole().getName());
    }

    /**
     * Some javadoc. // OK
     *
     * @author Vuong
     * @since 20/11/2022
     * @deprecated Some javadoc.
     */
    public int loadUserIdByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user.getId();
    }


    /**
     * Some javadoc. // OK
     *
     * @author Vuong
     * @since 20/11/2022
     * @deprecated Some javadoc.
     */
    public User findUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }


    public int saveTeacher(String username, String password) {
        return saveUser(username, password, 2);
    }

    public int saveAdmin(String username, String password) {
        return saveUser(username, password, 1);
    }


    /**
     * Some javadoc. // OK
     *
     * @author Vuong
     * @since 20/11/2022
     * @deprecated Some javadoc.
     */
    public int saveUser(String username, String password, int roleId) {
        try {
            User user = userRepo.findUserByUsername(username);
            System.out.println(userRepo.findUserByUsername(username));
            if (user == null) {
                Date date = new Date();
                User newUser = new User();
                newUser.setUsername(username);
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String encodedPassword = passwordEncoder.encode(password);
                newUser.setPassword(encodedPassword);
                Role role = roleRepository.findRoleById(roleId);
                newUser.setRole(role);
                newUser.setCreateAt(date);
                newUser.setUpdateAt(date);
                userRepo.save(newUser);
                logger.info("Created " + newUser.getRole().getName() + " " + newUser.getId());
                return 1;
            } else {
                logger.info("User is exist: " + user.getUsername());
                return 2;
            }
        } catch (Exception e) {
            logger.info("Custom user service: Save user exception" + e);
            return 0;
        }
    }


}