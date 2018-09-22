package com.atishay.springboot.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atishay.springboot.project.model.UserRole;

/***
 * This interface indicates dao layer for UserRole.java
 * 
 * @author vishvas
 *
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String> {
	public Optional<UserRole> findByUserRoleName(String usernameOrEmail);
}
