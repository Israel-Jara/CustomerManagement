package com.customermanagement.cmbackend.userRole.repository;

import com.customermanagement.cmbackend.role.entity.Role;
import com.customermanagement.cmbackend.user.entity.User;
import com.customermanagement.cmbackend.userRole.entity.UsersRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UsersRoles, Integer> {

    Optional<UsersRoles> findUsersRolesByUserAndRole(User user, Role role);

}
