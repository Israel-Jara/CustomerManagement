package com.customermanagement.cmbackend.utils;

import com.customermanagement.cmbackend.role.entity.Role;
import com.customermanagement.cmbackend.user.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityManager;


public class EntityUtils {



    public static User getUser(String name, String lastName, String email, String document, String username, String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordTest = passwordEncoder.encode(password);

        User user = new User();
        user.setName(name);
        user.setLastname(lastName);
        user.setEmail(email);
        user.setDocument(document);
        user.setUsername(username);
        user.setPassword(passwordTest);
        return user;
    }

    public static Role getRole(String role) {
        Role newRole = new Role();
        newRole.setRole(role);
        return newRole;
    }


    public static void createUser(EntityManager entityManager) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordTest = passwordEncoder.encode("pass1");

        User user = new User();
        user.setName("UserName1");
        user.setLastname("UserLastName1");
        user.setEmail("useremail1@correo.com");
        user.setDocument("111111");
        user.setUsername("user111111");
        user.setPassword(passwordTest);
        entityManager.persist(user);
    }

    public static int clear(EntityManager entityManager) {
        return entityManager.createNativeQuery("delete from users as u where u.id > 0", User.class).executeUpdate();
    }

}
