package com.atishay.springboot.project.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.atishay.springboot.project.model.User;

/***
 * This interface indicates dao layer for User.java
 * 
 * @author vishvas
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

	public Optional<User> findByUsername(String username);

	public Optional<User> findByEmail(String username);

	public Optional<User> findByMobileNumber(String mobileNumber);

	@Query(value = "SELECT * FROM tblUser aliasUser WHERE username=?1 OR email = ?1", nativeQuery = true)
	public Optional<User> findByUsernameOrEmail(String usernameOrEmail);

	@Query(value = "SELECT * FROM tblUser aliasUser WHERE age = ?1", nativeQuery = true)
	public Page<User> paginationUsingNativeQuery(int age, Pageable pageable);

	public Page<User> findAll(Specification<User> spec, Pageable pageable);

	public Optional<User> findById(String id);

}
