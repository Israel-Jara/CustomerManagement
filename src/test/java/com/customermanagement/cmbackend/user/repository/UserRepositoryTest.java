package com.customermanagement.cmbackend.user.repository;


import com.customermanagement.cmbackend.user.entity.User;
import com.customermanagement.cmbackend.utils.EntityUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    public void beforeEach() {
        EntityUtils.clear(entityManager);
        EntityUtils.createUser(entityManager);
    }


    @Test
    public void should_create_new_user() {
        User user = EntityUtils.getUser("IsraelTest",
                                    "JaraTest",
                                        "israeltest@correo.com",
                                    "777777test",
                                    "user777777test",
                                    "passtest");

        userRepository.save(user);

        User userTest = userRepository.findByDocument("777777test").orElse(null);
        assertNotNull(userTest);

    }

    @Test
    public void should_find_one_user() {
        User userTest = userRepository.findByDocument("111111").orElse(null);
        assertNotNull(userTest);
    }

    @Test
    public void should_find_all_users() {
        List<User> userTest = userRepository.findAll();
        assertEquals(userTest.size(), 1);
    }

}
