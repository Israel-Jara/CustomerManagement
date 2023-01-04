package com.customermanagement.cmbackend.user.service;

import com.customermanagement.cmbackend.role.entity.Role;
import com.customermanagement.cmbackend.user.dto.UserInfoDTO;
import com.customermanagement.cmbackend.user.entity.User;
import com.customermanagement.cmbackend.user.exception.customUserError.DuplicateUserException;
import com.customermanagement.cmbackend.user.exception.customUserError.UserNotFoundException;
import com.customermanagement.cmbackend.userRole.entity.UsersRoles;
import com.customermanagement.cmbackend.role.repository.RoleRepository;
import com.customermanagement.cmbackend.user.repository.UserRepository;
import com.customermanagement.cmbackend.userRole.exception.customuserroleerror.DuplicateUserRoleException;
import com.customermanagement.cmbackend.userRole.repository.UserRoleRepository;
import com.customermanagement.cmbackend.user.dto.UserDTO;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) throws Exception {

        if (userRepository.findByUsernameOrEmail( userDTO.getName() + userDTO.getLastname(), userDTO.getEmail()).isPresent()) {
            throw new DuplicateUserException("Este usuario ya se encuentra en el sistema.", new RuntimeException());
        }

        Role roleCustomer = getDefaultRole(); // Role por defecto ROLE_CUSTOMER
        if (roleCustomer == null) {
            throw new RuntimeException("No se encotró el rol."); // fallo del sistema.
        }

        User newUser = insertUser(userDTO);

        // Este error en este sistema como esta ahora mismo nunca va ocurrir al menos que se inserte en la BD directamente.
        if (userRoleRepository.findUsersRolesByUserAndRole(newUser, roleCustomer).isPresent()) {
            throw new DuplicateUserRoleException("Esto usuario ya existe con este rol", new RuntimeException());
        }

        insertUserRole(newUser, roleCustomer);

        return CreateUserDTO(newUser);
    }

    @Override
    public UserDTO getUser(Integer userId) throws Exception {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
           throw new UserNotFoundException("Usario no encontrado.", new ResourceNotFoundException());
        }
        return CreateUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();

        List<UserDTO> userDTOS = new ArrayList<>();
        users.forEach(user ->
             userDTOS.add(CreateUserDTO(user))
        );

        return userDTOS;
    }

    @Override
    public UserDTO updateUser(UserInfoDTO userInfoDTO, Integer userId) throws Exception {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            throw new UserNotFoundException("Usuario no encontrado.", new ResourceNotFoundException());
        }

        user.setName(userInfoDTO.getName());
        user.setLastname(userInfoDTO.getLastname());

        userRepository.save(user);

        return CreateUserDTO(user);
    }

    @Override
    public void deleteUser(Integer userId) throws Exception {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("Usuario no encontrado.", new ResourceNotFoundException());
        }
        userRepository.delete(user);
    }

    @Override
    public UserDTO getInfo(String usernameOrEmail) {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElse(null);
        return CreateUserDTO(user);
    }

    private User insertUser(UserDTO userDTO) {
        User newUser = new User();
        newUser.setName(userDTO.getName());
        newUser.setLastname(userDTO.getLastname());
        newUser.setEmail(userDTO.getEmail());
        newUser.setUsername(userDTO.getName() + userDTO.getLastname()); // por defecto
        newUser.setPassword(GenerateTemporaryPassword());

        return userRepository.save(newUser);
    }

    private Role getDefaultRole() {
        return roleRepository.findByRole("ROLE_CUSTOMER").orElse(null);
    }

    private void insertUserRole(User user, Role role) {
        UsersRoles newUserRole = new UsersRoles();
        newUserRole.setUser(user);
        newUserRole.setRole(role);
        userRoleRepository.save(newUserRole);
    }

    private String GenerateTemporaryPassword() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode("passtemp");
    }

    private UserDTO CreateUserDTO(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getLastname(), user.getEmail());
    }
}


