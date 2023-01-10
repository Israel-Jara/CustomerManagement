package com.customermanagement.cmbackend.user.service;

import com.customermanagement.cmbackend.role.entity.Role;
import com.customermanagement.cmbackend.role.repository.RoleRepository;
import com.customermanagement.cmbackend.user.dto.UserDTO;
import com.customermanagement.cmbackend.user.entity.User;
import com.customermanagement.cmbackend.user.exception.customUserError.UserNotFoundException;
import com.customermanagement.cmbackend.user.repository.UserRepository;
import com.customermanagement.cmbackend.utils.EntityUtils;
import com.customermanagement.cmbackend.utils.DataLoader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {


    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;


    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void should_return_user_by_id() throws Exception {

        final Integer id = 1;

        User user = new User();
        user.setId(1);
        user.setName("UserName1");
        user.setLastname("UserLastName1");
        user.setEmail("user@correo.com");
        user.setDocument("111111");
        user.setUsername("username1");
        user.setPassword("passuser1");

        given(userRepository.findById(id)).willReturn(Optional.of(user));

        UserDTO userDTOexpected = userService.getUser(id);

        assertNotNull(userDTOexpected);
        assertEquals(user.getName(), userDTOexpected.getName());
        assertEquals(user.getLastname(), userDTOexpected.getLastname());
        assertEquals(user.getEmail(), userDTOexpected.getEmail());
        assertEquals(user.getDocument(), userDTOexpected.getDocument());
    }


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
    }


}
