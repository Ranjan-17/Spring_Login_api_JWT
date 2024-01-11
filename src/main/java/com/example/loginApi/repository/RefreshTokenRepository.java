package com.example.loginApi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.loginApi.entity.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Integer>{

	Optional<RefreshToken> findByToken(String token);

}
