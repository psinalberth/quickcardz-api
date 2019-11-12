package com.github.psinalberth.quickcardz.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.github.psinalberth.quickcardz.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("select u from User u left join fetch u.authorities where u.username = :username")
	public Optional<User> findByUsername(@Param("username") String username);
}
