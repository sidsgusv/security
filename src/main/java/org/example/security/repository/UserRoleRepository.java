package org.example.security.repository;

import org.example.security.model.entity.UserRoleEntity;
import org.example.security.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
    Optional<UserRoleEntity> findUserRoleEntityByRole(UserRoleEnum role);

}