package com.example.loginApi.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.loginApi.entity.RefreshToken;
import com.example.loginApi.repository.RefreshTokenRepository;
import com.example.loginApi.repository.userRepository;

import lombok.Builder;

@Service
@Component
@Builder
public class RefreshTokenService {
	
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	@Autowired
	 userRepository UserRepository;
	
	
	public RefreshToken  createRefreshToken(String username) {
		
			RefreshToken refreshToken = new  RefreshToken()	;	
						refreshToken.setUserEntity(UserRepository.findByUsername(username).get());
						refreshToken.setExpiryDate(Instant.now().plusMillis(1000 *60*6));
						refreshToken.setToken(UUID.randomUUID().toString());
									
						refreshToken =refreshTokenRepository.save(refreshToken);
			return refreshToken;
		
	}
	
	public Optional<RefreshToken>findByToken(String token){
		return refreshTokenRepository.findByToken(token);
	}
	
	public RefreshToken verifyExpiration(RefreshToken token) {
		if(token.getExpiryDate().compareTo(Instant.now())<0) {
			refreshTokenRepository.delete(token);
			throw new RuntimeException(token.getToken()+" RefreshToken was Expired");
		}
		return token;
	}
}
