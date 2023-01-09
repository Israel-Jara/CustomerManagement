package com.customermanagement.cmbackend.user.service;

import com.customermanagement.cmbackend.role.entity.Role;
import com.customermanagement.cmbackend.role.repository.RoleRepository;
import com.customermanagement.cmbackend.user.dto.UserDTO;
import com.customermanagement.cmbackend.user.entity.User;
import com.customermanagement.cmbackend.user.exception.customUserError.UserNotFoundException;
import com.customermanagement.cmbackend.user.repository.UserRepository;
import com.customermanagement.cmbackend.utils.EntityUtils;
import com.customermanagement.cmbackend.utils.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

//TODO: Agregar mockeos al H2 para poder probar todos los demas escenarios.
//@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTest {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserService userService;

    @BeforeEach
    public void beforeEach() {
        EntityUtils.clear(entityManager);
        EntityUtils.createUser(entityManager);
    }

    @Test
    public void test() throws Exception {
        UserDTO userDTO = userService.getUser(1);
        assertEquals("UserName1", userDTO.getName());
    }

    /*@Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;


    @InjectMocks
    private UserServiceImpl userService;


    @Test
    public void should_throw_userNotFound_when_wrong_id_in_getUser() {

        String password = "passtest";


        User user = EntityUtils.getUser("IsraelTest",
                "JaraTest",
                "israeltest@correo.com",
                "777777test",
                "user777777test",
                password);

        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setLastname(user.getLastname());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());


        // Probando el service.
        Throwable exception = assertThrows(UserNotFoundException.class, () -> userService.getUser(-1));
        assertEquals("Usario no encontrado.", exception.getMessage());
    }*/


}
