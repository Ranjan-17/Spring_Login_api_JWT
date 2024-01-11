package com.example.loginApi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.loginApi.entity.userEntity;


public interface userRepository extends JpaRepository<userEntity,Long> {
	
	Optional <userEntity>findByEmail(String email);
	Optional<userEntity> findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
	
	

	
}
