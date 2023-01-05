package com.customermanagement.cmbackend.role.repository;

import com.customermanagement.cmbackend.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    /***
     * Obtiene el role específico.
     * @param role el nombre del role.
     * @return el role correspondiente.
     */
    Optional<Role> findByRole(String role);


}
