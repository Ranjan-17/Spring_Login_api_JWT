package com.example.loginApi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.loginApi.entity.roleEntity;

public interface roleRepository extends JpaRepository<roleEntity,Long>{
	Optional<roleEntity> findByName(String name);
}
